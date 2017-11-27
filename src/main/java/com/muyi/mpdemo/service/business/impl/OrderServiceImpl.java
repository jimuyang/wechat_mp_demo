package com.muyi.mpdemo.service.business.impl;

import com.muyi.mpdemo.component.HttpSessionAdvice;
import com.muyi.mpdemo.dao.OrderDao;
import com.muyi.mpdemo.domain.OrderHead;
import com.muyi.mpdemo.dto.CreateOrderReq;
import com.muyi.mpdemo.enums.OrderStatusEnum;
import com.muyi.mpdemo.service.business.OrderService;
import com.muyi.mpdemo.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Author: muyi
 * @Date: Created in 18:37 2017/11/20
 * @Description:
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private HttpSessionAdvice httpSession;


    @Override
    public OrderHead getOrderInfo(String orderID) {
        return orderDao.selectOne(orderID);
    }

    @Override
    public String createOrder(CreateOrderReq coReq) {
        OrderHead orderHead = new OrderHead();
        //生成订单ID
        String orderID = KeyUtil.UUID();
        //计算价格
        BigDecimal totalPrice = new BigDecimal(20);

        orderHead.setBuyerID(httpSession.getBuyerID());
        orderHead.setOrderContent(coReq.getOrderContent());
        orderHead.setOrderID(orderID);
        orderHead.setSellerID(coReq.getSellerID());
        orderHead.setOrderType(coReq.getOrderType());
        orderHead.setTotalPrice(totalPrice);
        orderHead.setOrderStatus(OrderStatusEnum.NEW.getCode());

        orderDao.insertOne(orderHead);

        return orderID;
    }

    @Override
    public boolean payOrder(String orderID) {
        return false;
    }
}
