package com.muyi.mpdemo.config;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.muyi.mpdemo.config.properties.WechatPayProperties;
import com.muyi.mpdemo.config.properties.WechatProperties;
import com.muyi.mpdemo.enums.SignType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Author: muyi
 * @Date: Created in 21:58 2017/11/8
 * @Description:
 */

@Slf4j
@Configuration
@EnableConfigurationProperties(WechatProperties.class)
public class WechatPayConfig{

    @Autowired
    private WechatProperties wechatProperties;

    /**
     * 这里是使用微信官方sdk的配置
     */
    @Bean
    public WechatPayProperties wechatPayProperties() throws Exception{
        WechatPayProperties wechatPayProperties = new WechatPayProperties(wechatProperties.getPay().getKeyPath());
        wechatPayProperties.setAppID(wechatProperties.getMp().getAppId());
        wechatPayProperties.setKey(wechatProperties.getPay().getMchKey());
        wechatPayProperties.setMchID(wechatProperties.getPay().getMchId());
        wechatPayProperties.setHttpConnectTimeoutMs(8000);
        wechatPayProperties.setHttpReadTimeoutMs(10000);
        wechatPayProperties.setNotifyUrl(wechatProperties.getPay().getNotifyUrl());
        return wechatPayProperties;
    }
    @Bean
    public WXPay wxPayService(WechatPayProperties wechatPayProperties) throws Exception{
        WXPayConstants.SignType signType = this.wechatProperties.getPay().getSignType().equals("MD5")
                ? WXPayConstants.SignType.MD5 : WXPayConstants.SignType.HMACSHA256;
        return new WXPay(wechatPayProperties,signType,this.wechatProperties.getPay().isUseSandbox());
    }


    /**
     * 这里是使用了 best-pay-sdk 第三方sdk时需要的配置
     */
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

}
