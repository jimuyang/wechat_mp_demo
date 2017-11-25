package com.muyi.mpdemo.wechat.builder;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * @Author: muyi
 * @Date: Created in 13:18 2017/11/8
 * @Description:
 */

@Slf4j
public abstract class WxAbstractBuilder {


    public abstract WxMpXmlOutMessage build(String content,
                                            WxMpXmlMessage wxMpXmlMessage,
                                            WxMpService wxMpService);


}
