package com.muyi.mpdemo.component.task;

import com.muyi.mpdemo.dao.OrderDao;
import com.muyi.mpdemo.listener.RedisMsgPubSubListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

/**
 * @Author: muyi
 * @Date: Created in 15:49 2017/11/27
 * @Description:
 */
@Slf4j
@Component
public class RedisListenTask{

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private JedisPool jedisPool1;

    @Async
    public void listen(){
        RedisMsgPubSubListener.OnMessageHandler redisExpireMsgHandler = new RedisMsgPubSubListener.OnMessageHandler() {
            @Override
            public void onMessage(String channel, String message) {
                String expireKey = message;
                //expireKey 这里是过期的orderID，需要对订单做过期处理
                orderDao.timeout(expireKey);
            }
        };
        //log.error("orderDao in RedisListenTask :{}",orderDao);
        RedisMsgPubSubListener listener = new RedisMsgPubSubListener(redisExpireMsgHandler);
        jedisPool1.getResource().subscribe(listener,"__keyevent@1__:expired");
    }

}
