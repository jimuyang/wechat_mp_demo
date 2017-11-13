package com.muyi.mpdemo.service.impl;

import com.muyi.mpdemo.exception.MpException;
import com.muyi.mpdemo.service.MpQRCodeService;
import com.sun.javafx.binding.StringFormatter;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ClassPathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * @Author: muyi
 * @Date: Created in 11:16 2017/11/10
 * @Description:
 */
@Slf4j
@Service("mpQRCodeService")
public class MpQRCodeServiceImpl implements MpQRCodeService {

    @Autowired
    private WxMpService wxMpService;


    public void getLastMpQRCode(int sceneID){
        try{
            WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateLastTicket(sceneID);
            log.info("\n【ticket】:{},\n【url】:{},\n【expire_seconds】:{}",ticket.getTicket(),ticket.getUrl(),ticket.getExpire_seconds());
        }catch (WxErrorException e){
            throw new MpException(e);
        }
    }

    public void getLastMpQRCode(String sceneStr) throws IOException{
        try{
            WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateLastTicket(sceneStr);
            log.info("\n【ticket】:{},\n【url】:{},\n【expire_seconds】:{}", ticket.getTicket(), ticket.getUrl(), ticket.getExpire_seconds());
            //存入本地数据库
            saveQRCodeInDB(sceneStr,ticket);
            //存入本地文件
            saveQRCodeFile(sceneStr,ticket);

        }catch (WxErrorException e){
            throw new MpException(e);
        }
    }
    
    private void saveQRCodeInDB(String str,WxMpQrCodeTicket ticket){
        // TODO: 2017/11/12
    }

    private void saveQRCodeFile(String sceneStr,WxMpQrCodeTicket ticket)
            throws IOException,WxErrorException{
        //保存图片到  项目根目录/qrcodes/ 下
        String qrcodeFileName = String.format("qrcode_%s.png", sceneStr);
        String filePath = String.format("qrcodes/%s", qrcodeFileName);

        File qrcodeFile = wxMpService.getQrcodeService().qrCodePicture(ticket);
        File destFile = new File(filePath);
        FileUtils.copyFile(qrcodeFile,destFile);
    }



}
