package com.muyi.mpdemo.enums;

import lombok.Getter;

/**
 * @Author: muyi
 * @Date: Created in 12:47 2017/11/9
 * @Description:
 */

@Getter
public enum ResultEnum {

    SUCCESS(0,"success"),

    USER_NOT_FOUND(1,"用户找不到"),

    ;

    private int code;
    private String message;

    ResultEnum(int code,String message){
        this.code = code;
        this.message = message;
    }

}
