package com.example.zyq.foodtest.util;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by ZYQ on 2015/5/13 0013.
 */
public class Get extends AsyncTask<String, Integer, String> {
    @Override
    protected String doInBackground(String... params) {
        HttpClient client = HttpClient.getInstance();
        HttpGet httpGet = new HttpGet(client.getDomain() + params[0]);
        try {
            HttpResponse httpResponse = client.execute(httpGet);
            return EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
