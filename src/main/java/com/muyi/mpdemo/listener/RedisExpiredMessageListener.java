package com.muyi.mpdemo.listener;

import com.muyi.mpdemo.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: muyi
 * @Date: Created in 10:47 2017/11/24
 * @Description:
 */
@Slf4j
//@Component
public class RedisExpiredMessageListener implements MessageListener,InitializingBean {

    @Autowired
    private RedisMessageListenerContainer redisMessageListenerContainer;

    @Override
    public void afterPropertiesSet() throws Exception {
        redisMessageListenerContainer.addMessageListener(this,new PatternTopic("__key*__:"));
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        log.error("ON REDIS MESSAGE...");
        log.error(JsonUtil.toPrettyJson(message.getBody()));
    }
}
