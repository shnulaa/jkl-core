package com.jkinvest.jkl.core.oauth.api;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.core.oauth.api.hystrix.RoleApiFallback;

/**
 * 角色API
 *
 * @author zuihou
 * @date 2019/08/02
 */
@FeignClient(name = "${zuihou.feign.oauth-server:jkl-core-oauth-server}", path = "/role", fallback = RoleApiFallback.class)
public interface RoleApi {
    /**
     * 根据角色编码，查找用户id
     *
     * @param codes 角色编码
     * @return
     */
    @GetMapping("/codes")
    R<List<Long>> findUserIdByCode(@RequestParam(value = "codes") String[] codes);
}
