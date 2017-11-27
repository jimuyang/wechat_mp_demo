package com.muyi.mpdemo.utils;

import com.muyi.mpdemo.dto.CreateOrderReq;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest
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

    @Test
    public void json(){
        log.info(JsonUtil.toPrettyJson(
                new CreateOrderReq()));
    }

    public void hashTest(){
        HashMap<Hash,String> map = new HashMap<>();

        Hash hash1 = new Hash();
        hash1.setInt1(2);
        hash1.setInt2(2);
        map.put(hash1,"hash1");

        Hash hash2 = new Hash();
        hash2.setInt1(1);
        hash2.setInt2(12);
        map.put(hash2,"hash2");

        Hash hash3 = new Hash();
        hash3.setInt1(0);
        hash3.setInt2(22);
        map.put(hash3,"hash3");

        HashSet<Hash> set = new HashSet<>();

        log.info("===============");
        map.forEach(new BiConsumer<Hash, String>() {
            @Override
            public void accept(Hash hash, String s) {
                log.info("MAP:{} .value:{}",hash.toString(),s);
                set.add(hash);
            }
        });

        log.info("===============");
        set.forEach(new Consumer<Hash>() {
            @Override
            public void accept(Hash hash) {
                log.info("SET:{}",hash.toString());
            }
        });

        log.info("===============");
        Hash h = new Hash();
        h.setInt1(0);
        h.setInt2(22);

        map.get(h);



//        map.entrySet().forEach(new Consumer<Map.Entry<Hash, String>>() {
//            @Override
//            public void accept(Map.Entry<Hash, String> hashStringEntry) {
//                log.info("{} .value:{}",hashStringEntry.getKey(),hashStringEntry.getValue());
//            }
//        });


    }




}

@Data
@Slf4j
class Hash{
    private int int1;
    private int int2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        //if (!super.equals(o)) return false;
        Hash hash = (Hash) o;

        log.info("???? {} .equals {}",this.toString(),hash.toString());

        if (getInt1() != hash.getInt1()) return false;
        return getInt2() == hash.getInt2();
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 10 * result + getInt1();
        result = 10 * result + getInt2();

        log.info("{} .hashCode = {}",this.toString(),result);

        return result;
    }
}