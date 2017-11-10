package com.muyi.mpdemo.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * @Author: muyi
 * @Date: Created in 13:20 2017/11/8
 * @Description:
 */
public class WxTextBuilder extends WxAbstractBuilder {

    @Override
    public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMpXmlMessage, WxMpService wxMpService) {

        WxMpXmlOutMessage outMessage = WxMpXmlOutMessage.TEXT()
                .content(content)
                .fromUser(wxMpXmlMessage.getToUser())
                .toUser(wxMpXmlMessage.getFromUser())
                .build();
        return outMessage;


    }
}
