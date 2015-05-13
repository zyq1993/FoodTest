package com.example.zyq.foodtest.util;

/**
 * Created by ZYQ on 2015/5/4 0004.
 */

import org.apache.http.impl.client.DefaultHttpClient;

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
}

