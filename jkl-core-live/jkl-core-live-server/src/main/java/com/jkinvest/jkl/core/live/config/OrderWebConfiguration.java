package com.jkinvest.jkl.core.live.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jkinvest.jkl.base.boot.config.BaseConfig;
import com.jkinvest.jkl.base.log.event.SysLogListener;
import com.jkinvest.jkl.core.oauth.api.LogApi;

/**
 * @author zuihou
 * @createTime 2017-12-15 14:42
 */
@Configuration
public class OrderWebConfiguration extends BaseConfig {

    @Bean
    @ConditionalOnExpression("${zuihou.log.enabled:true} && 'DB'.equals('${zuihou.log.type:LOGGER}')")
    public SysLogListener sysLogListener(LogApi logApi) {
        return new SysLogListener((log) -> logApi.save(log));
    }
}
