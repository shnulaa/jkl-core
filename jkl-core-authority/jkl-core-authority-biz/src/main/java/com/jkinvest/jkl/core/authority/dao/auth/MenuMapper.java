package com.jkinvest.jkl.core.authority.dao.auth;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jkinvest.jkl.base.base.mapper.SuperMapper;
import com.jkinvest.jkl.core.authority.entity.auth.Menu;

/**
 * <p>
 * Mapper 接口
 * 菜单
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Repository
public interface MenuMapper extends SuperMapper<Menu> {

    /**
     * 查询用户可用菜单
     *
     * @param userId
     * @return
     */
    List<Menu> findVisibleMenu(@Param("userId") Long userId);
}
