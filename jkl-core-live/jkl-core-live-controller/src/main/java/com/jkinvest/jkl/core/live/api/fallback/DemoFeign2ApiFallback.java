package com.jkinvest.jkl.core.live.api.fallback;

import org.springframework.stereotype.Component;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.core.live.api.DemoFeign2Api;
import com.jkinvest.jkl.core.live.dto.RestTestDTO;

/**
 * @author zuihou
 * @date 2020/6/10 下午10:46
 */
@Component
public class DemoFeign2ApiFallback implements DemoFeign2Api {
    @Override
    public R<RestTestDTO> fallback(String key) {
        return R.timeout();
    }
}
