package com.example.zyq.foodtest;

/**
 * Created by ZYQ on 2015/4/11 0011.
 */

//餐馆的类
public class Restaurant {

    private int restaurantId;
    private String restaurantName;//餐馆名称
    private int restaurantImage;//餐馆的配图
    private String restaurantAddress;
    //评价等未加
    public Restaurant(int restaurantId, String restaurantName, int restaurantImage, String restaurantAddress) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantImage = restaurantImage;
        this.restaurantAddress = restaurantAddress;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public int getRestaurantImage() {
        return restaurantImage;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

}
