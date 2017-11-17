package com.muyi.mpdemo.exception;

import com.muyi.mpdemo.config.properties.WechatProperties;
import com.muyi.mpdemo.enums.ResultEnum;
import lombok.Data;
import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * @Author: muyi
 * @Date: Created in 12:50 2017/11/9
 * @Description: 公众号相关异常
 */

@Data
public class MpException extends RuntimeException {

    private int code;

    public MpException(int code){
        super();
        this.code = code;
    }

    public MpException(WxErrorException wxe){
        super(wxe.getError().getErrorMsg());
        this.code = wxe.getError().getErrorCode();
    }
}
