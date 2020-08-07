package com.jkinvest.jkl.core.oauth.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.base.log.entity.OptLogDTO;
import com.jkinvest.jkl.core.oauth.api.hystrix.LogApiHystrix;

/**
 * 操作日志保存 API
 *
 * @author zuihou
 * @date 2019/07/02
 */
@FeignClient(name = "${zuihou.feign.oauth-server:jkl-core-oauth-server}", fallback = LogApiHystrix.class, qualifier = "logApi")
public interface LogApi {

    /**
     * 保存日志
     *
     * @param log 日志
     * @return
     */
    @RequestMapping(value = "/optLog", method = RequestMethod.POST)
    R<OptLogDTO> save(@RequestBody OptLogDTO log);

}
