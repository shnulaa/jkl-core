package com.jkinvest.jkl.core.file.config;

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
public class FileWebConfiguration extends BaseConfig {
    /**
     * zuihou.log.enabled = true 并且 zuihou.log.type=DB时实例该类
     *
     * @param logApi
     * @return
     */
    @Bean
    @ConditionalOnExpression("${zuihou.log.enabled:true} && 'DB'.equals('${zuihou.log.type:LOGGER}')")
    public SysLogListener sysLogListener(LogApi logApi) {
        return new SysLogListener((log) -> logApi.save(log));
    }
}
