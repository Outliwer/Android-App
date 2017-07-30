package com.example.outlier.prictace_1.HomePage.Tiket;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.outlier.prictace_1.AboutMe.mycomment;
import com.example.outlier.prictace_1.HomePage.Tiket.datetimepicker.TiketAdapter;
import com.example.outlier.prictace_1.R;

import java.util.ArrayList;
import java.util.List;

public class TiketSearchActivity extends AppCompatActivity {
    //初始化标题栏
    private ImageView titleLeftImv;
    private TextView titleTv;

    private List<tiketitem> commentList=new ArrayList<>();
    private ListView listView;


    int m = 0;
    String[] trainNo;
    String[] use_time;
    String[] startTime;
    String[] startStationName;
    String[] endStationName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiket_search);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }

        // 初始化页面标题栏
        titleTv = (TextView) findViewById(R.id.homepage_title_text_tv);
        titleTv.setText("车票结果");
        titleLeftImv = (ImageView) findViewById(R.id.homepage_title_imv);
        titleLeftImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        m = intent.getIntExtra("data.size()",0);
        trainNo = intent.getStringArrayExtra("trainNo");
        use_time = intent.getStringArrayExtra("use_time");
        startTime = intent.getStringArrayExtra("startTime");
        startStationName = intent.getStringArrayExtra("startStationName");
        endStationName = intent.getStringArrayExtra("endStationName");
        initeComment();
        TiketAdapter adapter=new TiketAdapter(TiketSearchActivity.this,R.layout.tiket_item,commentList);
        listView=(ListView)findViewById(R.id.list_comment);
        listView.setAdapter(adapter);

    }

    private void initView(){
        listView.findViewById(R.id.list_comment);
    }

    private void initeComment(){
        for(int num=0;num<m;num++){
            Log.e("m",""+m);
            tiketitem firstOne=new tiketitem(trainNo[num],use_time[num],startTime[num],startStationName[num],endStationName[num]);
            commentList.add(firstOne);
        }


    }



}
