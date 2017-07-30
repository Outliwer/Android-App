package com.example.outlier.prictace_1.HomePage.Hotel;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnChangeLisener;
import com.codbking.widget.bean.DateType;
import com.common.CatLoadingView;
import com.example.outlier.prictace_1.BaseApplication;
import com.example.outlier.prictace_1.HomePage.Hotel.Entity.List_1;
import com.example.outlier.prictace_1.HomePage.Tiket.datetimepicker.DateTimePickerActivity;
import com.example.outlier.prictace_1.HomePage.Hotel.Entity.Root;
import com.example.outlier.prictace_1.HomePage.Hotel.Entity.Showapi_res_body;
import com.example.outlier.prictace_1.HomePage.View.ViewAnswerActivity.ViewAnswerActivity;
import com.example.outlier.prictace_1.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HotelActivity extends AppCompatActivity {
    private static final int UPDATE_TEXT = 1;
    Root entity;
    private String checkInDate;
    private String checkOutDate;
    Class<Root> entityClass = Root.class;
    private Button checkInBtn;
    private Button checkOutBtn;

    protected Handler mHandler =  new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:

                    Gson gs = new Gson();
                    entity= gs.fromJson(responseData_1,entityClass);
                    Showapi_res_body showapi_res_body = entity.getShowapi_res_body();
                    List<List_1> list = showapi_res_body.getList();
                    if (list != null) {

                    /*http://route.showapi.com/405-5?showapi_appid=42305&cityId=192&comeDate=20170717&leaveDate=20170718&ionId=&keyword=&longitude=&latitude=&hbs=&starRatedId=&sortType=1&page=&pageSize=&showapi_sign=c069130238404775894ba39caf3ac094*/
                        String[] starRatedNames = new String[list.size()];
                        String[] hotelNames = new String[list.size()];
                        String[] intros = new String[list.size()];
                        String[] roomTypes = new String[list.size()];
                        String[] longitudes = new String[list.size()];
                        String[] latitudes = new String[list.size()];
                        String[] highestPrices = new String[list.size()];
                        String[] lowestPrices = new String[list.size()];
                        String[] imgs = new String[list.size()];
                        String[] onewords = new String[list.size()];
                        String[] nearBys = new String[list.size()];

                        for (int i = 0; i < list.size(); i++) {
                            String starRatedName = list.get(i).getStarRatedName();
                            starRatedNames[i] = starRatedName;
                            String hotelName = list.get(i).getHotelName();
                            hotelNames[i] = hotelName;
                            String intro = list.get(i).getIntro();
                            intros[i] = intro;
                            String roomType = list.get(i).getRoomType();
                            roomTypes[i] = roomType;
                            String longitude = list.get(i).getLongitude();
                            longitudes[i] = longitude;
                            String latitude = list.get(i).getLatitude();
                            latitudes[i] = latitude;
                            String highestPrice = list.get(i).getHighestPrice();
                            highestPrices[i] = highestPrice;
                            String lowestPrice = list.get(i).getLowestPrice();
                            lowestPrices[i] = lowestPrice;
                            String img = list.get(i).getImg();
                            imgs[i] = img;
                            String oneword = list.get(i).getOneWord();
                            onewords[i] = oneword;
                            String nearBy = list.get(i).getNearBy();
                            nearBys[i] = nearBy;

                        }
                        Intent intent = new Intent(HotelActivity.this, HotelSearchActivity.class);
                        intent.putExtra("starRatedName", starRatedNames);
                        intent.putExtra("hotelName", hotelNames);
                        intent.putExtra("intro", intros);
                        intent.putExtra("roomType", roomTypes);
                        intent.putExtra("longitude", longitudes);
                        intent.putExtra("latitude", latitudes);
                        intent.putExtra("highestPrice", highestPrices);
                        intent.putExtra("lowestPrice", lowestPrices);
                        intent.putExtra("img", imgs);
                        intent.putExtra("oneword", onewords);
                        intent.putExtra("nearBy", nearBys);
                        Log.e("-----------", "" + imgs[2]);
                        mView.dismiss();
                        startActivity(intent);
                    }else {
                        Toast.makeText(HotelActivity.this,"查无结果",Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    };

    //编辑框
    private EditText value;
    String responseData_1;
    private Spinner spinner;
    private Spinner spinner1;
    //初始化标题栏
    private ImageView titleLeftImv;
    private TextView titleTv;
    //按钮实现功能
    private Button btn_StartSearchView;
    private Button btn_chooseView;

    CatLoadingView mView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
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
        titleTv.setText("酒店搜索");

        checkInBtn = (Button)findViewById(R.id.CheckInDate);
        checkOutBtn = (Button)findViewById(R.id.CheckOutDate);

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

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner1 = (Spinner) findViewById(R.id.spinner1);

        value = (EditText) findViewById(R.id.value);

        btn_chooseView = (Button) findViewById(R.id.btn_chooseView);
        btn_StartSearchView = (Button) findViewById(R.id.btn_StartSearchView);
        //点击到开始搜索界面
        btn_StartSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView = new CatLoadingView();
                mView.show(getSupportFragmentManager(), "");
                //爬虫酒店信息
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String comeDate = checkInDate;
                            String Actualcome = checkInDate;
                            String leaveDate = checkOutDate;
                            String Actualleave = checkOutDate;
                            //爬取城市列表
                            String cityId = value.getText().toString().trim();
                            final String address_1  =  BaseApplication.get("hostaddress","http://169.254.24.205:8080/trip/") + "findcity?city="+cityId;
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url(address_1)
                                    .build();
                            Response response = client.newCall(request).execute();
                            String responseData = response.body().string();
                            Log.e("responseData",""+responseData);

                            //对给入的信息进行一定的删选
                            String starRatedId = spinner1.getSelectedItem().toString().trim();
                            String sortType = spinner.getSelectedItem().toString().trim();
                            String star = "";
                            switch (starRatedId){
                                case "价格由低到高":
                                    star = "1";
                                    break;
                                case "价格由高到低":
                                    star = "2";
                                    break;
                                case "入住量由多到少":
                                    star = "3";
                                    break;
                                case "推荐度":
                                    star = "4";
                                    break;
                            }
                            String sort = "";
                            switch (sortType){
                                case "无要求":
                                    sort = "";
                                    break;
                                case "一星级":
                                    sort = "1";
                                    break;
                                case "二星级":
                                    sort = "2";
                                    break;
                                case "三星级":
                                    sort = "3";
                                    break;
                                case "四星级":
                                    sort = "4";
                                    break;
                                case "五星级":
                                    sort = "5";
                                    break;
                                case "六星级":
                                    sort = "6";
                                    break;
                                case "七星级":
                                    sort = "7";
                                    break;
                            }
                            String address = "http://route.showapi.com/405-5?showapi_appid=42305&cityId=" + responseData +
                                    "&comeDate="+Actualcome+"&leaveDate="+Actualleave+"&ionId=&keyword=&longitude=&latitude=&hbs=&starRatedId="+
                                    sort+"&sortType="+star+"&page=&pageSize=&showapi_sign=c069130238404775894ba39caf3ac094";
                            Log.e("address",""+address);
                            client = new OkHttpClient();
                            request = new Request.Builder()
                                    .url(address)
                                    .build();
                            response = client.newCall(request).execute();
                            responseData_1 = response.body().string();
                            Log.e("responseData_1----",""+responseData_1);

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
        btn_chooseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(HotelActivity.this, ViewAnswerActivity.class),3);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 3)
        {
            String phone = data.getStringExtra("city");
            value.setText(phone);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void InDatePickDialog(){
        DatePickDialog dialog = new DatePickDialog(HotelActivity.this);
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
