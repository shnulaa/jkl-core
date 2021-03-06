package com.jkinvest.jkl.core.oauth.granter;

import static com.jkinvest.jkl.base.context.BaseContextConstants.BASIC_HEADER_KEY;
import static com.jkinvest.jkl.base.utils.BizAssert.gt;
import static com.jkinvest.jkl.base.utils.BizAssert.notNull;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.base.boot.utils.WebUtils;
import com.jkinvest.jkl.base.context.BaseContextHandler;
import com.jkinvest.jkl.base.database.mybatis.conditions.Wraps;
import com.jkinvest.jkl.base.exception.code.ExceptionCode;
import com.jkinvest.jkl.base.jwt.TokenUtil;
import com.jkinvest.jkl.base.jwt.model.AuthInfo;
import com.jkinvest.jkl.base.jwt.model.JwtUserInfo;
import com.jkinvest.jkl.base.jwt.utils.JwtUtil;
import com.jkinvest.jkl.base.utils.BeanPlusUtil;
import com.jkinvest.jkl.base.utils.BizAssert;
import com.jkinvest.jkl.base.utils.DateUtils;
import com.jkinvest.jkl.base.utils.SpringUtils;
import com.jkinvest.jkl.base.utils.StrHelper;
import com.jkinvest.jkl.base.utils.StrPool;
import com.jkinvest.jkl.core.authority.dto.auth.LoginParamDTO;
import com.jkinvest.jkl.core.authority.entity.auth.Application;
import com.jkinvest.jkl.core.authority.entity.auth.User;
import com.jkinvest.jkl.core.authority.entity.auth.UserToken;
import com.jkinvest.jkl.core.authority.event.LoginEvent;
import com.jkinvest.jkl.core.authority.event.model.LoginStatusDTO;
import com.jkinvest.jkl.core.authority.service.auth.ApplicationService;
import com.jkinvest.jkl.core.authority.service.auth.UserService;
import com.jkinvest.jkl.core.oauth.utils.TimeUtils;
import com.jkinvest.jkl.core.tenant.entity.Tenant;
import com.jkinvest.jkl.core.tenant.enumeration.TenantStatusEnum;
import com.jkinvest.jkl.core.tenant.service.TenantService;

import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.CacheChannel;

/**
 * 验证码TokenGranter
 *
 * @author Chill
 */
@Slf4j
public abstract class AbstractTokenGranter implements TokenGranter {
    @Autowired
    protected TokenUtil tokenUtil;
    @Autowired
    protected UserService userService;
    @Autowired
    protected TenantService tenantService;
    @Autowired
    protected CacheChannel cacheChannel;
    @Autowired
    protected ApplicationService applicationService;

    /**
     * 处理登录逻辑
     *
     * @param loginParam 登录参数
     * @return 认证信息
     */
    protected R<AuthInfo> login(LoginParamDTO loginParam) {
        if (StrHelper.isAnyBlank(loginParam.getAccount(), loginParam.getPassword())) {
            return R.fail("请输入用户名或密码");
        }
        // 1，检测租户是否可用
        Tenant tenant = this.tenantService.getByCode(loginParam.getTenant());
        notNull(tenant, "企业不存在");
        BizAssert.equals(TenantStatusEnum.NORMAL, tenant.getStatus(), "企业不可用~");
        if (tenant.getExpirationTime() != null) {
            gt(LocalDateTime.now(), tenant.getExpirationTime(), "企业服务已到期~");
        }

        BaseContextHandler.setTenant(tenant.getCode());
        // 2.检测client是否可用
        R<String[]> checkR = checkClient();
        if (checkR.getIsError()) {
            return R.fail(checkR.getMsg());
        }

        // 3. 验证登录
        R<User> result = this.getUser(tenant, loginParam.getAccount(), loginParam.getPassword());
        if (result.getIsError()) {
            return R.fail(result.getCode(), result.getMsg());
        }

        // 4.查询用户的权限
        User user = result.getData();

        // 5.生成 token
        AuthInfo authInfo = this.createToken(user);

        UserToken userToken = getUserToken(checkR.getData()[0], authInfo);

        //成功登录事件
        SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.success(user.getId(), userToken)));
        return R.success(authInfo);
    }

    private UserToken getUserToken(String clientId, AuthInfo authInfo) {
        UserToken userToken = new UserToken();
        Map<String, String> fieldMapping = new HashMap<>();
        fieldMapping.put("userId", "createUser");
        BeanPlusUtil.copyProperties(authInfo, userToken, CopyOptions.create().setFieldMapping(fieldMapping));
        userToken.setClientId(clientId);
        userToken.setExpireTime(DateUtils.date2LocalDateTime(authInfo.getExpiration()));
        return userToken;
    }


    /**
     * 检测 client
     *
     * @return
     */
    protected R<String[]> checkClient() {
        String basicHeader = ServletUtil.getHeader(WebUtils.request(), BASIC_HEADER_KEY, StrPool.UTF_8);
        String[] client = JwtUtil.getClient(basicHeader);
        Application application = applicationService.getOne(Wraps.<Application>lbQ().eq(Application::getClientId, client[0])
                .eq(Application::getClientSecret, client[1]));

        if (application == null) {
            return R.fail("请填写正确的客户端ID或者客户端秘钥");
        }
        if (!application.getStatus()) {
            return R.fail("客户端[%s]已被禁用", application.getClientId());
        }
        return R.success(client);
    }


    /**
     * 检测用户密码是否正确
     *
     * @param tenant   租户
     * @param account  账号
     * @param password 密码
     * @return 用户信息
     */
    protected R<User> getUser(Tenant tenant, String account, String password) {
        User user = this.userService.getByAccount(account);
        // 密码错误
        String passwordMd5 = cn.hutool.crypto.SecureUtil.md5(password);
        if (user == null) {
            return R.fail(ExceptionCode.JWT_USER_INVALID);
        }

        if (!user.getPassword().equalsIgnoreCase(passwordMd5)) {
            String msg = "用户名或密码错误!";
            // 密码错误事件
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.pwdError(user.getId(), msg)));
            return R.fail(msg);
        }

        // 密码过期
        if (user.getPasswordExpireTime() != null && LocalDateTime.now().isAfter(user.getPasswordExpireTime())) {
            String msg = "用户密码已过期，请修改密码或者联系管理员重置!";
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(user.getId(), msg)));
            return R.fail(msg);
        }

        if (!user.getStatus()) {
            String msg = "用户被禁用，请联系管理员！";
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(user.getId(), msg)));
            return R.fail(msg);
        }

        // 用户锁定
//        Integer maxPasswordErrorNum = Convert.toInt(tenant.getPasswordErrorNum(), 0);
        Integer maxPasswordErrorNum = 0;
        Integer passwordErrorNum = Convert.toInt(user.getPasswordErrorNum(), 0);
        if (maxPasswordErrorNum > 0 && passwordErrorNum > maxPasswordErrorNum) {
            log.info("当前错误次数{}, 最大次数:{}", passwordErrorNum, maxPasswordErrorNum);

            LocalDateTime passwordErrorLockTime = TimeUtils.getPasswordErrorLockTime("0");
            log.info("passwordErrorLockTime={}", passwordErrorLockTime);
            if (passwordErrorLockTime.isAfter(user.getPasswordErrorLastTime())) {
                // 登录失败事件
                String msg = StrUtil.format("密码连续输错次数已达到{}次,用户已被锁定~", maxPasswordErrorNum);
                SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(user.getId(), msg)));
                return R.fail(msg);
            }
        }

        return R.success(user);
    }

    /**
     * 创建用户TOKEN
     *
     * @param user 用户
     * @return token
     */
    protected AuthInfo createToken(User user) {
        JwtUserInfo userInfo = new JwtUserInfo(user.getId(), user.getAccount(), user.getName());
        AuthInfo authInfo = tokenUtil.createAuthInfo(userInfo, null);
        authInfo.setAvatar(user.getAvatar());
        authInfo.setWorkDescribe(user.getWorkDescribe());
        return authInfo;
    }


}
