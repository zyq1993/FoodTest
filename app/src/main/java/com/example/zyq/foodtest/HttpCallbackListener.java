package com.example.zyq.foodtest;

/**
 * Created by ZYQ on 2015/4/29 0029.
 */
public interface HttpCallbackListener {

    void onFinish(final String response);

    void onError(Exception e);
}
