package com.example.zyq.foodtest.model;


import java.io.Serializable;

/**
 * Created by ZYQ on 2015/4/14 0014.
 */
public class Food implements Serializable {

    private String foodId;//食物的编号
    private String foodName;
    private String foodImage;
    private String foodPrice;
    private String foodNumber;//订下的数量
    private static final long serialVersionUID = 1L;

    public Food(String foodId, String foodName, String foodImage, String foodPrice) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.foodPrice = foodPrice;
        this.foodNumber= "0";
    }

    public Food() {
        this.foodNumber= "0";
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public void setFoodNumber(String foodNumber) {
        this.foodNumber = foodNumber;
    }

    public String getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public String getFoodNumber() {
        return foodNumber;
    }
}
