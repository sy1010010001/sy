package com.sy.common.config.redis;

import lombok.Data;
import lombok.Getter;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.config.TransportMode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;


@Configuration
@ConfigurationProperties(prefix = "config.redis")
@Data
public class RedissonConfig {

    /** redis 配置 */
    private String hostName;
    private int port;
//    private int database;
//    private String password;

    private int connectionMinimumIdleSize;
    private int connectionPoolSize;


    @Bean(destroyMethod = "shutdown")
    RedissonClient redisson() {
        Config config = new Config();
        //Redis多节点
        // config.useClusterServers()
        //     .addNodeAddress("redis://127.0.0.1:6379", "redis://127.0.0.1:7001");
        //Redis单节点
        SingleServerConfig singleServerConfig = config.useSingleServer();
        //可以用"rediss://"来启用SSL连接
        String address = "redis://" + hostName + ":" + port;
        singleServerConfig.setAddress(address);
        //设置 数据库编号
//        singleServerConfig.setDatabase(database);
//        singleServerConfig.setPassword(password);
        singleServerConfig.setConnectionMinimumIdleSize(connectionMinimumIdleSize);
        //连接池大小:默认值：64
         singleServerConfig.setConnectionPoolSize(connectionPoolSize);
        return Redisson.create(config);
    }


}
