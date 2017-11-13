package com.muyi.mpdemo.service.impl;

import com.muyi.mpdemo.exception.MpException;
import com.muyi.mpdemo.service.MpQRCodeService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: muyi
 * @Date: Created in 11:16 2017/11/10
 * @Description:
 */
@Slf4j
@Service("mpQRCodeService")
public class MpQRCodeServiceImpl implements MpQRCodeService {

    @Autowired
    private RedisTemplate redisTemplate;

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

    public void getLastMpQRCode(String sceneStr){
        try{
            WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateLastTicket(sceneStr);
            log.info("\n【ticket】:{},\n【url】:{},\n【expire_seconds】:{}", ticket.getTicket(), ticket.getUrl(), ticket.getExpire_seconds());
            //存入本地数据库或
            saveQRCodeInDB(sceneStr,ticket);
        }catch (WxErrorException e){
            throw new MpException(e);
        }
    }
    
    private boolean saveQRCodeInDB(String str,WxMpQrCodeTicket ticket){
        // TODO: 2017/11/12
        return true;
    }



}
