package com.muyi.mpdemo.wxhandler;

import com.muyi.mpdemo.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final String QRSCENE_PREFIX = "qrscene_";

    @Autowired
    private ScanHandler scanHandler;
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage inMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) throws WxErrorException {

        log.info("======> 发送给路由【SubscribeHandler】");
        log.info("新关注用户 OPENID：{}",inMessage.getFromUser());

        //获取微信用户基本信息 TODO 无权限获得基本信息
        WxMpUser wxuser = wxMpService.getUserService().userInfo(inMessage.getFromUser(),null);
        log.info("新关注用户 基本信息:{}", JsonUtil.toJson(wxuser));

        //处理特殊请求，比如如果是扫码进来的，可以做相应处理
        if(StringUtils.startsWith(inMessage.getEventKey(),QRSCENE_PREFIX)){

            inMessage.setEventKey(inMessage.getEventKey().replaceFirst(QRSCENE_PREFIX,""));
            return scanHandler.handle(inMessage,map,wxMpService,wxSessionManager);
        }
        return null;
    }




}
