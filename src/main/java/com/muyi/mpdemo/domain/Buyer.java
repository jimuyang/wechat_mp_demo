package com.muyi.mpdemo.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
public class Buyer {

    private String buyerID;

    private String openID;
    private String unionID;

    private String buyerName;

    private String buyerPass;

    private BigDecimal accountBalance;

}
