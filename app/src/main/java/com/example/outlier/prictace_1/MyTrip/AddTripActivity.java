package com.example.outlier.prictace_1.MyTrip;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnChangeLisener;
import com.codbking.widget.bean.DateType;
import com.example.outlier.prictace_1.HomePage.Hotel.HotelActivity;
import com.example.outlier.prictace_1.HomePage.Tiket.datetimepicker.DateTimePickerActivity;
import com.example.outlier.prictace_1.HomePage.View.ViewAnswerActivity.ViewAnswerActivity;
import com.example.outlier.prictace_1.R;

import java.util.Date;

public class AddTripActivity extends AppCompatActivity {

    //初始化标题栏
    private ImageView titleLeftImv;
    private TextView titleTv;
    //数据的获取
    private EditText edit_text_tripname;
    private EditText edit_text_start;
    private EditText edit_text_final;
    private Button checkInBtn;
    private Button checkOutBtn;
    //按钮
    private Button btn_choosecity_start;
    private Button btn_choosecity_final;
    //点击跳转按钮
    private Button button;


    //日期记录
    private String checkInDate;
    private String checkOutDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        initView();
    }

    private void initView(){
        //编辑框
        edit_text_tripname=(EditText)findViewById(R.id.edit_text_tripname);
        checkInBtn = (Button) findViewById(R.id.value_start);
        checkOutBtn = (Button) findViewById(R.id.value_final);

        checkInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InDatePickDialog();
            }
        });
        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OutDatePickDialog();
            }
        });

        edit_text_final = (EditText) findViewById(R.id.edit_text_final);
        edit_text_start = (EditText) findViewById(R.id.edit_text_start);
        //按钮
        btn_choosecity_start = (Button) findViewById(R.id.btn_choosecity_start);
        btn_choosecity_final = (Button) findViewById(R.id.btn_choosecity_final);
        button = (Button) findViewById(R.id.button);

        btn_choosecity_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(AddTripActivity.this, ViewAnswerActivity.class),3);
            }
        });

        btn_choosecity_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(AddTripActivity.this, ViewAnswerActivity.class),4);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                final String tripname = edit_text_tripname.getText().toString().trim();
                final String startday = checkInBtn.getText().toString().trim();
                final String finalday = checkOutBtn.getText().toString().trim();
                final String startcity = edit_text_start.getText().toString().trim();
                final String finalcity = edit_text_final.getText().toString().trim();
                intent.putExtra("tripname",tripname);
                intent.putExtra("startday",startday);
                intent.putExtra("finalday", finalday);
                intent.putExtra("startcity", startcity);
                intent.putExtra("finalcity", finalcity);
                setResult(2, intent);
                finish();
            }
        });

        // 初始化页面标题栏
        titleLeftImv = (ImageView) findViewById(R.id.homepage_title_imv);
        titleLeftImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleTv = (TextView) findViewById(R.id.homepage_title_text_tv);
        titleTv.setText("行程创建");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if (requestCode == 3){
            String date = data.getStringExtra("city");
            edit_text_start.setText(date);
        }else if (requestCode == 4){
            String date = data.getStringExtra("city");
            edit_text_final.setText(date);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void InDatePickDialog(){
        DatePickDialog dialog = new DatePickDialog(AddTripActivity.this);
        dialog.setYearLimt(5);
        dialog.setTitle("入住时间");
        dialog.setType(DateType.TYPE_YMD);
        dialog.setMessageFormat("yyyy-MM-dd");
        dialog.setOnChangeLisener(new OnChangeLisener() {
            @Override
            public void onChanged(Date date) {
                String checkYear = ""+date.getYear();
                Log.e("checkInDate----",""+checkYear);
                checkYear = "20"+checkYear.substring(1);
                String checkMonth = String.valueOf(date.getMonth()+1);
                if(checkMonth.length()<2){
                    checkMonth = "0"+checkMonth;
                }
                checkInDate = checkYear+checkMonth+String.valueOf(date.getDate());
                Log.e("Indate",""+checkInDate);
                checkInBtn.setText(checkYear+"--"+String.valueOf(date.getMonth()+1)+"--"+String.valueOf(date.getDate()));
            }
        });
        dialog.show();
    }
    private void OutDatePickDialog(){
        DatePickDialog dialog = new DatePickDialog(this);
        dialog.setYearLimt(10);
        dialog.setTitle("退房时间");
        dialog.setType(DateType.TYPE_YMD);
        dialog.setMessageFormat("yyyy-mm-dd");
        dialog.setOnChangeLisener(new OnChangeLisener() {
            @Override
            public void onChanged(Date date) {
                String checkYear = ""+date.getYear();
                Log.e("checkInDate----",""+checkYear);
                String checkMonth = String.valueOf(date.getMonth()+1);
                if(checkMonth.length()<2){
                    checkMonth = "0"+checkMonth;
                }
                checkYear = "20"+checkYear.substring(1);
                checkOutDate = checkYear+checkMonth+String.valueOf(date.getDate());
                Log.e("OutDate----",""+checkOutDate);
                checkOutBtn.setText(checkYear+"--"+String.valueOf(date.getMonth()+1)+"--"+String.valueOf(date.getDate()));
            }
        });
        dialog.show();
    }
}
