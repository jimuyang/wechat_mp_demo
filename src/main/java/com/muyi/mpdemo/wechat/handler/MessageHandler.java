package com.muyi.mpdemo.wechat.handler;

import com.muyi.mpdemo.wechat.builder.WxTextBuilder;
import com.muyi.mpdemo.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: muyi
 * @Date: Created in 13:07 2017/11/8
 * @Description:
 */
@Component
@Slf4j
public class MessageHandler extends BaseHandler {


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) throws WxErrorException {
        log.info("======> 发送给路由【MessageHandler】");

        if(!wxMpXmlMessage.getMsgType().equals(WxConsts.XML_MSG_EVENT)){
            //不是event
        }
        try{
            if(StringUtils.startsWithAny(wxMpXmlMessage.getContent(),"你好","客服")
                    && wxMpService.getKefuService().kfOnlineList().getKfOnlineList().size() > 0){
                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                        .fromUser(wxMpXmlMessage.getToUser())
                        .toUser(wxMpXmlMessage.getFromUser())
                        .build();
            }
        }catch (WxErrorException e){
            e.printStackTrace();
        }

        //收到消息
        String content = "你好，已经收到你的消息：" + JsonUtil.toJson(wxMpXmlMessage.getContent());

        return new WxTextBuilder().build(content,wxMpXmlMessage,wxMpService);

    }
}
