package com.muyi.mpdemo.controller;


import com.muyi.mpdemo.enums.ResultEnum;
import com.muyi.mpdemo.exception.BizException;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private HttpSession httpSession;

    @GetMapping
    public boolean login(@RequestParam("userID")String userID,
                             @RequestParam("password")String password){

        //String userID = request.getHeader("userID");
        //String password = request.getHeader("password");

        if(StringUtils.isAnyBlank(userID,password)){
            throw new BizException(ResultEnum.LOGIN_FAILED);
        }

        if (userID.startsWith("user") && userID.equals(password)){
            //登录成功
            httpSession.setAttribute("userID",userID);
            return true;
        }
        return false;
    }






}
