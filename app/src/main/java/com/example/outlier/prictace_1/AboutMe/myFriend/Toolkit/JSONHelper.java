package com.example.outlier.prictace_1.AboutMe.myFriend.Toolkit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by outlier on 2017/7/24.
 */

public class JSONHelper {
    public static JSONObject getJSONObj(String json){
        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static JSONArray getJSONArray(String json){
        try {
            return new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getObjString(JSONObject jsonObject,String key){
        try {
            return jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
