package com.example.zyq.foodtest.util;

/**
 * Created by ZYQ on 2015/5/4 0004.
 */

import android.graphics.Bitmap;

import org.apache.http.impl.client.DefaultHttpClient;

import java.util.concurrent.ExecutionException;

public class HttpClient extends DefaultHttpClient {
    private static HttpClient ourInstance = new HttpClient();

    public static HttpClient getInstance() {
        return ourInstance;
    }

    private HttpClient() {
        super();
    }

    private String domain = "http://foodieworld.sinaapp.com";

    public String getDomain() {
        return domain;
    }

    public String httpGet(String... params) {
        try {
            return new Get().execute(params).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Bitmap httpImage(String... params) {
        try {
            return new Image().execute(params).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected String httpPost(String... params) {
        try {
            return new Post().execute(params).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}