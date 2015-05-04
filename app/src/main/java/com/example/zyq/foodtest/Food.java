package com.example.zyq.foodtest;

/**
 * Created by ZYQ on 2015/4/14 0014.
 */
public class Food {

    private int foodId;//食物的编号
    private String foodName;
    private int foodImage;
    private float foodPrice;

    //个数等未加
    public Food(int foodId, String foodName, int foodImage, float foodPrice) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.foodPrice = foodPrice;
    }

    public int getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getFoodImage() {
        return foodImage;
    }

    public float getFoodPrice() {
        return foodPrice;
    }

}
