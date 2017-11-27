package com.muyi.mpdemo.dao;

import com.muyi.mpdemo.domain.Buyer;
import com.muyi.mpdemo.utils.JsonUtil;
import com.muyi.mpdemo.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONUtil;
import org.apache.ibatis.annotations.Select;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class BuyerDaoTest {

    @Autowired
    private BuyerDao buyerDao;


    @Test
    public void insertOne() throws Exception {
        Buyer newBuyer = new Buyer();
        newBuyer.setBuyerID(KeyUtil.UUID());
        newBuyer.setOpenID("open001");
        newBuyer.setBuyerName("xiaoming");
        newBuyer.setBuyerPass("1234");
        newBuyer.setAccountBalance(new BigDecimal(100));
        int result = buyerDao.insertOne(newBuyer);
        log.info("result:{}",result);
    }

    @Test
    public void tracTest() throws Exception {
        tracTest1();
        tracTest2();
    }

    @Transactional
    public void tracTest1(){
        String xiaoming_uuid = "3B6B9FBF-AC0E-4861-9CB3-62A54E08D2E2";
        Buyer buyer = buyerDao.selectOneLock(xiaoming_uuid);
        log.info("xiaoming:{}", JsonUtil.toPrettyJson(buyer));
    }

    @Transactional
    public void tracTest2(){
        String xiaoming_uuid = "33FAD6EC-832E-48F8-8470-EA176FF62C85";
        Buyer buyer = buyerDao.selectOneLock(xiaoming_uuid);
        log.info("xiaoming:{}", JsonUtil.toPrettyJson(buyer));
    }


    @Test
    public void updateAcctBalance() throws Exception {



    }

}