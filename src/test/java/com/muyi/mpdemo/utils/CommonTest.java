package com.muyi.mpdemo.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonTest {

    @Test
    public void array2List(){

        String[] array = new String[10];
        array[1] = "ss";
        array[2] = "ssss";
        array[3] = "sssss";
        List<String> list = CollectionUtils.arrayToList(array);

        log.info("String array:{}\nString list:{}",
                JsonUtil.toJson(array), JsonUtil.toJson(list));
    }


    @Value("${spring.redis.host}")
    private String redisHost;

    @Test
    public void test(){
        log.info("redis.host:{}",redisHost);
    }

    @Test
    public void uuidTest(){
        for (int i = 0; i < 100 ; i++) {
            log.info(KeyUtil.UUID());
        }
    }


}
