package com.example.zyq.foodtest.model;

/**
 * Created by ZYQ on 2015/4/11 0011.
 */

//餐馆的类
public class Restaurant {

    private String restaurantId;
    private String restaurantName;//餐馆名称
    private String restaurantImage;//餐馆的配图
    private String restaurantAddress;
    //评价等未加
    public Restaurant() {

    }
    public Restaurant(String restaurantId, String restaurantName, String restaurantImage, String restaurantAddress) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantImage = restaurantImage;
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantImage() {
        return restaurantImage;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setRestaurantImage(String restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }


}
