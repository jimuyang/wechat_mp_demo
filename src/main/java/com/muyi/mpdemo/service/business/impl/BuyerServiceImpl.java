package com.muyi.mpdemo.service.business.impl;

import com.muyi.mpdemo.frame.HttpSessionAdvice;
import com.muyi.mpdemo.dao.BuyerDao;
import com.muyi.mpdemo.domain.Buyer;
import com.muyi.mpdemo.service.business.BuyerService;
import com.muyi.mpdemo.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service("buyerService")
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerDao buyerDao;

    @Autowired
    private HttpSessionAdvice httpSession;

    @Override
    public String createBuyer(Buyer buyer) {
        //先看看以前有没有创建过  （openID是不重的）
        Buyer old = buyerDao.selectOneByOpenID(buyer.getOpenID());
        if (old != null){
            return old.getBuyerID();
        }

        String buyerID = KeyUtil.UUID();
        buyer.setBuyerID(buyerID);

        buyerDao.insertOne(buyer);
        return buyerID;
    }

    @Override
    public Buyer getBuyerInfo() {
        String buyerID = httpSession.getBuyerID();
        return buyerDao.selectOne(buyerID);
    }
}
