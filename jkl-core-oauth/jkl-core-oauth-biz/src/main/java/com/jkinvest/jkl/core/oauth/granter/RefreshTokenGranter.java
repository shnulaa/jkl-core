package com.jkinvest.jkl.core.oauth.granter;

import static com.jkinvest.jkl.core.oauth.granter.RefreshTokenGranter.GRANT_TYPE;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.base.context.BaseContextConstants;
import com.jkinvest.jkl.base.jwt.model.AuthInfo;
import com.jkinvest.jkl.base.utils.SpringUtils;
import com.jkinvest.jkl.base.utils.StrHelper;
import com.jkinvest.jkl.core.authority.dto.auth.LoginParamDTO;
import com.jkinvest.jkl.core.authority.entity.auth.User;
import com.jkinvest.jkl.core.authority.event.LoginEvent;
import com.jkinvest.jkl.core.authority.event.model.LoginStatusDTO;

/**
 * RefreshTokenGranter
 *
 * @author zuihou
 * @date 2020年03月31日10:23:53
 */
@Component(GRANT_TYPE)
public class RefreshTokenGranter extends AbstractTokenGranter implements TokenGranter {

    public static final String GRANT_TYPE = "refresh_token";

    @Override
    public R<AuthInfo> grant(LoginParamDTO loginParam) {
        String grantType = loginParam.getGrantType();
        String refreshTokenStr = loginParam.getRefreshToken();
        if (StrHelper.isAnyBlank(grantType, refreshTokenStr) || !GRANT_TYPE.equals(grantType)) {
            return R.fail("加载用户信息失败");
        }

        AuthInfo authInfo = tokenUtil.parseRefreshToken(refreshTokenStr);

        if (!BaseContextConstants.REFRESH_TOKEN_KEY.equals(authInfo.getTokenType())) {
            return R.fail("refreshToken无效，无法加载用户信息");
        }

        User user = userService.getByIdCache(authInfo.getUserId());

        if (user == null || !user.getStatus()) {
            String msg = "您已被禁用！";
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(user.getId(), msg)));
            return R.fail(msg);
        }

        // 密码过期
        if (user.getPasswordExpireTime() != null && LocalDateTime.now().isAfter(user.getPasswordExpireTime())) {
            String msg = "用户密码已过期，请修改密码或者联系管理员重置!";
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(user.getId(), msg)));
            return R.fail(msg);
        }

        return R.success(createToken(user));

    }
}
