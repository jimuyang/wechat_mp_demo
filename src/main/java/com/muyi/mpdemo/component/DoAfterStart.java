package com.muyi.mpdemo.component;

import com.muyi.mpdemo.component.task.RedisListenTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.support.collections.RedisList;
import org.springframework.stereotype.Component;

/**
 * @Author: muyi
 * @Date: Created in 15:59 2017/11/27
 * @Description:
 */
@Slf4j
@Component
public class DoAfterStart implements InitializingBean {

    @Autowired
    private RedisListenTask redisListenTask;


    @Override
    public void afterPropertiesSet() throws Exception {
        log.warn("DO AFTER START ...");
        redisListenTask.listen();
    }
}
