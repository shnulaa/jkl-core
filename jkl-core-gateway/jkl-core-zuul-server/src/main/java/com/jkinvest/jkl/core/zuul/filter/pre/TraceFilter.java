package com.jkinvest.jkl.core.zuul.filter.pre;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.FORM_BODY_WRAPPER_FILTER_ORDER;

import org.slf4j.MDC;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.jkinvest.jkl.base.context.BaseContextConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import cn.hutool.core.util.IdUtil;

/**
 * 生成日志链路追踪id，并传入header中
 *
 * @author zuihou
 * @date 2020年03月09日18:01:33
 */
@Component
public class TraceFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FORM_BODY_WRAPPER_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        //根据配置控制是否开启过滤器
        return true;
    }

    @Override
    public Object run() {
        //链路追踪id
        String traceId = IdUtil.fastSimpleUUID();
        MDC.put(BaseContextConstants.LOG_TRACE_ID, traceId);
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(BaseContextConstants.TRACE_ID_HEADER, traceId);
        return null;
    }
}
