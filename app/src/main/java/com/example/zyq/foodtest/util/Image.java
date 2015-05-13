package com.example.zyq.foodtest.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by ZYQ on 2015/5/13 0013.
 */
public class Image extends AsyncTask<String, Integer, Bitmap> {
    @Override
    protected Bitmap doInBackground(String... params) {
        HttpClient client = HttpClient.getInstance();
        HttpGet httpGet = new HttpGet(client.getDomain() + params[0]);
        try {
            HttpResponse httpResponse = client.execute(httpGet);
            byte[] byteArray = EntityUtils.toByteArray(httpResponse.getEntity());
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

