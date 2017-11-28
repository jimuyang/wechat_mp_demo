package com.muyi.mpdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author: muyi
 * @Date: Created in 15:55 2017/11/8
 * @Description:
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
public class RedisSessionConfig {






}
