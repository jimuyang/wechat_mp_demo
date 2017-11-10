package com.muyi.mpdemo.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: muyi
 * @Date: Created in 16:18 2017/11/7
 * @Description:
 */

@RestController
@RequestMapping("/wechat/portal")
@Slf4j
public class WechatAuthController {

    public static final String ENCTYPE_AES = "aes";

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpMessageRouter wxMpMessageRouter;

    @GetMapping(produces = "text/plain;charset=utf-8")
    public String authGet(@RequestParam("signature") String signature,
                          @RequestParam("timestamp") String timestamp,
                          @RequestParam("nonce") String nonce,
                          @RequestParam("echostr") String echostr){

        log.info("收到疑为微信服务器认证消息...【{}，{}，{}，{}】",
                signature,timestamp,nonce,echostr);

        if(StringUtils.isAnyBlank(signature,timestamp,nonce,echostr)){
            throw new IllegalArgumentException("请求参数不合法，请核实");
        }

        if(this.wxMpService.checkSignature(timestamp,nonce,signature)){
            log.info("签名正确...");
            return echostr;
        }else{
            log.error("签名验证失败...认证无法通过");
            return "非法请求";
        }
    }


    @PostMapping(produces = "application/xml; charset=UTF-8")
    public String postMsg(@RequestBody String requestBody,
                          @RequestParam("signature") String signature,
                          @RequestParam("timestamp") String timestamp,
                          @RequestParam("nonce") String nonce,
                          @RequestParam(name = "encrypt_type",required = false) String encType,
                          @RequestParam(name = "msg_signature",required = false) String msgSignature){

        log.info("\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
                + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n]",
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
            if(outMessage == null) return "";
            logout = outMessage.toXml();
        }else if (encType.equals(ENCTYPE_AES)){
            //aes 加密的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(
                    requestBody,this.wxMpService.getWxMpConfigStorage(),timestamp,nonce,msgSignature);

            log.info("\n消息解密后内容为：\n{} ", inMessage.toString());
            WxMpXmlOutMessage outMessage = this.route(inMessage);
            if(outMessage == null) return "";
            //加密后发回
            logout = outMessage.toEncryptedXml(this.wxMpService.getWxMpConfigStorage());
        }
        log.info("\n组装回复信息：{}", logout);
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
