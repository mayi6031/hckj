package com.hckj.trade.microservice.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


/**
 * 数据源及其druid的配置
 * rp
 */
@Configuration
public class DatasourceConfig {
    private Logger logger = LoggerFactory.getLogger(DatasourceConfig.class);

    @Value("${trade.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${trade.datasource.url}")
    private String dbUrl;

    @Value("${trade.datasource.username}")
    private String username;

    @Value("${trade.datasource.password}")
    private String password;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.removeAbandoned}")
    private boolean removeAbandoned;

    @Value("${spring.datasource.removeAbandonedTimeout}")
    private int removeAbandonedTimeout;

    @Value("${spring.datasource.logAbandoned}")
    private boolean logAbandoned;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("${spring.datasource.connectionProperties}")
    private String connectionProperties;

    @Value("${spring.datasource.useGlobalDataSourceStat}")
    private boolean useGlobalDataSourceStat;

    @Value("${spring.datasource.druidLoginName}")
    private String druidLoginName;

    @Value("${spring.datasource.druidPassword}")
    private String druidPassword;

    @Value("${spring.datasource.type}")
    private String dbType;

    @Bean(name = "dataSource", destroyMethod = "close", initMethod = "init")
    @Primary //不要漏了这
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        try {
            datasource.setDriverClassName(driverClassName);
            datasource.setUrl(this.dbUrl);
            datasource.setUsername(username);
            datasource.setPassword(password);
            datasource.setMinIdle(minIdle);
            datasource.setMaxActive(maxActive);
            datasource.setInitialSize(initialSize);
            datasource.setMaxWait(maxWait);
            datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
            datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            datasource.setValidationQuery(validationQuery);
            datasource.setRemoveAbandoned(removeAbandoned);
            datasource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
            datasource.setLogAbandoned(logAbandoned);
            datasource.setTestWhileIdle(testWhileIdle);
            datasource.setTestOnBorrow(testOnBorrow);
            datasource.setTestOnReturn(testOnReturn);
            datasource.setPoolPreparedStatements(poolPreparedStatements);
            datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
            datasource.setFilters(filters);
            datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
            datasource.setDbType(dbType);
            logger.info("connect to :" + dbUrl);
        } catch (Exception e) {
            logger.error("druid configuration initialization filter", e);
        }
        return datasource;
    }

}
