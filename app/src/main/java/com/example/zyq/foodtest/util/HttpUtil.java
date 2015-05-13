package com.example.zyq.foodtest.util;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by ZYQ on 2015/4/29 0029.
 */
public class HttpUtil {

    final static String domain = "http://foodieworld.sinaapp.com";

    public static void doGet(final String address, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    Log.d("response", "httpGet:" );
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet(domain + address);
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        //请求和响应成功
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity, "utf-8");
//                        Log.d("response", "httpGet: " + response);//

                        if (listener != null) {
                            listener.onFinish(response.toString());
                        }
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                }
            }
        }).start();
    }

    public static void doPost(final String address, final List<NameValuePair> params, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(domain + address);
                try {
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "utf-8");
                    httpPost.setEntity(entity);
                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    String response = EntityUtils.toString(httpEntity);
                    Log.d("response", response);
                    if (listener != null) {
                        listener.onFinish(response.toString());
                    }
                } catch (UnsupportedEncodingException e) {
                    listener.onError(e);
                } catch (IOException e) {
                    listener.onError(e);
                }
            }

        }).start();
    }

}
