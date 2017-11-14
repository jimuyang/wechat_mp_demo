package com.muyi.mpdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author: muyi
 * @Date: Created in 15:38 2017/11/14
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private HttpSession httpSession;

    @GetMapping
    public void index(){
        log.info("【index->session】:{}",httpSession.getId());
    }





}
