package com.example.outlier.prictace_1.HomePage.Tiket;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnChangeLisener;
import com.codbking.widget.bean.DateType;
import com.example.outlier.prictace_1.HomePage.Search.SearchActivity;
import com.example.outlier.prictace_1.HomePage.Search.SearchDetailActivity;
import com.example.outlier.prictace_1.HomePage.Search.SearchEntity;
import com.example.outlier.prictace_1.HomePage.Tiket.datetimepicker.BusinessSeat;
import com.example.outlier.prictace_1.HomePage.Tiket.datetimepicker.DateTimePickerActivity;
import com.example.outlier.prictace_1.HomePage.Tiket.datetimepicker.FirstSeat;
import com.example.outlier.prictace_1.HomePage.Tiket.datetimepicker.SecondSeat;
import com.example.outlier.prictace_1.HomePage.Tiket.datetimepicker.TiketBoDyDataEntity;
import com.example.outlier.prictace_1.HomePage.Tiket.datetimepicker.TiketBodyEntity;
import com.example.outlier.prictace_1.HomePage.Tiket.datetimepicker.TiketBodySeatsEntity;
import com.example.outlier.prictace_1.HomePage.Tiket.datetimepicker.TiketEntity;
import com.example.outlier.prictace_1.HomePage.View.ViewActivity;
import com.example.outlier.prictace_1.HomePage.View.ViewAnswerActivity.ViewAnswerActivity;
import com.example.outlier.prictace_1.HomePage.View.ViewSearchActivity;
import com.example.outlier.prictace_1.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TiketActivity extends AppCompatActivity {
    //Json实体类
    String responseData;
    String need = null;
    TiketEntity entity;
    Class<TiketEntity> entityClass = TiketEntity.class;
    private Button selectTime;
    //消息处理
    private static final int UPDATE_TEXT = 1;
    private String startDate;
    //初始化标题栏
    private ImageView titleLeftImv;
    private TextView titleTv;
    //按钮实现功能
    private Button btn_StartSearchView;
    private Button btn_chooseView;
    private Button btn_choosecity_final;
    private Button btn_choosecity_start;
    //编辑框
    private EditText edit_text_start;
    private EditText edit_text_final;
    //hander
    protected Handler mHandler =  new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:

                    Gson gs = new Gson();
                    entity= gs.fromJson(responseData,entityClass);
                    TiketBodyEntity showapi_res_body = entity.getShowapi_res_body();
                    Log.e("showapi_res_body",""+showapi_res_body);
                    String arrCity = showapi_res_body.getArrCity();
                    String dptCity = showapi_res_body.getDptCity();
                    String dptDate = showapi_res_body.getDptDate();
                    List<TiketBoDyDataEntity> data = showapi_res_body.getData();
                    if (data.size() != 0) {
                        Log.e("data.size()", "" + data.size());
                        String[] use_time = new String[data.size()];
                        String[] trainNo = new String[data.size()];
                        String[] endStationName = new String[data.size()];
                        String[] startStationName = new String[data.size()];
                        String[] startTime = new String[data.size()];
                        String[] arrTime = new String[data.size()];
                        TiketBodySeatsEntity[] seats = new TiketBodySeatsEntity[data.size()];
                        FirstSeat[] firstSeats = new FirstSeat[data.size()];
                        SecondSeat[] secondSeats = new SecondSeat[data.size()];
                        BusinessSeat[] businessSeats = new BusinessSeat[data.size()];
                        double[] firstSeats_price = new double[data.size()];
                        int[] firstSeats_count = new int[data.size()];
                        double[] secondSeats_price = new double[data.size()];
                        int[] secondSeats_count = new int[data.size()];
                        double[] businessSeats_price = new double[data.size()];
                        int[] businessSeats_count = new int[data.size()];

                        for (int i = 0; i < data.size(); i++) {
                            use_time[i] = data.get(i).getInterval();
                            Log.e("use_time", "" + use_time[i]);
                            trainNo[i] = data.get(i).getTrainNo();
                            endStationName[i] = data.get(i).getEndStationName();
                            startStationName[i] = data.get(i).getStartStationName();
                            startTime[i] = data.get(i).getDptTime();
                            arrTime[i] = data.get(i).getArrTime();

                            seats[i] = data.get(i).getSeats();
                            Log.e("seats", "" + seats[i]);
                            firstSeats[i] = seats[i].get一等座();
                            secondSeats[i] = seats[i].get二等座();
                            businessSeats[i] = seats[i].get商务座();
                            Log.e("firstSeats", "" + firstSeats[i]);
                                                                       /*Log.e("businessSeats_count",""+businessSeats_count[i]);
                                                                       businessSeats_count[i] = businessSeats[i].getCount();
                                                                       businessSeats_price[i] = businessSeats[i].getPrice();
                                                                       firstSeats_price[i] = firstSeats[i].getPrice();
                                                                       firstSeats_count[i] = firstSeats[i].getCount();
                                                                       secondSeats_price[i] = secondSeats[i].getPrice();
                                                                       secondSeats_count[i] = secondSeats[i].getCount();
                                                                       Log.e("businessSeats_price",""+businessSeats_price[i]);*/
                        }


                        Intent intent = new Intent(TiketActivity.this, TiketSearchActivity.class);
                        intent.putExtra("trainNo", trainNo);
                        intent.putExtra("use_time", use_time);
                        intent.putExtra("startTime", startTime);
                        intent.putExtra("data.size()", data.size());
                        intent.putExtra("startStationName", startStationName);
                        intent.putExtra("endStationName", endStationName);
                        startActivity(intent);
                    }else {
                        Toast.makeText(TiketActivity.this,"查无结果",Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiket);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        selectTime = (Button)findViewById(R.id.selectTime);
        selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartDatePickDialog();
            }
        });
        initView();
    }

    private void initView(){
        // 初始化页面标题栏
        titleLeftImv = (ImageView) findViewById(R.id.homepage_title_imv);
        titleLeftImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleTv = (TextView) findViewById(R.id.homepage_title_text_tv);
        titleTv.setText("车票搜索");


        //编辑框
        edit_text_start = (EditText) findViewById(R.id.edit_text_start);
        edit_text_final = (EditText) findViewById(R.id.edit_text_final);

        btn_chooseView = (Button) findViewById(R.id.btn_chooseView);
        btn_StartSearchView = (Button) findViewById(R.id.btn_StartSearchView);
        btn_choosecity_final = (Button) findViewById(R.id.btn_choosecity_final);
        btn_choosecity_start = (Button) findViewById(R.id.btn_choosecity_start);
        //点击到开始搜索界面
        btn_StartSearchView.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(final View v) {

                                                       //爬虫车票信息
                                                       String startcity = edit_text_start.getText().toString().trim();
                                                       String finalcity = edit_text_final.getText().toString().trim();
                                                       final String date = startDate;
                                                       final String address = "http://route.showapi.com/832-3?showapi_appid=42305&from="+ startcity +
                                                               "&to=" + finalcity + "&date=" + date + "&showapi_sign=c069130238404775894ba39caf3ac094";
                                                       new Thread(new Runnable() {
                                                           @Override
                                                           public void run() {
                                                               try {
                                                                   OkHttpClient client = new OkHttpClient();
                                                                   Request request = new Request.Builder()
                                                                           .url(address)
                                                                           .build();
                                                                   Response response = client.newCall(request).execute();
                                                                   responseData = response.body().string();
                                                                   Log.e("responseData----",""+responseData);
                                                                                                                                      Message message = new Message();
                                                                   message.what = UPDATE_TEXT;
                                                                   mHandler.sendEmptyMessage(message.what);



                                                               } catch (IOException e) {
                                                                   e.printStackTrace();
                                                               }
                                                           }
                                                       }).start();
                                                   }
                                               });

        //点击到选择城市界面
        btn_choosecity_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(TiketActivity.this, ViewAnswerActivity.class),2);
            }
        });
        btn_choosecity_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(TiketActivity.this, ViewAnswerActivity.class),1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1)
        {
            String phone = data.getStringExtra("city");
            edit_text_start.setText(phone);

        }else if(requestCode == 2){
            String content = data.getStringExtra("city");
            edit_text_final.setText(content);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void StartDatePickDialog(){
        DatePickDialog dialog = new DatePickDialog(TiketActivity.this);
        dialog.setYearLimt(5);
        dialog.setTitle("出发日期");
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
                startDate = checkYear+checkMonth+String.valueOf(date.getDate());
                Log.e("Indate",""+startDate);
                selectTime.setText(checkYear+"--"+String.valueOf(date.getMonth()+1)+"--"+String.valueOf(date.getDate()));
            }
        });
        dialog.show();
    }

}
