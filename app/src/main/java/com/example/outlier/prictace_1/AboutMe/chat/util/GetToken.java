package com.example.outlier.prictace_1.AboutMe.chat.util;




import android.util.Log;

import com.example.outlier.prictace_1.BaseApplication;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by outlier on 2017/7/22.
 */

public class GetToken {

    public static void GetRongCloudToken(final String username, final String usrid) {
        final String url = "https://api.cn.ronghub.com/user/getToken.json";
        final String App_Key = "0vnjpoad0eyrz"; //开发者平台分配的 App Key。
        String App_Secret = "ZlpdEliqgZGyF";
        final String Timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳，从 1970 年 1 月 1 日 0 点 0 分 0 秒开始到现在的秒数。
        final String Nonce = String.valueOf(Math.floor(Math.random() * 1000000));//随机数，无长度限制。
        final String Signature = sha1(App_Secret + Nonce + Timestamp);//数据签名。

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient httpClient = new OkHttpClient();
                    RequestBody responsebody = new FormBody.Builder()
                            .add("name",username)
                            .add("userId",usrid)
                            .add("portraitUri","")
                            .build();
                    Request request = new Request.Builder()
                            .url(url)
                            .addHeader("App-Key", App_Key)
                            .addHeader("Timestamp", Timestamp)
                            .addHeader("Nonce", Nonce)
                            .addHeader("Signature", Signature)
                            .addHeader("Content-Type", "application/x-www-form-urlencoded")
                            .post(responsebody)
                            .build();

                    Response response = null;
                    response = httpClient.newCall(request).execute();
                    Log.e("response",""+response);

                    Log.e(" response.body().string()",""+ response.body().string());
                    Gson gs = new Gson();
                    Entity entity;
                    Class<Entity> entityClass = Entity.class;
                    String text = response.body().string();
                    entity= gs.fromJson(text,entityClass);
                    BaseApplication.set("rongyun",entity.getToken());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            }).start();
    }
    //SHA1加密//http://www.rongcloud.cn/docs/server.html#通用_API_接口签名规则
    private static String sha1(String data){
        StringBuffer buf = new StringBuffer();
        try{
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(data.getBytes());
            byte[] bits = md.digest();
            for(int i = 0 ; i < bits.length;i++){
                int a = bits[i];
                if(a<0) a+=256;
                if(a<16) buf.append("0");
                buf.append(Integer.toHexString(a));
            }
        }catch(Exception e){

        }
        return buf.toString();
    }
}
