package com.muyi.mpdemo.enums;

import lombok.Getter;

@Getter
public enum OrderOperationEnum implements CodeEnum {

    CREATE(0,""),
    PAY(1,""),
    ACCEPT(2,""),



    ;

    private int code;
    private String message;

    OrderOperationEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

}
