package com.muyi.mpdemo.frame;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: muyi
 * @Date: Created in 12:57 2017/11/9
 * @Description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {

    private boolean status;
    private int code;
    private String message;
    private Object data;
}
