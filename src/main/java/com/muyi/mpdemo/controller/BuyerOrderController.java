package com.muyi.mpdemo.controller;

import com.muyi.mpdemo.frame.HttpSessionAdvice;
import com.muyi.mpdemo.domain.OrderHead;
import com.muyi.mpdemo.dto.CreateOrderReq;
import com.muyi.mpdemo.service.business.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @Author: muyi
 * @Date: Created in 11:04 2017/11/22
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/order/buyer")
public class BuyerOrderController {

    @Autowired
    private HttpSessionAdvice httpSession;

    @Autowired
    private OrderService orderService;

    @GetMapping("/login")
    public void moniLogin(){
        httpSession.setBuyerID("buyer001");
        httpSession.setOpenID("open001");
    }

    @PostMapping("/create")
    public OrderHead createOrder(CreateOrderReq coReq){
        String orderID = orderService.createOrder(coReq);
        return orderService.getOrderInfo(orderID);
    }

    @GetMapping("/pay/{orderID}")
    public void payOrder(@PathParam("orderID")String orderID){
        orderService.payOrder(orderID);
    }



}
