package com.muyi.mpdemo.controller;


import com.muyi.mpdemo.domain.OrderHead;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/pay")
public class PayController {


    @GetMapping("/create")
    public void create(@RequestParam("orderID")String orderID,
                       @RequestParam("returnUrl")String returnUrl){
        //查询订单并支付


    }



}
