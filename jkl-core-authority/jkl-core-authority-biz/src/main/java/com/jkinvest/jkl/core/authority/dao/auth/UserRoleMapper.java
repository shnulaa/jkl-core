package com.jkinvest.jkl.core.authority.dao.auth;

import org.springframework.stereotype.Repository;

import com.jkinvest.jkl.base.base.mapper.SuperMapper;
import com.jkinvest.jkl.core.authority.entity.auth.UserRole;

/**
 * <p>
 * Mapper 接口
 * 角色分配
 * 账号角色绑定
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Repository
public interface UserRoleMapper extends SuperMapper<UserRole> {

}
