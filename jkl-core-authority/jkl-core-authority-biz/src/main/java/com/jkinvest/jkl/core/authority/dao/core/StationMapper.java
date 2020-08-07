package com.jkinvest.jkl.core.authority.dao.core;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.jkinvest.jkl.base.base.mapper.SuperMapper;
import com.jkinvest.jkl.base.database.mybatis.auth.DataScope;
import com.jkinvest.jkl.core.authority.entity.core.Station;

/**
 * <p>
 * Mapper 接口
 * 岗位
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Repository
public interface StationMapper extends SuperMapper<Station> {
    /**
     * 分页查询岗位信息（含角色）
     *
     * @param page
     * @param queryWrapper
     * @param dataScope
     * @return
     */
    IPage<Station> findStationPage(IPage page, @Param(Constants.WRAPPER) Wrapper<Station> queryWrapper, DataScope dataScope);
}
