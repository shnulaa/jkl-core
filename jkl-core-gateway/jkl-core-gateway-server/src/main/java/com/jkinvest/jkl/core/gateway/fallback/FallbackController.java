package com.jkinvest.jkl.core.gateway.fallback;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.base.exception.code.ExceptionCode;

import reactor.core.publisher.Mono;

/**
 * 响应超时熔断处理器
 *
 * @author zuihou
 */
@RestController
public class FallbackController {

    @RequestMapping("/fallback")
    public Mono<R> fallback() {
        return Mono.just(R.fail(ExceptionCode.SYSTEM_TIMEOUT));
    }
}
