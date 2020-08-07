package com.jkinvest.jkl.core.oauth.api.hystrix;

import org.springframework.stereotype.Component;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.core.oauth.api.ParameterApi;


/**
 * 熔断类
 *
 * @author zuihou
 * @date 2020年02月09日11:24:23
 */
@Component
public class ParameterApiFallback implements ParameterApi {
    @Override
    public R<String> getValue(String key, String defVal) {
        return R.timeout();
    }
}
