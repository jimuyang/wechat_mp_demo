package com.muyi.mpdemo.controller;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.muyi.mpdemo.domain.OrderHead;
import com.muyi.mpdemo.service.business.PayService;
import com.muyi.mpdemo.service.business.impl.PayServiceImpl;
import com.muyi.mpdemo.utils.JsonUtil;
import com.sun.deploy.net.cookie.CookieUnavailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@Controller
//@RequestMapping("/pay")
public class WechatPayController {


    @Autowired
    private BestPayServiceImpl bestPayService;

    /**
     * 支付请求中转
     *
     * 接受来自米趣服务器的支付请求，并在cookie中设置orderID?这样可行吗
     * 之后转发到  http://sell.springboot.cn/sell/pay?openid=oTgZpwXr5pg2eSS99pcYBwYiaVvY
     * 它会转发到 本controller下的pay方法
     */
    @GetMapping(value = "/transferPay")
    public void transferPay(@RequestParam String orderID,
                            @RequestParam String price,
                            HttpServletResponse response,
                            HttpServletRequest request) throws Exception {
//        Cookie cookie1 = new Cookie("orderID",orderID);
//        Cookie cookie2 = new Cookie("price",price);
//        response.addCookie(cookie1);
//        response.addCookie(cookie2);
        response.sendRedirect("http://sell.springboot.cn/sell/pay?openid=oTgZpwXr5pg2eSS99pcYBwYiaVvY");
    }

    /**
     * 发起支付
     */
    @GetMapping(value = "/pay")
    public ModelAndView pay(@RequestParam("openid") String openid,
                            HttpServletRequest request,
                            Map<String, Object> map) {
        Cookie[] cookies = request.getCookies();
        Map<String,String> cookieMap = new HashMap<>();

        if(cookies == null || cookies.length == 0){
            log.info("【发起支付cookie】 为空");
        }else{
            for (Cookie cookie: cookies) {
                log.info("【发起支付cookie】{}: {}",cookie.getName(),cookie.getValue());
                cookieMap.put(cookie.getName(),cookie.getValue());
            }
        }
        PayRequest payRequest = new PayRequest();
        Random random = new Random();
        //支付请求参数
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        payRequest.setOrderId(String.valueOf(random.nextInt(1000000000)));
        payRequest.setOrderAmount(0.01);
        payRequest.setOrderName("最好的支付sdk");
        payRequest.setOpenid(openid);
        log.info("【发起支付】request={}", JsonUtil.toJson(payRequest));

        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【发起支付】response={}", JsonUtil.toJson(payResponse));

        map.put("payResponse", payResponse);

        return new ModelAndView("pay/create", map);
    }

    /**
     * 异步回调
     */
    @PostMapping(value = "/pay/notify")
    public ModelAndView notify(@RequestBody String notifyData) throws Exception {
        log.info("【异步回调】request={}", notifyData);
        PayResponse response = bestPayService.asyncNotify(notifyData);
        log.info("【异步回调】response={}", JsonUtil.toPrettyJson(response));

        return new ModelAndView("pay/success");
    }


}
