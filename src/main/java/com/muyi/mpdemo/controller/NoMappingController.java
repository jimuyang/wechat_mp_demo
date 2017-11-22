package com.muyi.mpdemo.controller;


import com.muyi.mpdemo.controller.advice.ResponseData;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 这样实现后居然也拦截的访问静态资源的url
 * 很要命
 */

//@Slf4j
//@RestController
//@RequestMapping("/**")
public class NoMappingController {

    @GetMapping
    public ResponseData noMapping(){
        return new ResponseData(false,0,"Url not found",null);
    }
}
