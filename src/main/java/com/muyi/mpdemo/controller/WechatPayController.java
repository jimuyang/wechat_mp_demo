package com.muyi.mpdemo.controller;


import com.github.binarywang.wxpay.bean.request.*;
import com.github.binarywang.wxpay.bean.result.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.muyi.mpdemo.domain.OrderHead;
import com.muyi.mpdemo.service.business.PayService;
import com.muyi.mpdemo.service.business.impl.PayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@Slf4j
@Controller
@RequestMapping("/pay")
public class WechatPayController {


}
