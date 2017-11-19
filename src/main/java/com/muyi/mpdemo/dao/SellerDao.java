package com.muyi.mpdemo.dao;

import com.muyi.mpdemo.domain.Seller;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SellerDao {

    @Insert("insert into `seller` " +
            "values(UUIDTOBIN(#{sellerID}),#{sellerName},#{sellerPass},#{sellerType}) ;")
    int insertOne(Seller seller);

    @Select("select BINTOUUID(sellerID) as sellerID,sellerName,sellerPass,sellerType " +
            "from `seller` where sellerID = UUIDTOBIN(#{sellerID}) limit 1 ;")
    Seller selectOne(@Param("sellerID")String sellerID);

    @Select("select BINTOUUID(sellerID) as sellerID,sellerName,sellerPass,sellerType " +
            "from `seller` where sellerID = UUIDTOBIN(#{sellerID}) limit 1 for update ;")
    Seller selectOneLock(@Param("sellerID")String sellerID);

    int updateOne(Seller seller);

    int deleteOne(@Param("sellerID")String sellerID);


}
