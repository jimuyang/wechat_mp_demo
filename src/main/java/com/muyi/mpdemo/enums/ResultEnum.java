package com.muyi.mpdemo.enums;

import lombok.Getter;

/**
 * @Author: muyi
 * @Date: Created in 12:47 2017/11/9
 * @Description:
 */

@Getter
public enum ResultEnum implements CodeEnum {

    SUCCESS(0,"success"),

    USER_NOT_FOUND(1,"用户找不到"),
    LOGIN_FIRST(2,"请先登录！"),
    LOGIN_FAILED(3,"登录失败！"),


    ;

    private int code;
    private String message;

    ResultEnum(int code,String message){
        this.code = code;
        this.message = message;
    }

}
