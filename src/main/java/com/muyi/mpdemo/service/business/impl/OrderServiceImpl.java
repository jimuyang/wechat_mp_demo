package com.muyi.mpdemo.service.business.impl;

import com.muyi.mpdemo.dao.OrderDao;
import com.muyi.mpdemo.service.business.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: muyi
 * @Date: Created in 18:37 2017/11/20
 * @Description:
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    
    public void test(){

    }

}
