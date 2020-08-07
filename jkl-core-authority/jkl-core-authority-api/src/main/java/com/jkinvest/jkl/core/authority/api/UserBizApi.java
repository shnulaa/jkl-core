package com.jkinvest.jkl.core.authority.api;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.core.authority.api.hystrix.UserBizApiFallback;

/**
 * 用户
 *
 * @author zuihou
 * @date 2019/07/02
 */
@FeignClient(name = "${zuihou.feign.authority-server:zuihou-authority-server}", fallback = UserBizApiFallback.class
        , path = "/user", qualifier = "userBizApi")
public interface UserBizApi {

    /**
     * 查询所有的用户id
     *
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    R<List<Long>> findAllUserId();
}
