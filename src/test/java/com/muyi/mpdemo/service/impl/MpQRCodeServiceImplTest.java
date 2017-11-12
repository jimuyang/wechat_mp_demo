package com.muyi.mpdemo.service.impl;

import com.muyi.mpdemo.service.MpQRCodeService;
import com.muyi.mpdemo.wxhandler.ScanHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MpQRCodeServiceImplTest {

    @Autowired
    private MpQRCodeService mpQRCodeService;
    @Test
    public void getLastMpQRCode() throws Exception {
        String shopID = "shop001";
        String sceneStr = ScanHandler.TABLE_SCENE_PREFIX + "001" + "_" + shopID;
        //mpQRCodeService.getLastMpQRCode(1);
        mpQRCodeService.getLastMpQRCode(sceneStr);
    }

}