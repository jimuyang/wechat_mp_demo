package com.muyi.mpdemo.frame;

import com.muyi.mpdemo.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: muyi
 * @Date: Created in 18:49 2017/11/17
 * @Description:
 */
@Slf4j
@Component
@EnableScheduling
public class ScheduleTasks {

    /**
     * 每5s触发一次的定时任务
     */
    @Scheduled(cron = "*/5 * * * * ?")
    public void schTest1() {
        String dateStr = DateUtil.currentDateString();
        //System.out.println("这是spring定时器1，每五秒执行一次,当前时间：" + dateStr);
    }

}
