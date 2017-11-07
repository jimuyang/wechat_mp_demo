package com.muyi.mpdemo.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author: muyi
 * @Date: Created in 16:23 2017/11/7
 * @Description:
 */

@Data
@ConfigurationProperties(prefix = "wechat.server")
public class WechatServerProperties {

    private String token;
    private String encodingAESKey;
    private String url;

}
