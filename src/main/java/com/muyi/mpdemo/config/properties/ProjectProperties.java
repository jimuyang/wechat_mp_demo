package com.muyi.mpdemo.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: muyi
 * @Date: Created in 14:54 2017/11/14
 * @Description:
 */
@Data
@ConfigurationProperties(prefix = "project")
public class ProjectProperties {

    private Url url;

    @Data
    public static class Url{
        private String index;
        private String authUserInfo;
        private String webAuth;
    }





}
