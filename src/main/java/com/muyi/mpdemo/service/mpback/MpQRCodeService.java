package com.muyi.mpdemo.service.mpback;

import com.muyi.mpdemo.exception.MpException;

import java.io.IOException;

/**
 * @Author: muyi
 * @Date: Created in 11:15 2017/11/10
 * @Description:
 */
public interface MpQRCodeService {

    void getLastMpQRCode(int sceneID) throws MpException;

    void getLastMpQRCode(String sceneStr) throws IOException,MpException;


}
