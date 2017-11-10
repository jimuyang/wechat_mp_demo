package com.muyi.mpdemo.wxhandler;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: muyi
 * @Date: Created in 17:12 2017/11/7
 * @Description:
 */
@Component
@Slf4j
public class SubscribeHandler extends BaseHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) throws WxErrorException {

        log.info("新关注用户 OPENID：{}",wxMpXmlMessage.getFromUser());

        //获取微信用户基本信息
        WxMpUser wxuser = wxMpService.getUserService().userInfo(wxMpXmlMessage.getFromUser(),null);
        return null;
    }



    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage) throws Exception {

        //TODO
        return null;
    }
}
