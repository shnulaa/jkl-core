package com.jkinvest.jkl.core.authority.strategy.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jkinvest.jkl.core.authority.entity.core.Org;
import com.jkinvest.jkl.core.authority.service.core.OrgService;
import com.jkinvest.jkl.core.authority.strategy.AbstractDataScopeHandler;

/**
 * 所有
 *
 * @author zuihou
 * @version 1.0
 * @date 2019-06-08 16:27
 */
@Component("ALL")
public class AllDataScope implements AbstractDataScopeHandler {

    @Autowired
    private OrgService orgService;

    @Override
    public List<Long> getOrgIds(List<Long> orgList, Long userId) {
        List<Org> list = orgService.lambdaQuery().select(Org::getId).list();
        return list.stream().map(Org::getId).collect(Collectors.toList());
    }


}
