package com.example.zyq.foodtest.util;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZYQ on 2015/5/13 0013.
 */
public class Post extends AsyncTask<String, Integer, String> {
    @Override
    protected String doInBackground(String... params) {
        HttpClient client = HttpClient.getInstance();
        HttpPost httpPost = new HttpPost(client.getDomain() + params[0]);
        try {
            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
            for (int i = 1; i < params.length; i += 2) {
                nameValuePairList.add(new BasicNameValuePair(params[i], params[i + 1]));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList, "utf-8"));

            HttpResponse httpResponse = client.execute(httpPost);
            return EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
