package com.muyi.mpdemo.annotation;


import com.muyi.mpdemo.enums.CodeEnum;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {
    //颜色枚举
    enum Color{BLUE,RED,GREEN,YELLOW}

    Color fruitColor() default Color.RED;

}
