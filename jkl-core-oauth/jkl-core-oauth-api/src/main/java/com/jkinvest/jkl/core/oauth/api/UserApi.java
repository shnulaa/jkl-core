package com.jkinvest.jkl.core.oauth.api;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jkinvest.jkl.core.oauth.api.hystrix.UserApiFallback;

/**
 * 用户
 *
 * @author zuihou
 * @date 2019/07/02
 */
@FeignClient(name = "${zuihou.feign.oauth-server:jkl-core-oauth-server}", fallback = UserApiFallback.class
        , path = "/user", qualifier = "userApi")
public interface UserApi {
    /**
     * 刷新token
     *
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "/ds/{id}", method = RequestMethod.GET)
    Map<String, Object> getDataScopeById(@PathVariable("id") Long id);

    /**
     * 根据id查询用户
     *
     * @param ids
     * @return
     */
    @GetMapping("/findUserByIds")
    Map<Serializable, Object> findUserByIds(@RequestParam(value = "ids") Set<Serializable> ids);

    /**
     * 根据id查询用户名称
     *
     * @param ids
     * @return
     */
    @GetMapping("/findUserNameByIds")
    Map<Serializable, Object> findUserNameByIds(@RequestParam(value = "ids") Set<Serializable> ids);
}
