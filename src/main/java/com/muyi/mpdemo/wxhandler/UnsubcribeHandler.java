package com.muyi.mpdemo.wxhandler;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: muyi
 * @Date: Created in 11:13 2017/11/8
 * @Description:
 */
@Component
@Slf4j
public class UnsubcribeHandler extends BaseHandler {


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) throws WxErrorException {

        String openID = wxMpXmlMessage.getFromUser();
        log.info("取消关注用户 OPENID: {}",openID);

        //todo 可以更新本地数据库

        return null;
    }
}
