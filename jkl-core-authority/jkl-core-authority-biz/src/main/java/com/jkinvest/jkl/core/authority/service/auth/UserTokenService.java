package com.jkinvest.jkl.core.authority.service.auth;

import com.jkinvest.jkl.base.base.service.SuperService;
import com.jkinvest.jkl.core.authority.entity.auth.UserToken;

/**
 * <p>
 * 业务接口
 * token
 * </p>
 *
 * @author zuihou
 * @date 2020-04-02
 */
public interface UserTokenService extends SuperService<UserToken> {
    /**
     * 重置用户登录
     *
     * @return
     */
    boolean reset();
}
