package com.muyi.mpdemo.annotation;

import org.junit.Test;

import static org.junit.Assert.*;

public class FruitUtilTest {
    @Test
    public void getFruitInfo() throws Exception {
        FruitUtil.getFruitInfo(Apple.class);
    }

}