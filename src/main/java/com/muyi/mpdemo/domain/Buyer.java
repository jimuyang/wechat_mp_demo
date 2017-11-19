package com.muyi.mpdemo.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
public class Buyer {

//    @Id
//    @Column(name = "userID")
    private String buyerID;

    private String buyerName;

    private String buyerPass;

    private BigDecimal accountBalance;

}
