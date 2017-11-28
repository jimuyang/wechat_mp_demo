package com.muyi.mpdemo.service.business;

import com.github.binarywang.wxpay.bean.request.*;
import com.github.binarywang.wxpay.bean.result.*;
import com.github.binarywang.wxpay.exception.WxPayException;

import java.io.File;

/**
 * @Author: muyi
 * @Date: Created in 14:44 2017/11/28
 * @Description:
 */
@Deprecated
public interface PayService {

    WxPayOrderQueryResult queryOrder(String transactionId, String orderID)
            throws WxPayException;

    WxPayOrderCloseResult closeOrder(String orderID);

    WxPayUnifiedOrderResult unifiedOrder(WxPayUnifiedOrderRequest request) throws WxPayException;

    WxPayRefundResult refund(WxPayRefundRequest request) throws WxPayException;

    WxPayRefundQueryResult refundQuery(String transactionId, String orderID, String outRefundNo, String refundId)
            throws WxPayException;

    WxPaySendRedpackResult sendRedpack(WxPaySendRedpackRequest request) throws WxPayException;

    WxPayRedpackQueryResult queryRedpack(String mchBillNo) throws WxPayException;

    byte[] createScanPayQrcodeMode1(String productId, File logoFile, Integer sideLength);

    String createScanPayQrcodeMode1(String productId);

    byte[] createScanPayQrcodeMode2(String codeUrl, File logoFile, Integer sideLength);

    void report(WxPayReportRequest request) throws WxPayException;

    WxPayBillResult downloadBill(String billDate, String billType,
                                 String tarType, String deviceInfo) throws WxPayException;

    WxPayMicropayResult micropay(WxPayMicropayRequest request) throws WxPayException;

    WxPayOrderReverseResult reverseOrder(WxPayOrderReverseRequest request) throws WxPayException;

    String shorturl(WxPayShorturlRequest wxPayShorturlRequest) throws WxPayException;

    String shorturl(String s) throws WxPayException;

    String authcode2Openid(WxPayAuthcode2OpenidRequest wxPayAuthcode2OpenidRequest) throws WxPayException;

    String authcode2Openid(String s) throws WxPayException;
}
