package com.muyi.mpdemo.utils;

import com.muyi.mpdemo.config.RedisPoolConfig;
import com.muyi.mpdemo.dao.OrderDao;
import com.muyi.mpdemo.domain.TestUser;
import com.muyi.mpdemo.listener.RedisMsgPubSubListener;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInRedisConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import static org.junit.Assert.*;

/**
 * @Author: muyi
 * @Date: Created in 15:08 2017/11/8
 * @Description:
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RedisUtilTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private JedisPool jedisPool1;

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpConfigStorage wxMpConfigStorage;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        log.info(stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObj() throws Exception {
        TestUser user = new TestUser();
        user.setUserID("userID001");
        user.setPassword("password");
        user.setUsername("user001");
        redisTemplate.opsForValue().set(user.getUserID(),user);

        TestUser user1 = (TestUser)redisTemplate.opsForValue().get("userID001");
        log.info(JsonUtil.toPrettyJson(user1));

    }

    @Test
    public void testAccessToken() throws Exception{

        ((WxMpInRedisConfigStorage)wxMpConfigStorage).updateAccessToken("demoToken",200);
        String accessToken = wxMpService.getAccessToken();
        log.info("【Wechat access_token】: {}",accessToken);

    }

    @Test
    public void testRedisExpire(){
        RedisMsgPubSubListener.OnMessageHandler redisExpireMsgHandler = new RedisMsgPubSubListener.OnMessageHandler() {
            @Override
            public void onMessage(String channel, String message) {
                String expireKey = message;
                //expireKey这里是过期的orderID，需要对订单做过期处理
                orderDao.timeout(expireKey);
            }
        };

        RedisMsgPubSubListener listener = new RedisMsgPubSubListener(redisExpireMsgHandler);
        jedisPool1.getResource().subscribe(listener,"__keyevent@0__:expired");
    }




}