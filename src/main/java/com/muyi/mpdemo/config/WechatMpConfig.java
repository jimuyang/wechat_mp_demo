package com.muyi.mpdemo.config;

import com.muyi.mpdemo.config.properties.WechatMpProperties;
import com.muyi.mpdemo.config.properties.WechatServerProperties;
import com.muyi.mpdemo.utils.JsonUtil;
import com.muyi.mpdemo.wxhandler.LogHandler;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.*;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: muyi
 * @Date: Created in 16:29 2017/11/7
 * @Description:
 */

@Configuration
@ConditionalOnClass(WxMpService.class)
@EnableConfigurationProperties({WechatMpProperties.class,WechatServerProperties.class})
@Slf4j
public class WechatMpConfig {

    @Autowired
    protected WechatMpProperties wechatMpProperties;

    @Autowired
    protected WechatServerProperties wechatServerProperties;

    @Autowired
    protected LogHandler logHandler;


    @Bean
    @ConditionalOnBean
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();

        wxMpConfigStorage.setAppId(this.wechatMpProperties.getAppId());
        wxMpConfigStorage.setSecret(this.wechatMpProperties.getAppSecret());

        wxMpConfigStorage.setAesKey(this.wechatServerProperties.getEncodingAESKey());
        wxMpConfigStorage.setToken(this.wechatServerProperties.getToken());

        log.info("WxMpConfigStorage: {}",JsonUtil.toJson(wxMpConfigStorage));
        return wxMpConfigStorage;
    }

    @Bean
    @ConditionalOnBean
    public WxMpService wxMpService(WxMpConfigStorage wxMpConfigStorage){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage);
        log.info("WxMpService initialized...");
        return wxMpService;
    }

    @Bean
    public WxMpMessageHandler router(WxMpService wxMpService){
        final WxMpMessageRouter newRouter
                = new WxMpMessageRouter(wxMpService);

        //记录所有事件的日志（异步）
        newRouter.rule().async(true).handler(this.logHandler).next();






    }




}
