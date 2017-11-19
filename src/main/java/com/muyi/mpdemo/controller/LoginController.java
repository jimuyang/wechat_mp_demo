package com.muyi.mpdemo.controller;


import com.muyi.mpdemo.enums.ResultEnum;
import com.muyi.mpdemo.exception.BizException;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public boolean headLogin(HttpServletRequest request){

        String userID = request.getHeader("userID");
        String password = request.getHeader("password");

        if(StringUtils.isAnyBlank(userID,password)){
            throw new BizException(ResultEnum.LOGIN_FAILED);
        }

        if (userID.startsWith("user") && userID.equals(password)){
            //登录成功
            HttpSession session = request.getSession();
            session.setAttribute("userID",userID);
            return true;
        }
        return false;
    }






}
