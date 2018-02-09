package com.muyi.mpdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.spi.Contract;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: muyi-corp
 * @Date: Created in 13:03 2017/12/7
 * @Description:
 */
@Controller
@RequestMapping("/paytest")
@Slf4j
public class TestController {


    @GetMapping("/test")
    public ModelAndView get(){
        return new ModelAndView("pay/create");
    }




}
