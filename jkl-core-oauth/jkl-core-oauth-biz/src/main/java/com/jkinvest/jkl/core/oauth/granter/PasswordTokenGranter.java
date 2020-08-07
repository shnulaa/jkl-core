package com.jkinvest.jkl.core.oauth.granter;

import static com.jkinvest.jkl.core.oauth.granter.PasswordTokenGranter.GRANT_TYPE;

import org.springframework.stereotype.Component;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.base.jwt.model.AuthInfo;
import com.jkinvest.jkl.core.authority.dto.auth.LoginParamDTO;

/**
 * 账号密码登录获取token
 *
 * @author zuihou
 * @date 2020年03月31日10:22:55
 */
@Component(GRANT_TYPE)
public class PasswordTokenGranter extends AbstractTokenGranter implements TokenGranter {

    public static final String GRANT_TYPE = "password";

    @Override
    public R<AuthInfo> grant(LoginParamDTO tokenParameter) {
        return login(tokenParameter);
    }
}
