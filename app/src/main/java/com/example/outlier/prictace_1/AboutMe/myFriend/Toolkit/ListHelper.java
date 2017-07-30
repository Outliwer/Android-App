package com.example.outlier.prictace_1.AboutMe.myFriend.Toolkit;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by outlier on 2017/7/24.
 */

public class ListHelper {
    private static List<NameValuePair> list = new ArrayList<NameValuePair>();
    public static void addData(String key,String value){
        list.add(new BasicNameValuePair(key,value));
    }
    public static void clearData(){
        list.clear();
    }
    public static List<NameValuePair> getList(){
        return list;
    }

}