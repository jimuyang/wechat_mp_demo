package com.muyi.mpdemo.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.muyi.mpdemo.config.properties.WechatProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: muyi
 * @Date: Created in 21:58 2017/11/8
 * @Description:
 */

@Slf4j
@Configuration
@EnableConfigurationProperties(WechatProperties.class)
public class WechatPayConfig {

    @Autowired
    private WechatProperties wechatProperties;

    @Bean
    public WxPayH5Config wxPayH5Config() {
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(wechatProperties.getMp().getAppId());
        wxPayH5Config.setMchId(wechatProperties.getPay().getMchId());
        wxPayH5Config.setMchKey(wechatProperties.getPay().getMchKey());
        wxPayH5Config.setKeyPath(wechatProperties.getPay().getKeyPath());
        wxPayH5Config.setNotifyUrl(wechatProperties.getPay().getNotifyUrl());
        return wxPayH5Config;
    }

    @Bean
    public BestPayServiceImpl bestPayService(WxPayH5Config wxPayH5Config) {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config);
        return bestPayService;
    }


//    @Bean
//    @ConditionalOnMissingBean
//    public WxPayH5Config wxPayH5Config(){
//        WxPayConfig payConfig = new WxPayConfig();
//        payConfig.setAppId(this.wechatProperties.getMp().getAppId());
//        payConfig.setMchId(this.wechatProperties.getPay().getMchId());
//        payConfig.setMchKey(this.wechatProperties.getPay().getMchKey());
//        payConfig.setKeyPath(this.wechatProperties.getPay().getKeyPath());
//
//        return payConfig;
//    }
//
//    @Bean
//    public WxPayService wxPayService(WxPayConfig payConfig){
//        WxPayService wxPayService = new WxPayServiceImpl();
//        wxPayService.setConfig(payConfig);
//        return wxPayService;
//    }



}
