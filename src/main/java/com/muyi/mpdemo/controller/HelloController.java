package com.muyi.mpdemo.controller;

import com.muyi.mpdemo.domain.TestUser;
import com.muyi.mpdemo.exception.MpException;
import com.muyi.mpdemo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/hello")
@Slf4j
public class HelloController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private TestService testService;

    @GetMapping("/")
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
    public TestUser exceptionTest() throws Exception{
        //int i = 5/0;
        //throw new MpException(134,"sfs");
        //throw new Exception("ssfsdfsdfs");
        testService.test();
        return null;
    }


    @GetMapping("/str")
    public String getString(@RequestParam("param")String param){
        return testService.test();
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
