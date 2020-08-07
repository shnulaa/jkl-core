package com.jkinvest.jkl.core.executor.jobhandler;

import java.util.List;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import com.jkinvest.jkl.base.context.BaseContextConstants;
import com.jkinvest.jkl.base.database.mybatis.conditions.Wraps;
import com.jkinvest.jkl.base.database.mybatis.conditions.query.LbqWrapper;
import com.jkinvest.jkl.core.tenant.entity.Tenant;
import com.jkinvest.jkl.core.tenant.enumeration.TenantStatusEnum;
import com.jkinvest.jkl.core.tenant.service.TenantService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.log.XxlJobLogger;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import groovy.util.logging.Slf4j;

@Slf4j
public abstract class GlobalTenantJobHandler extends IJobHandler {

    @Autowired
    private TenantService tenantService;

    @Override
    public ReturnT<String> execute2(String param) throws Exception {
        //记录日志的方法推荐使用这个:XxlJobLogger.log ，因为这个记录的日志，可以在zuihou-jobs-server管理后台查看
        XxlJobLogger.log("执行结果--->param={} ", param);

        String traceId = IdUtil.fastSimpleUUID();
        MDC.put(BaseContextConstants.LOG_TRACE_ID, StrUtil.isEmpty(traceId) ? StrUtil.EMPTY : traceId);

        LbqWrapper<Tenant> wrapper = Wraps.<Tenant>lbQ()
                .eq(Tenant::getStatus, TenantStatusEnum.NORMAL);

        List<Tenant> list = tenantService.list(wrapper);

        list.forEach((tenant) -> {
            MDC.put(BaseContextConstants.JWT_KEY_TENANT, tenant.getCode());
            executeBiz(tenant, param);
        });

        return SUCCESS;
    }

    public abstract ReturnT<String> executeBiz(Tenant tenant, String param);
}
