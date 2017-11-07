package com.muyi.mpdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: muyi
 * @Date: Created in 15:39 2017/11/7
 * @Description:
 */

@RestController
public class HelloController {

    @GetMapping
    public String hello(){
        return "hello world";
    }
}
