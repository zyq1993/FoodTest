package com.example.zyq.foodtest;

/**
 * Created by ZYQ on 2015/4/29 0029.
 */
public class User {

    private int userId;
    private String userName;
    private String userAddress;

    public User(int userId, String userName, String userAddress) {
        this.userId = userId;
        this.userName = userName;
        this.userAddress = userAddress;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

}
