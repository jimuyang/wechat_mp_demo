package com.muyi.mpdemo.annotation;

import lombok.Data;

@Data
public class Apple {

    @FruitName("apple")
    private String appleName;

    @FruitColor(fruitColor = FruitColor.Color.RED)
    private String appleColor;


}
