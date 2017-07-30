package com.example.outlier.prictace_1.AboutMe.myFriend.Toolkit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by outlier on 2017/7/24.
 */

public class IntentHelper {
    private static UrlEncodedFormEntity entity;
    private static HttpClient httpClient = new DefaultHttpClient();
    private static HttpPost httpPost;
    private static HttpResponse httpResponse;
    private static String res = null;

    public static String getData(final String url, final List<NameValuePair> valuePairs){
        res = null;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                httpPost = new HttpPost(url);
                try {
                    entity = new UrlEncodedFormEntity(valuePairs,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    httpPost.setEntity(entity);
                    httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
                    httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,3000);
                    httpResponse = httpClient.execute(httpPost);
                    if(httpResponse.getStatusLine().getStatusCode() == 200){
                        HttpEntity httpEntity = httpResponse.getEntity();
                        try {
                            res = EntityUtils.toString(httpEntity,"utf-8");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return res;
    }

}
