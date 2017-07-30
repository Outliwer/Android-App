package com.example.outlier.prictace_1.HomePage.Tiket.datetimepicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.outlier.prictace_1.R;

/**
 * 时间拾取器界面
 *
 * @author wwj_748
 *
 */
public class DateTimePickerActivity extends AppCompatActivity {
    //初始化标题栏
    private ImageView titleLeftImv;
    private TextView titleTv;
    /** Called when the activity is first created. */
    private EditText startDateTime;

    private String initStartDateTime = "2017年7月26日 14:44"; // 初始化开始时间

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_picker);


        // 初始化页面标题栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        titleLeftImv = (ImageView) findViewById(R.id.homepage_title_imv);
        titleLeftImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String date = startDateTime.getText().toString().trim();
                Intent intent = new Intent();
                intent.putExtra("date",date.substring(0,10));
                setResult(3,intent);
                finish();
            }
        });
        titleTv = (TextView) findViewById(R.id.homepage_title_text_tv);
        titleTv.setText("日期搜索");

        // 两个输入框
        startDateTime = (EditText) findViewById(R.id.inputDate);

        startDateTime.setText(initStartDateTime);

        startDateTime.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        DateTimePickerActivity.this, initStartDateTime);
                dateTimePicKDialog.dateTimePicKDialog(startDateTime);

            }
        });

    }
}