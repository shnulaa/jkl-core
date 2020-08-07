package com.jkinvest.jkl.core.tenant.service.impl;

import static com.jkinvest.jkl.base.utils.BizAssert.isFalse;
import static com.jkinvest.jkl.core.common.constant.CacheKey.TENANT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jkinvest.jkl.base.base.service.SuperCacheServiceImpl;
import com.jkinvest.jkl.base.database.mybatis.conditions.Wraps;
import com.jkinvest.jkl.base.utils.BeanPlusUtil;
import com.jkinvest.jkl.core.tenant.dao.TenantMapper;
import com.jkinvest.jkl.core.tenant.dto.TenantSaveDTO;
import com.jkinvest.jkl.core.tenant.entity.Tenant;
import com.jkinvest.jkl.core.tenant.enumeration.TenantStatusEnum;
import com.jkinvest.jkl.core.tenant.enumeration.TenantTypeEnum;
import com.jkinvest.jkl.core.tenant.service.TenantService;
import com.jkinvest.jkl.core.tenant.strategy.InitSystemContext;

import cn.hutool.core.convert.Convert;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 业务实现类
 * 企业
 * </p>
 *
 * @author zuihou
 * @date 2019-10-24
 */
@Slf4j
@Service

public class TenantServiceImpl extends SuperCacheServiceImpl<TenantMapper, Tenant> implements TenantService {

    @Autowired
    private InitSystemContext initSystemContext;

    @Override
    protected String getRegion() {
        return TENANT;
    }

    @Override
    public Tenant getByCode(String tenant) {
        return super.getOne(Wraps.<Tenant>lbQ().eq(Tenant::getCode, tenant));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tenant save(TenantSaveDTO data) {
        // defaults 库
        isFalse(check(data.getCode()), "编码重复，请重新输入");

        // 1， 保存租户 (默认库)
        Tenant tenant = BeanPlusUtil.toBean(data, Tenant.class);
        tenant.setStatus(TenantStatusEnum.NORMAL);
        tenant.setType(TenantTypeEnum.CREATE);
        // defaults 库
        save(tenant);

        // 3, 初始化库，表, 数据  考虑异步完成 // 租户库
        initSystemContext.init(tenant.getCode());
        return tenant;
    }

    @Override
    public boolean check(String tenantCode) {
        return super.count(Wraps.<Tenant>lbQ().eq(Tenant::getCode, tenantCode)) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(List<Long> ids) {
        List<String> tenantCodeList = listObjs(Wraps.<Tenant>lbQ().select(Tenant::getCode).in(Tenant::getId, ids), Convert::toStr);
        if (tenantCodeList.isEmpty()) {
            return true;
        }
        removeByIds(ids);

        return initSystemContext.delete(tenantCodeList);
    }
}
