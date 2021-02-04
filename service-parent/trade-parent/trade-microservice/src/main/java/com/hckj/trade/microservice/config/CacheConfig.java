package com.hckj.trade.microservice.config;

import com.hckj.common.cache.redis.RedisConfig;
import com.hckj.common.cache.redis.RedisUtil;
import com.hckj.common.cache.redis.sentinel.RedisSentinel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存配置
 *
 * @author ：yuhui
 * @date ：Created in 2020/9/24 17:43
 */
@Configuration
public class CacheConfig {

    @Value("${redis.timeout}")
    private int timeout;

    @Value("${redis.maxTotal}")
    private int maxTotal;

    @Value("${redis.maxIdle}")
    private int maxIdle;

    @Value("${redis.minIdle}")
    private int minIdle;

    @Value("${redis.maxRedirections}")
    private int maxRedirections;

    @Value("${redis.maxWaitMillis}")
    private int maxWaitMillis;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.sentinel.master}")
    private String sentinelMaster;

    @Value("${redis.sentinel.nodes}")
    private String sentinelNodes;

    @Value("${redis.cluster.address}")
    private String clusterAddress;

    @Value("${redis.single.ip}")
    private String singleIp;

    @Value("${redis.single.port}")
    private Integer singlePort;

    @Bean(initMethod = "init")
    public RedisUtil redis() {
        RedisConfig redisConfig = new RedisConfig();
        redisConfig.setTimeout(timeout);
        redisConfig.setMaxTotal(maxTotal);
        redisConfig.setMaxIdle(maxIdle);
        redisConfig.setMinIdle(minIdle);
        redisConfig.setMaxRedirections(maxRedirections);
        redisConfig.setMaxWaitMillis(maxWaitMillis);
        redisConfig.setPassword(password);
        redisConfig.setSentinelMasterName(sentinelMaster);
        redisConfig.setSentinelNodes(sentinelNodes);
        redisConfig.setClusterAddress(clusterAddress);
        redisConfig.setSingleIp(singleIp);
        redisConfig.setSinglePort(singlePort);
        return new RedisSentinel(redisConfig);
    }

}
