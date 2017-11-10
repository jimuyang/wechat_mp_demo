package com.muyi.mpdemo.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: muyi
 * @Date: Created in 15:13 2017/11/8
 * @Description:
 */

@Data
public class TestUser implements Serializable{

    private String userID;
    private String username;
    private String password;


}
