package com.jkinvest.jkl.core.oauth.api.hystrix;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.core.oauth.api.RoleApi;

/**
 * 角色查询API
 *
 * @author zuihou
 * @date 2019/08/02
 */
@Component
public class RoleApiFallback implements RoleApi {
    @Override
    public R<List<Long>> findUserIdByCode(String[] codes) {
        return R.timeout();
    }
}
