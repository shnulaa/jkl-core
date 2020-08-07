package com.jkinvest.jkl.core.authority.service.common;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import com.jkinvest.jkl.base.base.service.SuperCacheService;
import com.jkinvest.jkl.core.authority.entity.common.DictionaryItem;

/**
 * <p>
 * 业务接口
 * 字典项
 * </p>
 *
 * @author zuihou
 * @date 2019-07-02
 */
public interface DictionaryItemService extends SuperCacheService<DictionaryItem> {
    /**
     * 根据类型查询字典
     *
     * @param types
     * @return
     */
    Map<String, Map<String, String>> map(String[] types);

    /**
     * 根据类型编码查询字典项
     * @param codes
     * @return
     */
    Map<Serializable, Object> findDictionaryItem(Set<Serializable> codes);
}
