package com.muyi.mpdemo.exception;

import com.muyi.mpdemo.config.properties.WechatProperties;
import com.muyi.mpdemo.enums.ResultEnum;
import lombok.Data;

/**
 * @Author: muyi
 * @Date: Created in 12:50 2017/11/9
 * @Description:
 */

@Data
public class MpException extends RuntimeException {

    private int code;

    MpException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    MpException(int code,String message){
        super(message);
        this.code = code;
    }

    MpException(int code){
        super();
        this.code = code;
    }
}
