package com.jkinvest.jkl.core.tenant.service.impl;

import static com.jkinvest.jkl.base.utils.BizAssert.isFalse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jkinvest.jkl.base.base.service.SuperServiceImpl;
import com.jkinvest.jkl.base.database.mybatis.conditions.Wraps;
import com.jkinvest.jkl.base.utils.BeanPlusUtil;
import com.jkinvest.jkl.base.utils.BizAssert;
import com.jkinvest.jkl.base.utils.StrHelper;
import com.jkinvest.jkl.core.authority.dto.auth.UserUpdatePasswordDTO;
import com.jkinvest.jkl.core.tenant.dao.GlobalUserMapper;
import com.jkinvest.jkl.core.tenant.dto.GlobalUserSaveDTO;
import com.jkinvest.jkl.core.tenant.dto.GlobalUserUpdateDTO;
import com.jkinvest.jkl.core.tenant.entity.GlobalUser;
import com.jkinvest.jkl.core.tenant.service.GlobalUserService;

import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 业务实现类
 * 全局账号
 * </p>
 *
 * @author zuihou
 * @date 2019-10-25
 */
@Slf4j
@Service

public class GlobalUserServiceImpl extends SuperServiceImpl<GlobalUserMapper, GlobalUser> implements GlobalUserService {

    @Override
    public Boolean check(String account) {
        return super.count(Wraps.<GlobalUser>lbQ()
                .eq(GlobalUser::getAccount, account)) > 0;
    }

    /**
     * @param data
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public GlobalUser save(GlobalUserSaveDTO data) {
        BizAssert.equals(data.getPassword(), data.getConfirmPassword(), "2次输入的密码不一致");
        isFalse(check(data.getAccount()), "账号已经存在");

        String md5Password = SecureUtil.md5(data.getPassword());

        GlobalUser globalAccount = BeanPlusUtil.toBean(data, GlobalUser.class);
        // 全局表就不存用户数据了
        globalAccount.setPassword(md5Password);
        globalAccount.setName(StrHelper.getOrDef(data.getName(), data.getAccount()));
        globalAccount.setReadonly(false);

        save(globalAccount);
        return globalAccount;
    }

    /**
     * @param data
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public GlobalUser update(GlobalUserUpdateDTO data) {
        GlobalUser globalUser = BeanPlusUtil.toBean(data, GlobalUser.class);
        globalUser.setPassword(null);
        updateById(globalUser);
        return globalUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updatePassword(UserUpdatePasswordDTO data) {
        BizAssert.equals(data.getConfirmPassword(), data.getPassword(), "密码与确认密码不一致");

        GlobalUser user = getById(data.getId());
        BizAssert.notNull(user, "用户不存在");
        String oldPassword = SecureUtil.md5(data.getOldPassword());
        BizAssert.equals(user.getPassword(), oldPassword, "旧密码错误");

        GlobalUser build = GlobalUser.builder().password(SecureUtil.md5(data.getPassword())).id(data.getId()).build();
        return updateById(build);
    }
}
