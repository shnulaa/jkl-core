package com.jkinvest.jkl.core.demo.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.core.demo.entity.Product;
import com.jkinvest.jkl.core.demo.service.ProductService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * 分布式事务测试类
 *
 * @author zuihou
 * @date 2019/08/12
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/seata")
@Api(value = "SeataTxController", tags = "分布式事务测试类")
public class SeataTxController {

    @Autowired
    private ProductService productService;


    @PostMapping("/save")
    public R<Product> save(@RequestBody Product data) {
        log.info("data={}", data);
        productService.save(data);
        return R.success(data);
    }

    @PostMapping("/saveEx")
    public R<Product> saveEx(@RequestBody Product data) {
        log.info("data={}", data);
        productService.saveEx(data);
        return R.success(data);
    }
}
