package com.jkinvest.jkl.core.authority.service.auth;

import java.util.List;

import com.jkinvest.jkl.base.base.service.SuperService;
import com.jkinvest.jkl.core.authority.dto.auth.RoleAuthoritySaveDTO;
import com.jkinvest.jkl.core.authority.dto.auth.UserRoleSaveDTO;
import com.jkinvest.jkl.core.authority.entity.auth.RoleAuthority;

/**
 * <p>
 * 业务接口
 * 角色的资源
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
public interface RoleAuthorityService extends SuperService<RoleAuthority> {

    /**
     * 给用户分配角色
     *
     * @param userRole
     * @return
     */
    boolean saveUserRole(UserRoleSaveDTO userRole);

    /**
     * 给角色重新分配 权限（资源/菜单）
     *
     * @param roleAuthoritySaveDTO
     * @return
     */
    boolean saveRoleAuthority(RoleAuthoritySaveDTO roleAuthoritySaveDTO);

    /**
     * 根据权限id 删除
     *
     * @param ids
     * @return
     */
    boolean removeByAuthorityId(List<Long> ids);
}
