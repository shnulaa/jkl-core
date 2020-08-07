package com.jkinvest.jkl.core.order.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.core.demo.entity.Product;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/08/12
 */
@FeignClient(name = "${zuihou.feign.demo-server:zuihou-demo-server}", path = "/seata")
public interface DemoTestApi {
    /**
     * 新增时发生异常
     *
     * @param data
     * @return
     */
    @PostMapping("/saveEx")
    R<Product> saveEx(@RequestBody Product data);

    /**
     * 新增
     *
     * @param data
     * @return
     */
    @PostMapping("/save")
    R<Product> save(@RequestBody Product data);
}
