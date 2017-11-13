package com.muyi.mpdemo.service;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @Author: muyi
 * @Date: Created in 10:38 2017/11/13
 * @Description:
 */

public interface MpMenuService {


    boolean createDefaultMenu() throws IOException;

    WxMpMenu getMenu();


}
