package com.jkinvest.jkl.core.authority.strategy.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jkinvest.jkl.base.model.RemoteData;
import com.jkinvest.jkl.core.authority.entity.auth.User;
import com.jkinvest.jkl.core.authority.entity.core.Org;
import com.jkinvest.jkl.core.authority.service.auth.UserService;
import com.jkinvest.jkl.core.authority.service.core.OrgService;
import com.jkinvest.jkl.core.authority.strategy.AbstractDataScopeHandler;

/**
 * 本级以及子级
 *
 * @author zuihou
 * @version 1.0
 * @date 2019-06-08 16:30
 */
@Component("THIS_LEVEL_CHILDREN")
public class ThisLevelChildrenDataScope implements AbstractDataScopeHandler {
    @Autowired
    private UserService userService;
    @Autowired
    private OrgService orgService;

    @Override
    public List<Long> getOrgIds(List<Long> orgList, Long userId) {
        User user = userService.getById(userId);
        if (user == null) {
            return Collections.emptyList();
        }
        Long orgId = RemoteData.getKey(user.getOrg());
        List<Org> children = orgService.findChildren(Arrays.asList(orgId));
        return children.stream().mapToLong(Org::getId).boxed().collect(Collectors.toList());
    }
}
