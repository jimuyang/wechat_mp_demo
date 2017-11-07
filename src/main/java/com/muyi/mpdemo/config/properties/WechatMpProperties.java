package com.muyi.mpdemo.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: muyi
 * @Date: Created in 16:20 2017/11/7
 * @Description:
 */

@Data
@ConfigurationProperties(prefix = "wechat.mp")
public class WechatMpProperties {

    private String appId;
    private String appSecret;
}
