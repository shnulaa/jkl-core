package com.jkinvest.jkl.core.authority.service.core.impl;

import static com.jkinvest.jkl.core.common.constant.CacheKey.ORG;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableMap;
import com.jkinvest.jkl.base.base.service.SuperCacheServiceImpl;
import com.jkinvest.jkl.base.database.mybatis.conditions.Wraps;
import com.jkinvest.jkl.base.database.mybatis.conditions.query.LbqWrapper;
import com.jkinvest.jkl.base.utils.MapHelper;
import com.jkinvest.jkl.core.authority.dao.core.OrgMapper;
import com.jkinvest.jkl.core.authority.entity.auth.RoleOrg;
import com.jkinvest.jkl.core.authority.entity.core.Org;
import com.jkinvest.jkl.core.authority.service.auth.RoleOrgService;
import com.jkinvest.jkl.core.authority.service.core.OrgService;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 业务实现类
 * 组织
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@Service

public class OrgServiceImpl extends SuperCacheServiceImpl<OrgMapper, Org> implements OrgService {
    @Autowired
    private RoleOrgService roleOrgService;

    @Override
    protected String getRegion() {
        return ORG;
    }

    @Override
    public List<Org> findChildren(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        // MySQL 全文索引
        String applySql = String.format(" MATCH(tree_path) AGAINST('%s' IN BOOLEAN MODE) ", StringUtils.join(ids, " "));

        return super.list(Wraps.<Org>lbQ().in(Org::getId, ids).or(query -> query.apply(applySql)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean remove(List<Long> ids) {
        List<Org> list = this.findChildren(ids);
        List<Long> idList = list.stream().mapToLong(Org::getId).boxed().collect(Collectors.toList());

        boolean bool = !idList.isEmpty() ? super.removeByIds(idList) : true;

        // 删除自定义类型的数据权限范围
        roleOrgService.remove(Wraps.<RoleOrg>lbQ().in(RoleOrg::getOrgId, idList));
        return bool;
    }

    @Override
    public Map<Serializable, Object> findOrgByIds(Set<Serializable> ids) {
        List<Org> list = getOrgs(ids);

        //key 是 组织id， value 是org 对象
        ImmutableMap<Serializable, Object> typeMap = MapHelper.uniqueIndex(list, Org::getId, (org) -> org);
        return typeMap;
    }

    private List<Org> getOrgs(Set<Serializable> ids) {
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        List<Long> idList = ids.stream().mapToLong(Convert::toLong).boxed().collect(Collectors.toList());

        List<Org> list = null;
        if (idList.size() <= 1000) {
            list = idList.stream().map(this::getByIdCache).filter(Objects::nonNull).collect(Collectors.toList());
        } else {
            LbqWrapper<Org> query = Wraps.<Org>lbQ()
                    .in(Org::getId, idList)
                    .eq(Org::getStatus, true);
            list = super.list(query);

            if (!list.isEmpty()) {
                list.forEach(item -> {
                    String itemKey = key(item.getId());
                    cacheChannel.set(getRegion(), itemKey, item);
                });
            }
        }
        return list;
    }

    @Override
    public Map<Serializable, Object> findOrgNameByIds(Set<Serializable> ids) {
        List<Org> list = getOrgs(ids);

        //key 是 组织id， value 是org 对象
        ImmutableMap<Serializable, Object> typeMap = MapHelper.uniqueIndex(list, Org::getId, Org::getLabel);
        return typeMap;
    }

}
