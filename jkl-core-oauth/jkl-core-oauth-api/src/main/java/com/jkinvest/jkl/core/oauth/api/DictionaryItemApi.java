package com.jkinvest.jkl.core.oauth.api;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jkinvest.jkl.core.oauth.api.hystrix.DictionaryItemApiFallback;

/**
 * 数据字典API
 *
 * @author zuihou
 * @date 2019/07/26
 */
@FeignClient(name = "${zuihou.feign.oauth-server:jkl-core-oauth-server}", path = "dictionaryItem",
        qualifier = "dictionaryItemApi", fallback = DictionaryItemApiFallback.class)
public interface DictionaryItemApi {

    /**
     * 根据 code 查询字典
     *
     * @param codes
     * @return
     */
    @GetMapping("/findDictionaryItem")
    Map<Serializable, Object> findDictionaryItem(@RequestParam(value = "codes") Set<Serializable> codes);
}
