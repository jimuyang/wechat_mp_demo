package com.muyi.mpdemo.service.business.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.muyi.mpdemo.service.business.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("payService")
public class PayServiceImpl implements PayService {

    @Autowired
    private BestPayServiceImpl bestPayService;


    public void create(String orderID){
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid();
        payRequest.setOrderId();
        payRequest.setOrderAmount(0.01);
        payRequest.setOrderName("支付测试");
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        PayResponse payResponse = bestPayService.pay(payRequest);

    }





}
