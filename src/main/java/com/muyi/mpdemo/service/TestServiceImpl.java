package com.muyi.mpdemo.service;

import org.springframework.stereotype.Service;

/**
 * @Author: muyi
 * @Date: Created in 10:54 2017/11/17
 * @Description:
 */
@Service("testService")
public class TestServiceImpl implements TestService {
    @Override
    public String test() {
        return "test";
        //throw new BizException(ResultEnum.USER_NOT_FOUND);
    }
}
