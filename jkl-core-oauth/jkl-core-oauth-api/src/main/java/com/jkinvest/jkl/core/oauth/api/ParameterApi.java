package com.jkinvest.jkl.core.oauth.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.core.oauth.api.hystrix.ParameterApiFallback;

/**
 * 参数API
 *
 * @author zuihou
 * @date 2020年04月02日22:53:56
 */
@FeignClient(name = "${zuihou.feign.oauth-server:jkl-core-oauth-server}", path = "/parameter",
        qualifier = "parameterApi", fallback = ParameterApiFallback.class)
public interface ParameterApi {

    /**
     * 根据参数键查询参数值
     *
     * @param key    参数键
     * @param defVal 参数值
     * @return
     */
    @GetMapping("/value")
    R<String> getValue(@RequestParam(value = "key") String key, @RequestParam(value = "defVal") String defVal);
}
