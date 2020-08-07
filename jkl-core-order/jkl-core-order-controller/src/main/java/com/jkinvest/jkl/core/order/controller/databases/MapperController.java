package com.jkinvest.jkl.core.order.controller.databases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.base.base.request.PageParams;
import com.jkinvest.jkl.base.database.mybatis.conditions.Wraps;
import com.jkinvest.jkl.base.database.mybatis.conditions.query.LbqWrapper;
import com.jkinvest.jkl.base.injection.core.InjectionCore;
import com.jkinvest.jkl.core.order.entity.Order;
import com.jkinvest.jkl.core.order.service.OrderService;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * 日期测试类
 *
 * @author zuihou
 * @date 2019/07/24
 */
@Slf4j
@RestController
@RequestMapping("/mapper")
@Api(value = "Mapper", tags = "Mapper新增方法演示")
public class MapperController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private InjectionCore injectionCore;

    @PostMapping("/saveBatchSomeColumn")
    public R saveBatchSomeColumn(@RequestBody Order data) {
        log.info("saveBatchSomeColumn={}", data);
        List<Order> list = new ArrayList<>();
        list.add(data);

        Order order = BeanUtil.toBean(data, Order.class);
        order.setName("new oder");
        list.add(order);
        orderService.saveBatchSomeColumn(list);

        return R.success(list);
    }

    @PostMapping("/updateAllById")
    public R updateAllById(@RequestBody Order data) {
        log.info("updateAllById={}", data);
        return R.success(orderService.updateAllById(data));
    }

    @PostMapping("/updateById")
    public R updateById(@RequestBody Order data) {
        log.info("updateById={}", data);
        return R.success(orderService.updateById(data));
    }

    @PostMapping("/likeTypeHandler")
    public R likeTypeHandler(@RequestBody Order data) {
        return R.success(orderService.find(data));
    }

    @PostMapping("/injection")
    public R injection(@RequestBody Order data) {
        List<Order> orders = orderService.find(data);
        injectionCore.injection(orders, false);
        return R.success(orders);
    }


    @PostMapping("/lbqWrapper1")
    public R lbqWrapper1(@RequestBody PageParams<Order> data) {
        LbqWrapper<Order> wraps = Wraps.lbQ(data.getModel());

        IPage<Order> page = orderService.page(data.buildPage(), wraps);
        injectionCore.injection(page, false);
        return R.success(page);
    }

    @PostMapping("/lbqWrapper2")
    public R lbqWrapper2(@RequestBody PageParams<Order> data) {
//        LbqWrapper<Order> wraps = Wraps.lbQ(data.getModel()).ignore(Order::setName);
        LbqWrapper<Order> wraps = Wraps.lbQ(data.getModel())
                .ignore(Order::setName)
                .likeRight(Order::getName, data.getModel().getName());

        IPage<Order> page = orderService.page(data.buildPage(), wraps);
        injectionCore.injection(page, false);
        return R.success(page);
    }

    @PostMapping("/lbqWrapper3")
    public R lbqWrapper3(@RequestBody PageParams<Order> data) {
        //cancelSkipEmpty() 不会影响 Wraps.lbQ(entity) 参数, 只会影响 .like .eq 等等
//        LbqWrapper<Order> wraps = Wraps.lbQ(data.getModel()).cancelSkipEmpty();

        LbqWrapper<Order> wraps = Wraps.<Order>lbQ()
                .cancelSkipEmpty()
                .like(Order::getName, data.getModel().getName())
                .eq(Order::getCode, data.getModel().getCode());

        IPage<Order> page = orderService.page(data.buildPage(), wraps);
        injectionCore.injection(page, false);
        return R.success(page);
    }

    @PostMapping("/lbqWrapper4")
    public R lbqWrapper4(@RequestBody PageParams<Order> data,
                         @RequestParam LocalDateTime start,
                         @RequestParam LocalDateTime end) {
        LbqWrapper<Order> wraps = Wraps.<Order>lbQ()
                .like(Order::getName, data.getModel().getName())
                .eq(Order::getCode, data.getModel().getCode())
                .geHeader(Order::getCreateTime, start)
                .leFooter(Order::getCreateTime, end);
//                .ge(Order::getCreateTime, start)
//                .le(Order::getCreateTime, end);

        IPage<Order> page = orderService.page(data.buildPage(), wraps);
        injectionCore.injection(page, false);
        return R.success(page);
    }

    @PostMapping("/dataScope")
    public R dataScope(@RequestBody PageParams<Order> data) {
        LbqWrapper<Order> wraps = Wraps.<Order>lbQ(data.getModel());
        return R.success(orderService.findPage(data.buildPage(), wraps));
    }


}
