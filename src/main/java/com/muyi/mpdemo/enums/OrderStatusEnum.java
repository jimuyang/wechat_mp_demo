package com.muyi.mpdemo.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements CodeEnum {

    NEW(0,"新创建"),
    PAID(1,"已支付"),
    ACCEPTED(2,"已接单"),
    COMPLETED(3,"已完成"),

    QUEUEING(4,"叫号中"),
    DELIVERING(5,"送餐中"),

    CANCELLED(6,"已取消"),

    ;

    private int code;
    private String message;

    OrderStatusEnum(int code,String message){
        this.code = code;
        this.message = message;
    }

}
