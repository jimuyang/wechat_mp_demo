package com.muyi.mpdemo.config.properties;

import com.github.wxpay.sdk.WXPayConfig;
import lombok.Data;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Author: muyi-corp
 * @Date: Created in 14:51 2017/11/29
 * @Description:
 */
@Data
public class WechatPayProperties implements WXPayConfig {

    public WechatPayProperties(String certPath) throws Exception{

        File certFile = ResourceUtils.getFile(certPath);
        this.certData = new byte[(int)certFile.length()];

        InputStream certStream = new FileInputStream(certFile);
        //将证书内容读入certData中
        certStream.read(this.certData);
        certStream.close();

        this.certStream = new ByteArrayInputStream(this.certData);
    }

    private String notifyUrl;

    private String appID;

    private String mchID;

    private String key;

    private InputStream certStream;

    private int httpConnectTimeoutMs;

    private int httpReadTimeoutMs;

    private byte[] certData;












}
