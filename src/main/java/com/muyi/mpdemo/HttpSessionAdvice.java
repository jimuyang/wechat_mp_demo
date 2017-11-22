package com.muyi.mpdemo;


import com.muyi.mpdemo.enums.ResultEnum;
import com.muyi.mpdemo.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class HttpSessionAdvice {
    private static final String BUYERID = "buyerID";
    private static final String OPENID = "openID";

    @Autowired
    private HttpSession httpSession;

    public String getID(){
        return httpSession.getId();
    }

    public String getBuyerID(){
        return this.getString(BUYERID);
    }

    public String getOpenID(){
        return this.getString(OPENID);
    }

    public void setBuyerID(String buyerID){
        this.setString(BUYERID,buyerID);
    }
    public void setOpenID(String openID){
        this.setString(BUYERID,openID);
    }

    public String getString(String key){
        Object o = httpSession.getAttribute(key);
        if(o == null){
            throw new BizException(ResultEnum.LOGIN_FIRST);
        }
        return o.toString();
    }
    public void setString(String key,String val){
        httpSession.setAttribute(key,val);
    }







}
