package com.muyi.mpdemo.controller.advice;

import com.muyi.mpdemo.frame.ResponseData;
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
     * 公众号异常处理
     */
    @ExceptionHandler(value = MpException.class)
    public ResponseData mpExceptionHandler(HttpServletRequest request, MpException mpe) throws Exception{

        log.error("MpException Occured:{}",mpe.getMessage());

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
    public ResponseData sysExceptionHandler(HttpServletRequest request,Exception e) throws Exception{
        log.error("SysException Occured:{}", e.getMessage());

        ResponseData responseData = new ResponseData();
        responseData.setMessage(e.getMessage());
        responseData.setStatus(false);
        responseData.setData(null);
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            responseData.setCode(404);
        } else {
            responseData.setCode(500);
        }
        return responseData;

    }





}
