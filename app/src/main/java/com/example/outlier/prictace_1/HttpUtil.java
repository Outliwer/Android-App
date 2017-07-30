package com.example.outlier.prictace_1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by outlier on 2017/7/13.
 */



public class HttpUtil {

    public static void sendHttpRequest(final String address,final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while (null != (line = reader.readLine())) {
                        response.append(line);
                    }
                    if (null != listener) {
                        // 回调onFinish()方法
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (null != listener) {
                        listener.onError(e);
                    }
                } finally {
                    if (null != connection) {
                        connection.disconnect();
                    }
                }
            }
        }).start();

    }
}

/*
    HttpUtil.sendHttpRequest(address , new HttpCallbackListrner(){
        @Override
        public void onFinish(String response){
            //根据返回内容执行具体的逻辑
        }
        @Override
        public void onError(Exception e){
            //对异常情况进行处理
        }
    });
 */