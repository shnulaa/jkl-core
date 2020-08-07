package com.jkinvest.jkl.core.live.api.fallback;

import org.springframework.stereotype.Component;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.core.live.api.DemoFeign3Api;
import com.jkinvest.jkl.core.live.dto.RestTestDTO;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zuihou
 * @date 2020/6/10 下午10:47
 */
@Component
@Slf4j
public class DemoFeign3ApiFallback implements FallbackFactory<DemoFeign3Api> {
    @Override
    public DemoFeign3Api create(Throwable throwable) {
        return new DemoFeign3Api() {
            @Override
            public R<RestTestDTO> fallback(String key) {
                log.error("error", throwable);
                return R.timeout();
            }

            @Override
            public RestTestDTO fallback2(String key) {
                log.error("error", throwable);
                return null;
            }
        };
    }
}
