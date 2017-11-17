package com.muyi.mpdemo.service.impl;

import com.muyi.mpdemo.exception.MpException;
import com.muyi.mpdemo.service.MpMenuService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

/**
 * @Author: muyi
 * @Date: Created in 10:39 2017/11/13
 * @Description:
 */

@Slf4j
@Service("mpMenuService")
public class MpMenuServiceImpl implements MpMenuService {

    public static final String MENU_JSON_PATH = "static/json/menu.json";


    @Autowired
    private WxMpService wxMpService;


    public boolean createDefaultMenu() throws IOException{

        WxMpMenuService menuService = wxMpService.getMenuService();

        try{
            //创建默认菜单
            File menuFile = ResourceUtils.getFile
                    (ResourceUtils.CLASSPATH_URL_PREFIX + MENU_JSON_PATH);
            String menuJson = FileUtils.readFileToString(menuFile,"utf-8");

            log.info(menuService.menuCreate(menuJson));

        }catch (WxErrorException e){
            throw new MpException(e);
        }
        return true;
    }

    public WxMpMenu getMenu(){
        try{
            return wxMpService.getMenuService().menuGet();
        }catch (WxErrorException e){
            throw new MpException(e);
        }
    }









}
