package com.muyi.mpdemo.wechat.handler;


import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;
import com.muyi.mpdemo.utils.JsonUtil;

import java.util.Map;

/**
 * @Author: muyi
 * @Date: Created in 16:56 2017/11/7
 * @Description:
 */
@Component
@Slf4j
public class LogHandler extends BaseHandler {


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) throws WxErrorException {
        log.info("接收到微信请求消息，内容：\n{}", JsonUtil.toJson(wxMpXmlMessage));
        return null;
    }
}
