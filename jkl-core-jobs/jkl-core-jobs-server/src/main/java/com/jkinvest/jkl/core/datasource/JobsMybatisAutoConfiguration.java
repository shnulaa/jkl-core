package com.jkinvest.jkl.core.datasource;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.jkinvest.jkl.base.database.datasource.BaseMybatisConfiguration;
import com.jkinvest.jkl.base.database.properties.DatabaseProperties;

import lombok.extern.slf4j.Slf4j;

/**
 * 配置一些拦截器
 *
 * @author zuihou
 * @createTime 2017-11-18 0:34
 */
@Configuration
@Slf4j
@EnableConfigurationProperties({DatabaseProperties.class})
public class JobsMybatisAutoConfiguration extends BaseMybatisConfiguration {


    public JobsMybatisAutoConfiguration(DatabaseProperties databaseProperties) {
        super(databaseProperties);

    }

}
