package com.muyi.mpdemo.controller;

import com.muyi.mpdemo.component.HttpSessionAdvice;
import com.muyi.mpdemo.config.properties.ProjectProperties;
import com.muyi.mpdemo.domain.Buyer;
import com.muyi.mpdemo.exception.MpException;
import com.muyi.mpdemo.service.business.BuyerService;
import com.muyi.mpdemo.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * @Author: muyi
 * @Date: Created in 14:28 2017/11/14
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/wechat/web")
@EnableConfigurationProperties(ProjectProperties.class)
public class WechatAuthController {

    @Autowired
    private HttpSessionAdvice httpSession;

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private ProjectProperties projectProperties;

    @GetMapping("/authorize")
    public String wechatAuth(){
        String sessionID = httpSession.getID();
        //log.info("【/authorize->sessionID】:{}",sessionID);

        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl
                        (projectProperties.getUrl().getAuthUserInfo(),
                         WxConsts.OAUTH2_SCOPE_USER_INFO,
                         URLEncoder.encode(sessionID));
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String wechatIndex(@RequestParam("code") String code,
                           @RequestParam("state") String sessionID) throws MpException{

        String ss = httpSession.getID();

        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try{
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            //获取userinfo
            WxMpUser user = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken,"zh_CN");
            log.info("【网页授权用户信息】:{}", JsonUtil.toPrettyJson(user));

            //若是新用户 将他作为一个buyer保存
            Buyer buyer = new Buyer();
            buyer.setBuyerName(user.getNickname());
            buyer.setBuyerPass("1234");
            buyer.setOpenID(user.getOpenId());
            buyer.setUnionID(user.getUnionId());
            String buyerID = buyerService.createBuyer(buyer);

            httpSession.setBuyerID(buyerID);
            httpSession.setOpenID(user.getOpenId());

        }catch (WxErrorException e){
            throw new MpException(e);
        }
        String openID = wxMpOAuth2AccessToken.getOpenId();

        return "redirect:" + projectProperties.getUrl().getIndex() + "?openID=" + openID;
    }









}
