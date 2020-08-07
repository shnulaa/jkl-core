package com.jkinvest.jkl.core.tenant.service;

import com.jkinvest.jkl.base.base.service.SuperService;
import com.jkinvest.jkl.core.authority.dto.auth.UserUpdatePasswordDTO;
import com.jkinvest.jkl.core.tenant.dto.GlobalUserSaveDTO;
import com.jkinvest.jkl.core.tenant.dto.GlobalUserUpdateDTO;
import com.jkinvest.jkl.core.tenant.entity.GlobalUser;

/**
 * <p>
 * 业务接口
 * 全局账号
 * </p>
 *
 * @author zuihou
 * @date 2019-10-25
 */
public interface GlobalUserService extends SuperService<GlobalUser> {

    /**
     * 检测账号是否可用
     *
     * @param account
     * @return
     */
    Boolean check(String account);

    /**
     * 新建用户
     *
     * @param data
     * @return
     */
    GlobalUser save(GlobalUserSaveDTO data);


    /**
     * 修改
     *
     * @param data
     * @return
     */
    GlobalUser update(GlobalUserUpdateDTO data);

    /**
     * 修改密码
     *
     * @param model
     * @return
     */
    Boolean updatePassword(UserUpdatePasswordDTO model);
}
