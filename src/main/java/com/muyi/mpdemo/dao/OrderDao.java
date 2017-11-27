package com.muyi.mpdemo.dao;

import com.muyi.mpdemo.domain.OrderHead;
import org.apache.ibatis.annotations.*;

public interface OrderDao {

    @Insert("insert into `order_head` " +
            "values (UUIDTOBIN(#{orderID}),#{orderType},#{orderContent},#{totalPrice},#{orderStatus},#{buyerID},#{sellerID},null) ;")
    int insertOne(OrderHead orderHead);

    @Select("select BINTOUUID(orderID) as orderID,orderType,orderContent,totalPrice,orderStatus,BINTOUUID(buyerID) as buyerID,BINTOUUID(sellerID) as sellerID,createTime " +
            "from `order_head` where orderID = UUIDTOBIN(#{orderID}) limit 1 ;")
    OrderHead selectOne(@Param("orderID") String orderID);

    @Select("select BINTOUUID(orderID) as orderID,orderType,orderContent,totalPrice,orderStatus,BINTOUUID(buyerID) as buyerID,BINTOUUID(sellerID) as sellerID,createTime " +
            "from `order_head` where orderID = UUIDTOBIN(#{orderID}) limit 1 for update ;")
    OrderHead selectOneLock(@Param("orderID") String orderID);

    @Update("update `order_head` set orderStatus = #{orderStatus} " +
            "where orderID = UUIDTOBIN(#{orderID}) ;")
    int updateOneStatus(OrderHead orderHead);

    @Update("update order_head set orderStatus = -1 where orderID = #{orderID} ;" )
    int timeout(@Param("orderID")String orderID);

    @Delete("delete from `order_head` " +
            "where orderID = UUIDTOBIN(#{orderID}) ;")
    int deleteOne(@Param("orderID")String orderID);

}
