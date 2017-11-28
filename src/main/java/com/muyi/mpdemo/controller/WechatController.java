package com.muyi.mpdemo.controller;

import com.muyi.mpdemo.exception.MpException;
import com.muyi.mpdemo.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NoOpCache;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: muyi
 * @Date: Created in 16:18 2017/11/7
 * @Description:
 */

@RestController
@RequestMapping("/wechat/portal")
@Slf4j
public class WechatController {


    public static final String ENCTYPE_AES = "aes";
    public static final String MSG_SIGNATURE = "msg_signature";
    public static final String ENCTYPE_TYPE = "encrypt_type";


    public static final String SIGNATURE = "signature";
    public static final String TIMESTAMP = "timestamp";
    public static final String NONCE = "nonce";
    public static final String ECHOSTR = "echostr";

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpMessageRouter wxMpMessageRouter;

    @GetMapping(produces = "text/plain;charset=utf-8")
    public String wechatAuthGet(@RequestParam(SIGNATURE) String signature,
                                @RequestParam(TIMESTAMP) String timestamp,
                                @RequestParam(NONCE) String nonce,
                                @RequestParam(ECHOSTR) String echostr){

        log.info("收到疑为微信服务器认证消息...【{}，{}，{}，{}】",
                signature,timestamp,nonce,echostr);

        if(StringUtils.isAnyBlank(signature,timestamp,nonce,echostr)){
            throw new MpException(0,"请求参数不合法。");
        }

        if(this.wxMpService.checkSignature(timestamp,nonce,signature)){
            log.info("签名正确...,回显str为：{}",echostr);
            return echostr;
        }else{
            log.error("签名验证失败...认证无法通过");
            return "非法请求";
        }
    }


    @PostMapping(produces = "application/xml; charset=UTF-8")
    public String wechatPostMsg(@RequestBody String requestBody,
                                @RequestParam(SIGNATURE) String signature,
                                @RequestParam(TIMESTAMP) String timestamp,
                                @RequestParam(NONCE) String nonce,
                                @RequestParam(name = ENCTYPE_TYPE,required = false) String encType,
                                @RequestParam(name = MSG_SIGNATURE,required = false) String msgSignature){

        log.info("<====== 接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
                + " timestamp=[{}], nonce=[{}], requestBody=[\n{}]",
                signature,encType,msgSignature,timestamp,nonce,requestBody);

        if(!this.wxMpService.checkSignature(timestamp,nonce,signature)){
            log.error("签名验证失败，属于非法请求");
            return "非法请求";
        }

        String logout = null;
        if(encType == null){
            //明文传输的信息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
            WxMpXmlOutMessage outMessage = this.route(inMessage);
            log.info("======> 组装回复信息：\n{}", JsonUtil.toJson(outMessage));
            if(outMessage == null) return "";
            logout = outMessage.toXml();
        }else if (encType.equals(ENCTYPE_AES)){
            //aes加密的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(
                    requestBody,this.wxMpService.getWxMpConfigStorage(),timestamp,nonce,msgSignature);

            log.info("<====== 消息解密后内容为：\n{} ", JsonUtil.toJson(inMessage));
            WxMpXmlOutMessage outMessage = this.route(inMessage);
            log.info("======> 组装回复信息：\n{}", JsonUtil.toJson(outMessage));

            if(outMessage == null) return "";
            //加密后发回
            logout = outMessage.toEncryptedXml(this.wxMpService.getWxMpConfigStorage());
        }
        return logout;
    }

    private WxMpXmlOutMessage route(WxMpXmlMessage message){
        try{
            return this.wxMpMessageRouter.route(message);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null;
    }










}
