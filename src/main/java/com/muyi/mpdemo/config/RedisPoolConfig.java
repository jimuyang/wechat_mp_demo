package com.muyi.mpdemo.config;

import com.muyi.mpdemo.config.properties.RedisProperties;
import com.muyi.mpdemo.config.properties.WechatProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;

/**
 * @Author: muyi
 * @Date: Created in 11:08 2017/11/27
 * @Description: Redis 连接池配置
 *
 *
 */

@Slf4j
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisPoolConfig {

    @Autowired
    private RedisProperties redisProperties;

    private static int MAX_IDLE = 10;   //连接池最大空闲连接数（最多保持的空闲连接）
    private static int MAX_ACTIVE = 50;    //最大激活的连接数
    private static int MAX_WAIT = 10 ;    //等待可用连接的最大时间（s）-1代表永不超时。若超过等待时间，则抛出 JedisConnectionException

    private static int TIMEOUT = 10 * 1000;     //连接连接池的超时时间 todo 测试subscribe
    private static boolean TEST_ON_BORROW = true;       //使用链接时，测试连接是否可用
    private static boolean TEST_ON_RETURN = true;       //归还连接时，测试连接是否可用

    @Bean
    public JedisPool jedisPool0(){
        return newInstance(0);
    }

    @Bean
    public JedisPool jedisPool1(){
        return newInstance(1);
    }

    private JedisPool newInstance(int database){
        JedisPool jedisPool ;
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(MAX_IDLE);
        poolConfig.setMaxTotal(MAX_ACTIVE);
        poolConfig.setMaxWaitMillis(MAX_WAIT);
        poolConfig.setTestOnBorrow(TEST_ON_BORROW);
        poolConfig.setTestOnReturn(TEST_ON_RETURN);

        jedisPool = new JedisPool
                (poolConfig,redisProperties.getHost(),redisProperties.getPort(),
                        TIMEOUT,null,database);
        return jedisPool;
    }

}
