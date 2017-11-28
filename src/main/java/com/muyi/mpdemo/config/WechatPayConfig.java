package com.muyi.mpdemo.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.muyi.mpdemo.config.properties.WechatProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: muyi
 * @Date: Created in 21:58 2017/11/8
 * @Description:
 */

@Slf4j
@Configuration
public class WechatPayConfig {

    @Autowired
    private WechatProperties wechatProperties;

    @Bean
    public BestPayServiceImpl bestPayService(){
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(wechatProperties.getMp().getAppId());
        //wxPayH5Config.setAppSecret(wechatProperties.getMp().getAppSecret());
        wxPayH5Config.setMchId(wechatProperties.getPay().getMchId());
        wxPayH5Config.setMchKey(wechatProperties.getPay().getMchKey());
        wxPayH5Config.setKeyPath(wechatProperties.getPay().getKeyPath());

        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config);
        return bestPayService;
    }







}
