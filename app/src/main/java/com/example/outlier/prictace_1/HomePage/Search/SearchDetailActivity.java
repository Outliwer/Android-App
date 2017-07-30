package com.example.outlier.prictace_1.HomePage.Search;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.outlier.prictace_1.BaseApplication;
import com.example.outlier.prictace_1.HomePage.Guide.GuideActivity;
import com.example.outlier.prictace_1.HomePage.Guide.WayGuideActivity;
import com.example.outlier.prictace_1.HomePage.Hotel.model.EuclidListAdapter;
import com.example.outlier.prictace_1.HomePage.Hotel.model.StringEuclidActivity;
import com.example.outlier.prictace_1.HomePage.Search.CycleView.ADInfo;
import com.example.outlier.prictace_1.HomePage.Search.CycleView.CycleViewPager;
import com.example.outlier.prictace_1.HomePage.Search.CycleView.ViewFactory;
import com.example.outlier.prictace_1.HttpUtil;
import com.example.outlier.prictace_1.R;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOError;
import java.io.IOException;
import java.io.StringReader;
import java.net.ResponseCache;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



/**
 * Created by outlier on 2017/7/12.
 */

public class SearchDetailActivity extends StringEuclidActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }

    }

    @Override
    protected BaseAdapter getAdapter() {
        Map<String, Object> profileMap;
        List<Map<String, Object>> profilesList = new ArrayList<>();
        Intent intent = getIntent();
        String[] picture = intent.getStringArrayExtra("picture");
        String[] value = intent.getStringArrayExtra("name");
        String[] summary = intent.getStringArrayExtra("summary");
        String[] my_address = intent.getStringArrayExtra("my_address");
        String[] opentime = intent.getStringArrayExtra("opentime");
        String[] coupon = intent.getStringArrayExtra("coupon");
        String[] attention = intent.getStringArrayExtra("attention");
        String[] content = intent.getStringArrayExtra("content");
        String[] lat = intent.getStringArrayExtra("lat");
        String[] lon = intent.getStringArrayExtra("lon");
        BaseApplication.set("type","jingdian");

        for (int i = 0; i < picture.length; i++) {
            profileMap = new HashMap<>();
            profileMap.put(EuclidListAdapter.KEY_AVATAR, picture[i]);
            profileMap.put(EuclidListAdapter.KEY_NAME, value[i]);
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_SHORT, summary[i]
                    + "  开放时间:" + opentime[i]);
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_FULL, content[i] + "\n" + coupon[i]);
            profileMap.put("lon",lon[i]);
            profileMap.put("lat",lat[i]);
            profileMap.put("address",my_address[i]);
            profilesList.add(profileMap);
        }

        return new EuclidListAdapter(this, R.layout.list_item, profilesList);
    }


}