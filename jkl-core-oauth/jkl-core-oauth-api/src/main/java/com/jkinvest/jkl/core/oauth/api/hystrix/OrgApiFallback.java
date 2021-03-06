package com.jkinvest.jkl.core.oauth.api.hystrix;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.jkinvest.jkl.core.oauth.api.OrgApi;

/**
 * 熔断类
 *
 * @author zuihou
 * @date 2020年02月09日11:24:23
 */
@Component
public class OrgApiFallback implements OrgApi {
    @Override
    public Map<Serializable, Object> findOrgByIds(Set<Serializable> ids) {
        return Collections.emptyMap();
    }

    @Override
    public Map<Serializable, Object> findOrgNameByIds(Set<Serializable> ids) {
        return Collections.emptyMap();
    }
}
