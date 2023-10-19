package com.lizhi.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redisson client implementation
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedissonConfig {
    /**
     *@description 同名数据自动装配
     **/
    private Integer database;
    private String host;
    private Integer port;
    private String password;
    private Integer timeout;
    @Bean
    public RedissonClient getRedissonClient(){
        Config config =new Config();
        config.useSingleServer()
                .setAddress(String.format("redis://%s:%s",host,port))
                .setDatabase(database)
                .setPassword(password)
                .setTimeout(timeout);
        return Redisson.create(config);
    }
}
