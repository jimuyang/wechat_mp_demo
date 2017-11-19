package com.muyi.mpdemo.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderHead {

    private String orderID;

    private String orderContent;

    private Integer orderType;

    private BigDecimal totalPrice;

    private Integer orderStatus;

    private String buyerID;
    private String sellerID;


    private Date createTime;

}
