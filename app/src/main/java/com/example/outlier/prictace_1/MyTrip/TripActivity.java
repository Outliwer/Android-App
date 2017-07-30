package com.example.outlier.prictace_1.MyTrip;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.outlier.prictace_1.R;

import java.util.ArrayList;
import java.util.List;

public class TripActivity extends AppCompatActivity {

    private List<TripDetail> commentList=new ArrayList<>();
    //初始化标题栏
    private ImageView titleLeftImv;
    private TextView titleTv;
    private ImageView homepage_title_left;

    //ListView
    private ListView listView;

    TripDetailAdapter adapter;

    //数据
    private String position;
    private String date;
    private int days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        initView();
        initeComment();

        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        position = intent.getStringExtra("position");
        days = intent.getIntExtra("days",1);

    }

    private void initView() {
        // 初始化页面标题栏
        titleLeftImv = (ImageView) findViewById(R.id.homepage_title_imv);
        titleLeftImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleTv = (TextView) findViewById(R.id.homepage_title_text_tv);
        titleTv.setText(position);
        //ListView
        listView=(ListView) findViewById(R.id.list_days);

        adapter=new TripDetailAdapter(TripActivity.this,R.layout.trip_detail,commentList);
        listView.setAdapter(adapter);

    }

    private void initeComment(){
        for(int num=0;num<days;num++){
            int day = Integer.valueOf(date.substring(1,4)).intValue() * 365 +
                    Integer.valueOf(date.substring(6,7)).intValue()*30 + Integer.valueOf(date.substring(9,10)).intValue() + num;
            int year = day / 365;
            int month = (day - year*365)/30;
            int d = day - month*30 - year*365;
            if (d == 0)
                d = 30;
            List<Detail> detail = new ArrayList<>();
            detail.add(new Detail("韩国烤肉"));
            detail.add(new Detail("美国烧鸡"));
            TripDetail firstOne=new TripDetail("" + year + "-" + month + "-" + d,detail);
            commentList.add(firstOne);
        }
    }
}
