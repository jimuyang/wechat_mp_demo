package com.muyi.mpdemo.dao;

import com.muyi.mpdemo.domain.Buyer;
import org.apache.ibatis.annotations.*;


import java.math.BigDecimal;

public interface BuyerDao {

    @Insert("insert into `buyer`(buyerID,buyerName,buyerPass,accountBalance) " +
            "values(UUIDTOBIN(#{buyerID}),#{buyerName},#{buyerPass},#{accountBalance}) ;")
    int insertOne(Buyer buyer);

    @Select("select BINTOUUID(buyerID) as buyerID,buyerName,buyerPass,accountBalance " +
            "from `buyer` where buyerID = UUIDTOBIN(#{buyerID}) limit 1 ;")
    Buyer selectOne(@Param("buyerID") String buyerID);

    @Select("select BINTOUUID(buyerID) as buyerID,buyerName,buyerPass,accountBalance " +
            "from `buyer` where buyerID = UUIDTOBIN(#{buyerID}) limit 1 for update ;")
    Buyer selectOneLock(@Param("buyerID") String buyerID);


    @Update("update `buyer` set accountBalance = #{newBalance} " +
            "where buyerID = UUIDTOBIN(#{buyerID}) ;")
    int updateAcctBalance(@Param("buyerID") String buyerID,
                          @Param("newBalance") BigDecimal newBalance);


}

