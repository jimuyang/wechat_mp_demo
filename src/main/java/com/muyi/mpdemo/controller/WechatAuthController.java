package com.muyi.mpdemo.controller;

import com.muyi.mpdemo.config.properties.ProjectProperties;
import com.muyi.mpdemo.config.properties.WechatProperties;
import com.muyi.mpdemo.exception.MpException;
import com.muyi.mpdemo.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

/**
 * @Author: muyi
 * @Date: Created in 14:28 2017/11/14
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/wechat/web")
public class WechatAuthController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private WxMpService wxMpService;

    @Value("${project.url.authUserInfo}")
    private String authUserInfoUrl;

    @Value("${project.url.index}")
    private String indexUrl;

    @GetMapping("/authorize")
    public String wechatAuth(){
        String sessionID = httpSession.getId();
        log.info("【/authorize->sessionID】:{}",sessionID);

        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl
                (authUserInfoUrl, WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode(sessionID));
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String wechatIndex(@RequestParam("code") String code,
                           @RequestParam("state") String sessionID){

        String ss = httpSession.getId();
        log.info("【/userInfo->sessionID】:{}",ss);
        log.info("【/authorize->sessionID】:{}",sessionID);

        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try{
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            //获取userinfo
            WxMpUser user = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken,"zh_CN");
            log.info("【网页授权用户信息】:{}", JsonUtil.toPrettyJson(user));

        }catch (WxErrorException e){
            throw new MpException(e);
        }
        String openID = wxMpOAuth2AccessToken.getOpenId();

        return "redirect:" + indexUrl + "?openID=" + openID;
    }









}
