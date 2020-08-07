package com.jkinvest.jkl.core.authority.service.auth.impl;


import static com.jkinvest.jkl.core.common.constant.BizConstant.INIT_ROLE_CODE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkinvest.jkl.base.base.service.SuperServiceImpl;
import com.jkinvest.jkl.base.database.mybatis.conditions.Wraps;
import com.jkinvest.jkl.base.exception.BizException;
import com.jkinvest.jkl.core.authority.dao.auth.RoleMapper;
import com.jkinvest.jkl.core.authority.dao.auth.UserRoleMapper;
import com.jkinvest.jkl.core.authority.entity.auth.Role;
import com.jkinvest.jkl.core.authority.entity.auth.UserRole;
import com.jkinvest.jkl.core.authority.service.auth.UserRoleService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 业务实现类
 * 角色分配
 * 账号角色绑定
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Slf4j
@Service

public class UserRoleServiceImpl extends SuperServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public boolean initAdmin(Long userId) {
        Role role = roleMapper.selectOne(Wraps.<Role>lbQ().eq(Role::getCode, INIT_ROLE_CODE));
        if (role == null) {
            throw BizException.wrap("初始化用户角色失败, 无法查询到内置角色:%s", INIT_ROLE_CODE);
        }
        UserRole userRole = UserRole.builder()
                .userId(userId).roleId(role.getId())
                .build();

        return super.save(userRole);
    }
}
