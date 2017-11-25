package com.muyi.mpdemo.controller.advice;

import com.muyi.mpdemo.exception.BizException;
import com.muyi.mpdemo.exception.MpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: muyi
 * @Date: Created in 12:46 2017/11/9
 * @Description:
 */


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务异常处理
     * 重要：所有没有被catch的业务异常在这里统一处理
     */
    @ExceptionHandler(value = BizException.class)
    public ResponseData bizExceptionHandler(HttpServletRequest request, BizException be){

        log.error("【BizException Occured】{}:{}",be.getBizCode(),be.getMessage());
        //log.error("         Stack Trace:{}", JsonUtil.toPrettyJson(be.getStackTrace()));
        ResponseData responseData = new ResponseData();
        responseData.setCode(be.getBizCode());
        responseData.setMessage(be.getMessage());
        responseData.setStatus(false);
        responseData.setData(null);

        return responseData;
    }

    /**
     * 公众号异常处理
     */
    @ExceptionHandler(value = MpException.class)
    public ResponseData mpExceptionHandler(HttpServletRequest request, MpException mpe){

        log.error("【MpException Occured】{}",mpe.getMessage());
        //log.error("        Stack Trace:{}", JsonUtil.toPrettyJson(mpe.getStackTrace()));
        ResponseData responseData = new ResponseData();
        responseData.setCode(mpe.getCode());
        responseData.setMessage(mpe.getMessage());
        responseData.setStatus(false);
        responseData.setData(null);

        return responseData;
    }
    /**
     * 系统异常处理 404,500
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseData sysExceptionHandler(HttpServletRequest request,Exception e){
        log.error("【SysException Occured】{}", e.getMessage());
        //log.error("         Stack Trace:{}", e.getStackTrace());
        ResponseData responseData = new ResponseData();
        responseData.setMessage(e.getMessage());
        responseData.setStatus(false);
        responseData.setData(null);
//        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
//            responseData.setCode(404);
//        } else {
//            responseData.setCode(500);
//        }
        return responseData;

    }


}
