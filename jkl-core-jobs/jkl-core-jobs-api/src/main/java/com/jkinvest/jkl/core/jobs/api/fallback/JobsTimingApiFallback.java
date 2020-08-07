package com.jkinvest.jkl.core.jobs.api.fallback;

import org.springframework.stereotype.Component;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.core.jobs.api.JobsTimingApi;
import com.jkinvest.jkl.core.jobs.dto.XxlJobInfo;

/**
 * 定时API 熔断
 *
 * @author zuihou
 * @date 2019/07/16
 */
@Component
public class JobsTimingApiFallback implements JobsTimingApi {
    @Override
    public R<String> addTimingTask(XxlJobInfo xxlJobInfo) {
        return R.timeout();
    }
}
