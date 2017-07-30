package com.example.outlier.prictace_1.HomePage.Guide;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.SupportMapFragment;
import com.baidu.mapapi.model.LatLng;
import com.example.outlier.prictace_1.R;



public class GuideActivity extends FragmentActivity {
    //初始化标题栏
    private ImageView titleLeftImv;
    private TextView titleTv;
    private static LatLng GEO_GUANGZHOU = new LatLng(23.155, 113.264);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_guide);
        // 初始化页面标题栏
        titleLeftImv = (ImageView) findViewById(R.id.homepage_title_imv);
        titleLeftImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleTv = (TextView) findViewById(R.id.homepage_title_text_tv);
        titleTv.setText("地图");
        Intent intent = getIntent();
        GEO_GUANGZHOU = new LatLng(Double.parseDouble(intent.getStringExtra("lat")),Double.parseDouble(intent.getStringExtra("lon")));
        initMap();
    }

    /**
     * 初始化Map,设置不同城市为地图中心点，设置Logo不同位置
     */
    private void initMap() {
        /*北京为地图中心，logo在左上角*/
        MapStatusUpdate u1 = MapStatusUpdateFactory.newLatLng(GEO_GUANGZHOU);
        SupportMapFragment map1 = (SupportMapFragment) (getSupportFragmentManager()
                .findFragmentById(R.id.map1));
        map1.getBaiduMap().setMapStatus(u1);
        map1.getMapView().setLogoPosition(LogoPosition.logoPostionleftTop);

    }

}
