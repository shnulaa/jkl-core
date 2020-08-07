package com.jkinvest.jkl.core.oauth.api.hystrix;

import org.springframework.stereotype.Component;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.base.log.entity.OptLogDTO;
import com.jkinvest.jkl.core.oauth.api.LogApi;

/**
 * 日志操作 熔断
 *
 * @author zuihou
 * @date 2019/07/02
 */
@Component
public class LogApiHystrix implements LogApi {
    @Override
    public R<OptLogDTO> save(OptLogDTO log) {
        return R.timeout();
    }
}
