package com.muyi.mpdemo.controller;

import com.muyi.mpdemo.domain.TestUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: muyi
 * @Date: Created in 15:39 2017/11/7
 * @Description:
 */

@RestController
@Slf4j
public class HelloController {


    @Autowired
    private HttpSession httpSession;


    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }


    @GetMapping("/session/{userID}")
    public String sessionTest(HttpSession session, @PathVariable(name = "userID") String userID){

        log.info("当前sessionID: {}, userID: {}",httpSession.getId(),userID);
        httpSession.setAttribute("pass","username");
        return "";
    }


    @GetMapping("/data")
    public TestUser jsonData(){
        TestUser user = new TestUser();
        user.setUserID("userID");
        user.setUsername("username");
        return user;
    }

    @GetMapping("/exception")
    public TestUser exceptionTest(){
        int i = 5/0;
        return null;
    }


    @GetMapping("/str")
    public String getString(){
        return "ssssss";
    }

    @GetMapping("/null")
    public String getNull(){
        return null;
    }

    @GetMapping("/boolean")
    public boolean getBoolean(){
        return true;
    }








}
