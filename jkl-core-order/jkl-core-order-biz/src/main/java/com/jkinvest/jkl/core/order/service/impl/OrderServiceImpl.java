package com.jkinvest.jkl.core.order.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jkinvest.jkl.base.base.service.SuperServiceImpl;
import com.jkinvest.jkl.base.database.mybatis.auth.DataScope;
import com.jkinvest.jkl.base.injection.annonation.InjectionResult;
import com.jkinvest.jkl.base.injection.properties.InjectionProperties;
import com.jkinvest.jkl.core.order.dao.OrderMapper;
import com.jkinvest.jkl.core.order.entity.Order;
import com.jkinvest.jkl.core.order.service.OrderService;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 业务实现类
 * 订单
 * </p>
 *
 * @author zuihou
 * @date 2019-08-13
 */
@Slf4j
@Service

public class OrderServiceImpl extends SuperServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private InjectionProperties ips;

    @Override
    public List<Order> find(Order data) {
        return baseMapper.find(data);
    }

    @Override
    @InjectionResult
    public List<Order> findInjectionResult(Order data) {
        return baseMapper.find(data);
    }

    @Override
    public IPage<Order> findPage(IPage page, Wrapper<Order> wrapper) {
        return baseMapper.findPage(page, wrapper, new DataScope());
    }


    public Map<Serializable, Object> findDictionaryItem(Set<Serializable> codes) {
        if (codes.isEmpty()) {
            return Collections.emptyMap();
        }
        Set<String> types = codes.stream().filter(Objects::nonNull)
                .map((item) -> StrUtil.split(String.valueOf(item), ips.getDictSeparator())[0]).collect(Collectors.toSet());
        Set<String> newCodes = codes.stream().filter(Objects::nonNull)
                .map((item) -> StrUtil.split(String.valueOf(item), ips.getDictSeparator())[1]).collect(Collectors.toSet());

        // 1. 根据 字典编码查询可用的字典列表
//        LbqWrapper<DictionaryItem> query = Wraps.<DictionaryItem>lbQ()
//                .in(DictionaryItem::getDictionaryType, types)
//                .in(DictionaryItem::getCode, newCodes)
//                .eq(DictionaryItem::getStatus, true)
//                .orderByAsc(DictionaryItem::getSortValue);
//        List<DictionaryItem> list = super.list(query);
//
//        // 2. 将 list 转换成 Map，Map的key是字典编码，value是字典名称
//        ImmutableMap<String, String> typeMap = MapHelper.uniqueIndex(list,
//                (item) -> StrUtil.join(ips.getDictSeparator(), item.getDictionaryType(), item.getCode())
//                , DictionaryItem::getName);

        // 3. 将 Map<String, String> 转换成 Map<Serializable, Object>
        Map<Serializable, Object> typeCodeNameMap = new HashMap<>();
        typeCodeNameMap.put("EDUCATION" + ips.getDictSeparator() + "COLLEGE", "本科");
        typeCodeNameMap.put("EDUCATION" + ips.getDictSeparator() + "SUOSHI", "硕士");
        typeCodeNameMap.put("NATION" + ips.getDictSeparator() + "mz_hanz", "汉族");
        typeCodeNameMap.put("NATION" + ips.getDictSeparator() + "mz_zz", "壮族");

        return typeCodeNameMap;
    }
}
