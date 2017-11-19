package com.muyi.mpdemo.service;

import com.muyi.mpdemo.enums.ResultEnum;
import com.muyi.mpdemo.exception.BizException;
import com.muyi.mpdemo.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @Author: muyi
 * @Date: Created in 10:54 2017/11/17
 * @Description:
 */
@Service("testService")
public class TestServiceImpl implements TestService {
    @Override
    public void test() {
        throw new BizException(ResultEnum.USER_NOT_FOUND);
    }
}
