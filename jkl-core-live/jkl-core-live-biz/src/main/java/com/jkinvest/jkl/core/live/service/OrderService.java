package com.jkinvest.jkl.core.live.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jkinvest.jkl.base.base.service.SuperService;
import com.jkinvest.jkl.core.live.entity.Order;

/**
 * <p>
 * 业务接口
 * 订单
 * </p>
 *
 * @author zuihou
 * @date 2019-08-13
 */
public interface OrderService extends SuperService<Order> {

    List<Order> find(Order data);

    List<Order> findInjectionResult(Order data);

    IPage<Order> findPage(IPage page, Wrapper<Order> wrapper);
}
