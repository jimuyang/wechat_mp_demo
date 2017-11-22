package com.muyi.mpdemo.service.business;

import com.muyi.mpdemo.dto.CreateOrderReq;

/**
 * @Author: muyi
 * @Date: Created in 17:56 2017/11/20
 * @Description:
 */
public interface OrderService {

    //获取订单详情

    //buyer获取订单列表

    //seller获取订单列表

    //buyer创建订单
    String createOrder(CreateOrderReq coReq);

    //buyer支付订单
    boolean payOrder(String orderID);

    //buyer取消订单

    //seller接单

    //seller叫号

    //seller结束订单

    //seller废弃订单

    //seller发货

}
