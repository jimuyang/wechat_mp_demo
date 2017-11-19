package com.muyi.mpdemo.controller;


import com.muyi.mpdemo.controller.advice.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/**")
public class NoMappingController {

    @GetMapping
    public ResponseData noMapping(){
        return new ResponseData(false,0,"Url not found",null);
    }
}
