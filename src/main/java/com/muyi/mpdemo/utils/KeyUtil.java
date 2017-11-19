package com.muyi.mpdemo.utils;

import java.util.UUID;

public class KeyUtil {

    private static final Integer macID = 0;

    //返回纯粹的uuid
    public static String UUID(){
        return UUID.randomUUID().toString();//.replaceAll("-","");

    }

//    //时间戳+随机数+机器号
//    public static Long timeID(){
//
//
//    }
}
