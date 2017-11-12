package com.muyi.mpdemo.wxhandler;

import com.muyi.mpdemo.builder.WxTableNewsBuilder;
import javafx.scene.control.Tab;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: muyi
 * @Date: Created in 13:23 2017/11/8
 * @Description:
 */

@Component
@Slf4j
public class ScanHandler extends BaseHandler {

    public static final String TABLE_SCENE_PREFIX = "Table_";

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) throws WxErrorException {

        log.info("======> 发送给路由【ScanHandler】");

        WxMpXmlMessage inMessage = wxMpXmlMessage;
        String scene = inMessage.getEventKey();

        if(scene.startsWith(TABLE_SCENE_PREFIX)){
            //桌号场景,回复一条图文消息
            String table_shop = scene.replaceFirst(TABLE_SCENE_PREFIX,"");
            return new WxTableNewsBuilder().setTable(table_shop).build("",inMessage,wxMpService);
        }
        return null;
    }
}
