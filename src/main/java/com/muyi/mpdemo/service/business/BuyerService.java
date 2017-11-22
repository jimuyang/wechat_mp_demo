package com.muyi.mpdemo.service.business;

import com.muyi.mpdemo.domain.Buyer;

/**
 * @Author: muyi
 * @Date: Created in 17:56 2017/11/20
 * @Description:
 */
public interface BuyerService {

    /**
     * 创建一个buyer
     * @param buyer
     * @return buyerID
     */
    String createBuyer(Buyer buyer);

    /**
     * 查看用户详情
     * @return buyer
     */
    Buyer getBuyerInfo();

    //修改密码等功能

    //修改账户余额

}
