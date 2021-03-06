package com.jkinvest.jkl.core.order.config.datasource;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.jkinvest.jkl.base.database.datasource.BaseMybatisConfiguration;
import com.jkinvest.jkl.base.database.mybatis.auth.DataScopeInterceptor;
import com.jkinvest.jkl.base.database.properties.DatabaseProperties;
import com.jkinvest.jkl.base.utils.SpringUtils;
import com.jkinvest.jkl.core.oauth.api.UserApi;

import lombok.extern.slf4j.Slf4j;

/**
 * 配置一些 Mybatis 常用重用拦截器
 *
 * @author zuihou
 * @createTime 2017-11-18 0:34
 */
@Configuration
@Slf4j
@EnableConfigurationProperties({DatabaseProperties.class})
public class OrderMybatisAutoConfiguration extends BaseMybatisConfiguration {


    public OrderMybatisAutoConfiguration(DatabaseProperties databaseProperties) {
        super(databaseProperties);

    }

    /**
     * 数据权限插件
     *
     * @return DataScopeInterceptor
     */
    @Order(10)
    @Bean
    @ConditionalOnProperty(prefix = DatabaseProperties.PREFIX, name = "isDataScope", havingValue = "true", matchIfMissing = true)
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor((userId) -> SpringUtils.getBean(UserApi.class).getDataScopeById(userId));
    }

}
