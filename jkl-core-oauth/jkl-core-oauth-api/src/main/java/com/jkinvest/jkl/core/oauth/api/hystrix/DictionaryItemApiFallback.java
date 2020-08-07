package com.jkinvest.jkl.core.oauth.api.hystrix;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.jkinvest.jkl.core.oauth.api.DictionaryItemApi;

/**
 * 数据字典项 查询
 *
 * @author zuihou
 * @date 2019/07/26
 */
@Component
public class DictionaryItemApiFallback implements DictionaryItemApi {

    @Override
    public Map<Serializable, Object> findDictionaryItem(Set<Serializable> codes) {
        return new HashMap<>(1);
    }
}
