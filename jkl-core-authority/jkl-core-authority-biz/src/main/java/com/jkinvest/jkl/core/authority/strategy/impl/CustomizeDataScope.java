package com.jkinvest.jkl.core.authority.strategy.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jkinvest.jkl.base.exception.BizException;
import com.jkinvest.jkl.base.exception.code.ExceptionCode;
import com.jkinvest.jkl.core.authority.entity.core.Org;
import com.jkinvest.jkl.core.authority.service.core.OrgService;
import com.jkinvest.jkl.core.authority.strategy.AbstractDataScopeHandler;

/**
 * 自定义模式
 *
 * @author zuihou
 * @version 1.0
 * @date 2019-06-08 16:31
 */
@Component("CUSTOMIZE")
public class CustomizeDataScope implements AbstractDataScopeHandler {

    @Autowired
    private OrgService orgService;

    @Override
    public List<Long> getOrgIds(List<Long> orgList, Long userId) {
        if (orgList == null || orgList.isEmpty()) {
            throw new BizException(ExceptionCode.BASE_VALID_PARAM.getCode(), "自定义数据权限类型时，组织不能为空");
        }
        List<Org> children = orgService.findChildren(orgList);
        return children.stream().mapToLong(Org::getId).boxed().collect(Collectors.toList());
    }
}
