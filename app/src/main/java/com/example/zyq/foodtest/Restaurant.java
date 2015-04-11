package com.example.zyq.foodtest;

/**
 * Created by ZYQ on 2015/4/11 0011.
 */

//餐馆的类
public class Restaurant {

    private String name;//餐馆名称
    private int imageId;//餐馆的配图

    public Restaurant(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

}
