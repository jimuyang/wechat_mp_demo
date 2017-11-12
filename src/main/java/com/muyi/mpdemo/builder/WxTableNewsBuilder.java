package com.muyi.mpdemo.builder;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.builder.outxml.NewsBuilder;

@Slf4j
public class WxTableNewsBuilder extends WxAbstractBuilder {

    public static final String TABLE_NEWS_TITLE_FORMAT = "欢迎来到 %s餐厅";
    public static final String TABLE_NEWS_DESC_FORMAT = "您的桌号是 %s ,请你点击这条消息点餐";
    public static final String TABLE_NEWS_PICURL = "http://muyi.natapp1.cc/mpserver/static/images/table_news_pic.jpg";
    public static final String TABLE_NEWS_URL_FORMAT = "http://www.baidu.com?shopID=%s&tableCode=%s";


    private NewsBuilder newsBuilder = new NewsBuilder();

    public WxTableNewsBuilder setTable(String table_shop) {

        String[] strings = table_shop.split("_");
        String shopID = strings[1];
        String tableCode = strings[0];

        WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
        item.setTitle(String.format(TABLE_NEWS_TITLE_FORMAT,shopID));
        item.setDescription(String.format(TABLE_NEWS_DESC_FORMAT, tableCode));
        item.setPicUrl(TABLE_NEWS_PICURL);
        item.setUrl(String.format(TABLE_NEWS_URL_FORMAT,shopID,tableCode));

        log.info("【Table_url】:{}",item.getUrl());

        newsBuilder.addArticle(item);
        return this;
    }

    @Override
    public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMpXmlMessage, WxMpService wxMpService) {
        return newsBuilder
                .toUser(wxMpXmlMessage.getFromUser())
                .fromUser(wxMpXmlMessage.getToUser())
                .build();
    }
}
