package com.jkinvest.jkl.core.order.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.core.order.api.fallback.DemoFeign3ApiFallback;
import com.jkinvest.jkl.core.order.dto.RestTestDTO;

/**
 * 测试日期类型API接口
 *
 * @author zuihou
 * @date 2019/07/24
 */
@FeignClient(name = "${zuihou.feign.demo-server:zuihou-demo-server}", path = "/restTemplate", fallbackFactory = DemoFeign3ApiFallback.class)
public interface DemoFeign3Api {

    @PostMapping("/fallback")
    R<RestTestDTO> fallback(@RequestParam("key") String key);

    @PostMapping("/fallback2")
    RestTestDTO fallback2(@RequestParam("key") String key);
}
