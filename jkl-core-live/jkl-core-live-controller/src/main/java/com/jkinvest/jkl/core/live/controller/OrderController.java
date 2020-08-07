package com.jkinvest.jkl.core.live.controller;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkinvest.jkl.base.base.controller.SuperController;
import com.jkinvest.jkl.base.security.annotation.PreAuth;
import com.jkinvest.jkl.core.live.dto.OrderPageDTO;
import com.jkinvest.jkl.core.live.dto.OrderSaveDTO;
import com.jkinvest.jkl.core.live.dto.OrderUpdateDTO;
import com.jkinvest.jkl.core.live.entity.Order;
import com.jkinvest.jkl.core.live.service.OrderService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 前端控制器
 * 订单
 * </p>
 *
 * @author zuihou
 * @date 2019-08-13
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/order")
@Api(value = "Order", tags = "订单")
@PreAuth(replace = "order:", enabled = false)
public class OrderController extends SuperController<OrderService, Long, Order, OrderPageDTO, OrderSaveDTO, OrderUpdateDTO> {

}
