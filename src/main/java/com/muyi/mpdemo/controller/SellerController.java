package com.muyi.mpdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: muyi
 * @Date: Created in 11:05 2017/11/22
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/seller")
public class SellerController {


    @GetMapping("/login")
    @ResponseBody
    public String sellerLogin(@RequestParam String code){

        log.error("code = {}",code);
        return "hello world";
    }

    //查看订单列表

    //查看订单详情

    //接单

    //叫号

    //快递

    //完结订单

    //废弃订单
}
