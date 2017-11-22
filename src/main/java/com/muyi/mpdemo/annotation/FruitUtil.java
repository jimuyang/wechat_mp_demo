package com.muyi.mpdemo.annotation;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class FruitUtil {

    public static void getFruitInfo(Class<?> clazz){

        Field[] fields = clazz.getDeclaredFields();


        for(Field field :fields){
            if(field.isAnnotationPresent(FruitName.class)){
                FruitName fruitName = (FruitName) field.getAnnotation(FruitName.class);
                log.info("Fruit Name:{}",fruitName.value());
            }
            else if(field.isAnnotationPresent(FruitColor.class)){
                FruitColor fruitColor= (FruitColor) field.getAnnotation(FruitColor.class);
                log.info("Fruit Color:{}",fruitColor.fruitColor().toString());
            }
        }
    }
}
