package com.muyi.mpdemo.config.properties;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class RedisProperties {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.database}")
    private String database;

    @Value("${spring.redis.password}")
    private String password;

}
