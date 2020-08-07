package com.jkinvest.jkl.core.oauth.granter;


import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.base.jwt.model.AuthInfo;
import com.jkinvest.jkl.core.authority.dto.auth.LoginParamDTO;

/**
 * 授权认证统一接口.
 *
 * @author zuihou
 * @date 2020年03月31日10:21:21
 */
public interface TokenGranter {

    /**
     * 获取用户信息
     *
     * @param loginParam 授权参数
     * @return LoginDTO
     */
    R<AuthInfo> grant(LoginParamDTO loginParam);

}
