package com.muyi.mpdemo.controller.advice;

import com.muyi.mpdemo.builder.ResponseData;
import com.muyi.mpdemo.enums.ResultEnum;
import com.muyi.mpdemo.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author: muyi
 * @Date: Created in 14:03 2017/11/9
 * @Description:
 */
@RestControllerAdvice
@Slf4j
public class DataResponseBodyAdvice implements ResponseBodyAdvice{

    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        //log.info("初始返回数据：{}", JsonUtil.toJson(o));
        ResponseData data = new ResponseData();
        data.setData(o);
        data.setMessage(null);
        data.setCode(ResultEnum.SUCCESS.getCode());
        data.setStatus(true);
        //解决无法返回string类型的问题
        if(methodParameter.getMethod().getReturnType().equals(String.class)){
            log.info("返回结果为：{}",JsonUtil.toJson(data));
            return JsonUtil.toPrettyJson(data);
        }
        log.info("返回结果为：{}",JsonUtil.toJson(data));
        return data;
    }


    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //log.info("methodParameter.method.name: {}", methodParameter.getMethod().getName());
        //log.info("methodParameter.methodAnnotations: {}", methodParameter.getMethodAnnotations());
        //过滤掉 ExceptionHandler 和 wechat
        if (methodParameter.getMethodAnnotation(ExceptionHandler.class) != null
                || methodParameter.getMethod().getName().startsWith("wechat")){
            return false;
        }
        //log.info("class: {}", aClass.getName());
        return true;
    }



}


