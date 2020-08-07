package com.jkinvest.jkl.core.jobs.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.core.jobs.api.fallback.JobsTimingApiFallback;
import com.jkinvest.jkl.core.jobs.dto.XxlJobInfo;

/**
 * 定时API
 *
 * @author zuihou
 * @date 2019/07/05
 */
@FeignClient(name = "JobsTimingApi", url = "${zuihou.feign.jobs-server:http://127.0.0.1:8767}",
        path = "/zuihou-jobs-server", fallback = JobsTimingApiFallback.class)
public interface JobsTimingApi {

    /**
     * 定时发送接口
     *
     * @param xxlJobInfo
     * @return
     */
    @RequestMapping(value = "/jobinfo/addTimingTask", method = RequestMethod.POST)
    R<String> addTimingTask(@RequestBody XxlJobInfo xxlJobInfo);

}
