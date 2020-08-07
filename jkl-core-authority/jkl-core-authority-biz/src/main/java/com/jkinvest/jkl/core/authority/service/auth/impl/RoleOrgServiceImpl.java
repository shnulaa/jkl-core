package com.jkinvest.jkl.core.authority.service.auth.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jkinvest.jkl.base.base.service.SuperServiceImpl;
import com.jkinvest.jkl.base.database.mybatis.conditions.Wraps;
import com.jkinvest.jkl.core.authority.dao.auth.RoleOrgMapper;
import com.jkinvest.jkl.core.authority.entity.auth.RoleOrg;
import com.jkinvest.jkl.core.authority.service.auth.RoleOrgService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 业务实现类
 * 角色组织关系
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Slf4j
@Service

public class RoleOrgServiceImpl extends SuperServiceImpl<RoleOrgMapper, RoleOrg> implements RoleOrgService {
    @Override
    public List<Long> listOrgByRoleId(Long id) {
        List<RoleOrg> list = super.list(Wraps.<RoleOrg>lbQ().eq(RoleOrg::getRoleId, id));
        List<Long> orgList = list.stream().mapToLong(RoleOrg::getOrgId).boxed().collect(Collectors.toList());
        return orgList;
    }
}
