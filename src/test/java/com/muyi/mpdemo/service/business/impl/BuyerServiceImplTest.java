package com.muyi.mpdemo.service.business.impl;

import com.muyi.mpdemo.domain.Buyer;
import com.muyi.mpdemo.service.business.BuyerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class BuyerServiceImplTest {
    @Autowired
    private BuyerService buyerService;

    @Test
    public void createBuyer() throws Exception {

        Buyer buyer = new Buyer();
        buyer.setBuyerName("testbuyer");
        log.info("buyerID:{}",buyerService.createBuyer(buyer));
        log.info("0000");
    }

    @Test
    public void getBuyerInfo() throws Exception {
    }

}