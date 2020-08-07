package com.jkinvest.jkl.core.oauth.granter;

import static com.jkinvest.jkl.core.oauth.granter.CaptchaTokenGranter.GRANT_TYPE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.base.context.BaseContextHandler;
import com.jkinvest.jkl.base.exception.BizException;
import com.jkinvest.jkl.base.jwt.model.AuthInfo;
import com.jkinvest.jkl.base.utils.SpringUtils;
import com.jkinvest.jkl.core.authority.dto.auth.LoginParamDTO;
import com.jkinvest.jkl.core.authority.event.LoginEvent;
import com.jkinvest.jkl.core.authority.event.model.LoginStatusDTO;
import com.jkinvest.jkl.core.oauth.service.ValidateCodeService;

import lombok.extern.slf4j.Slf4j;

/**
 * 验证码TokenGranter
 *
 * @author Chill
 */
@Component(GRANT_TYPE)
@Slf4j
public class CaptchaTokenGranter extends AbstractTokenGranter implements TokenGranter {

    public static final String GRANT_TYPE = "captcha";
    @Autowired
    private ValidateCodeService validateCodeService;

    @Override
    public R<AuthInfo> grant(LoginParamDTO loginParam) {
        R<Boolean> check = validateCodeService.check(loginParam.getKey(), loginParam.getCode());
        if (check.getIsError()) {
            String msg = check.getMsg();
            BaseContextHandler.setTenant(loginParam.getTenant());
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(loginParam.getAccount(), msg)));
            throw BizException.validFail(check.getMsg());
        }

        return login(loginParam);
    }

}
