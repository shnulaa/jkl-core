package com.jkinvest.jkl.core.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.jkinvest.jkl.base.base.mapper.SuperMapper;
import com.jkinvest.jkl.base.database.mybatis.auth.DataScope;
import com.jkinvest.jkl.core.order.entity.Order;

/**
 * <p>
 * Mapper 接口
 * 订单
 * </p>
 *
 * @author zuihou
 * @date 2019-08-13
 */
@Repository
public interface OrderMapper extends SuperMapper<Order> {

    List<Order> find(@Param("data") Order data);


    IPage<Order> findPage(IPage page, @Param(Constants.WRAPPER) Wrapper<Order> wrapper, DataScope dataScope);
}
