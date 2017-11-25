package com.muyi.mpdemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: muyi
 * @Date: Created in 14:56 2017/11/23
 * @Description:
 */
public class DateUtil {

    public static String simpleFormat(Date date){
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sim.format(date);
        return dateStr;
    }

    public static String currentDateString(){
        return simpleFormat(new Date());
    }

    public static Date currentDate(){
        return new Date();
    }


}
