package com.muyi.mpdemo.exception;

import com.muyi.mpdemo.enums.ResultEnum;
import lombok.Getter;


/**
 * @Author: muyi
 * @Date: Created in 11:25 2017/11/17
 * @Description: 业务异常 需要引起事务回滚
 *                      因此继承RuntimeException
 */
@Getter
public class BizException extends RuntimeException {

    private int bizCode;

    public BizException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.bizCode = resultEnum.getCode();
    }

    public BizException(int bizCode,String message){
        super(message);
        this.bizCode = bizCode;
    }

    //业务异常不需要 fill stack trace
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
        //return super.fillInStackTrace();
    }
}
