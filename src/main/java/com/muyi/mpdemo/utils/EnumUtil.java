package com.muyi.mpdemo.utils;

import com.muyi.mpdemo.enums.CodeEnum;

public class EnumUtil {

    public static <T extends CodeEnum> T getEnumByCode(int code, Class<T> tClass){
        for (T t: tClass.getEnumConstants()) {
            if (t.getCode() == code){
                return t;
            }
        }
        return null;
    }





}
