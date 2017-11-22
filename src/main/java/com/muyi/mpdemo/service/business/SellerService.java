package com.muyi.mpdemo.service.business;

import com.muyi.mpdemo.domain.Seller;
/**
 * @Author: muyi
 * @Date: Created in 17:57 2017/11/20
 * @Description:
 */
public interface SellerService {

    /**
     * 创建一个seller
     * @param seller
     * @return sellerID
     */
    String createSeller(Seller seller);


}
