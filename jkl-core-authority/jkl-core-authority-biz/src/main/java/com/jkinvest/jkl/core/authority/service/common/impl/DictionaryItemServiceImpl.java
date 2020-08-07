package com.jkinvest.jkl.core.authority.service.common.impl;

import static com.jkinvest.jkl.core.common.constant.CacheKey.DICTIONARY_ITEM;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;
import com.jkinvest.jkl.base.base.service.SuperCacheServiceImpl;
import com.jkinvest.jkl.base.database.mybatis.conditions.Wraps;
import com.jkinvest.jkl.base.database.mybatis.conditions.query.LbqWrapper;
import com.jkinvest.jkl.base.injection.properties.InjectionProperties;
import com.jkinvest.jkl.base.utils.MapHelper;
import com.jkinvest.jkl.core.authority.dao.common.DictionaryItemMapper;
import com.jkinvest.jkl.core.authority.entity.common.DictionaryItem;
import com.jkinvest.jkl.core.authority.service.common.DictionaryItemService;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 业务实现类
 * 字典项
 * </p>
 *
 * @author zuihou
 * @date 2019-07-02
 */
@Slf4j
@Service

public class DictionaryItemServiceImpl extends SuperCacheServiceImpl<DictionaryItemMapper, DictionaryItem> implements DictionaryItemService {

    @Autowired
    private InjectionProperties ips;

    @Override
    protected String getRegion() {
        return DICTIONARY_ITEM;
    }

    @Override
    public Map<String, Map<String, String>> map(String[] types) {
        if (ArrayUtil.isEmpty(types)) {
            return Collections.emptyMap();
        }
        LbqWrapper<DictionaryItem> query = Wraps.<DictionaryItem>lbQ()
                .in(DictionaryItem::getDictionaryType, types)
                .eq(DictionaryItem::getStatus, true)
                .orderByAsc(DictionaryItem::getSortValue);
        List<DictionaryItem> list = super.list(query);

        //key 是类型
        Map<String, List<DictionaryItem>> typeMap = list.stream().collect(groupingBy(DictionaryItem::getDictionaryType, LinkedHashMap::new, toList()));

        //需要返回的map
        Map<String, Map<String, String>> typeCodeNameMap = new LinkedHashMap<>(typeMap.size());

        typeMap.forEach((key, items) -> {
            ImmutableMap<String, String> itemCodeMap = MapHelper.uniqueIndex(items, DictionaryItem::getCode, DictionaryItem::getName);
            typeCodeNameMap.put(key, itemCodeMap);
        });
        return typeCodeNameMap;
    }

    @Override
    public Map<Serializable, Object> findDictionaryItem(Set<Serializable> codes) {
        if (codes.isEmpty()) {
            return Collections.emptyMap();
        }
        Set<String> types = codes.stream().filter(Objects::nonNull)
                .map((item) -> StrUtil.split(String.valueOf(item), ips.getDictSeparator())[0]).collect(Collectors.toSet());
        Set<String> newCodes = codes.stream().filter(Objects::nonNull)
                .map((item) -> StrUtil.split(String.valueOf(item), ips.getDictSeparator())[1]).collect(Collectors.toSet());

        // 1. 根据 字典编码查询可用的字典列表
        LbqWrapper<DictionaryItem> query = Wraps.<DictionaryItem>lbQ()
                .in(DictionaryItem::getDictionaryType, types)
                .in(DictionaryItem::getCode, newCodes)
                .eq(DictionaryItem::getStatus, true)
                .orderByAsc(DictionaryItem::getSortValue);
        List<DictionaryItem> list = super.list(query);

        // 2. 将 list 转换成 Map，Map的key是字典编码，value是字典名称
        ImmutableMap<String, String> typeMap = MapHelper.uniqueIndex(list,
                (item) -> StrUtil.join(ips.getDictSeparator(), item.getDictionaryType(), item.getCode())
                , DictionaryItem::getName);

        // 3. 将 Map<String, String> 转换成 Map<Serializable, Object>
        Map<Serializable, Object> typeCodeNameMap = new HashMap<>(typeMap.size());
        typeMap.forEach((key, value) -> typeCodeNameMap.put(key, value));
        return typeCodeNameMap;
    }
}
