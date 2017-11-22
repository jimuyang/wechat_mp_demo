package com.muyi.mpdemo.dto;

import lombok.Data;

@Data
public class CreateOrderReq {


    private Integer orderType;

    private String orderContent;

    private String sellerID;

}
