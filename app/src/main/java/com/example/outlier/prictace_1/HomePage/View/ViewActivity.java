package com.example.outlier.prictace_1.HomePage.View;

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

import com.common.CatLoadingView;
import com.example.outlier.prictace_1.HomePage.Food.SearchFood;
import com.example.outlier.prictace_1.HomePage.View.Entity.Detail_info;
import com.example.outlier.prictace_1.HomePage.View.Entity.Results;
import com.example.outlier.prictace_1.HomePage.View.Entity.Root;
import com.example.outlier.prictace_1.HomePage.View.Entity.Showapi_res_body;
import com.example.outlier.prictace_1.HomePage.View.ViewAnswerActivity.ViewAnswerActivity;
import com.example.outlier.prictace_1.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ViewActivity extends AppCompatActivity {

    //爬虫所需
    Root entity;
    Class<Root> entityClass = Root.class;
    private static final int UPDATE_TEXT = 1;
    String responseData = "";
    CatLoadingView mView;
    protected Handler mHandler =  new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    Gson gs = new Gson();
                    entity= gs.fromJson(responseData,entityClass);
                    Showapi_res_body showapi_res_body = entity.getShowapi_res_body();
                    List<Results> results = showapi_res_body.getResults();
                    String []viewname = new String[results.size()];
                    String []viewaddress = new String[results.size()];
                    Detail_info []viewdetail_info = new Detail_info[results.size()];
                    String []viewprice = new String[results.size()];
                    String []viewurl = new String[results.size()];
                    for (int i =0 ; i < results.size(); i ++){
                        String name = results.get(i).getName();
                        if (null == name)
                            viewname[i] = "";
                        viewname[i] = name;
                        String address = results.get(i).getAddress();
                        if (null == address)
                            viewaddress[i] = "";
                        viewaddress[i] = address;
                        Detail_info detail_info = results.get(i).getDetail_info();
                        viewdetail_info[i] = detail_info;
                        Log.e("detail_info",""+detail_info);
                        String detail_url = detail_info.getDetail_url();
                        if (null == detail_url)
                            viewurl[i] = "";
                        String price = detail_info.getPrice();
                        if (null == price)
                            viewprice[i] = "";
                        viewprice[i] = price;
                        viewurl[i] = detail_url;
                        Log.e("price",""+price);
                    }
                    Intent intent = new Intent(ViewActivity.this, ViewSearchActivity.class);
                    intent.putExtra("price",viewprice);
                    intent.putExtra("size",results.size());
                    intent.putExtra("viewurl",viewurl);
                    intent.putExtra("viewname",viewname);
                    intent.putExtra("viewaddress",viewaddress);
                    Log.e("intent","" + intent);
                    mView.dismiss();
                    startActivity(intent);
                    break;
            }
        }
    };
    //初始化标题栏
    private ImageView titleLeftImv;
    private TextView titleTv;
    //按钮实现功能
    private Button btn_StartSearchView;
    private Button btn_chooseView;
    //编辑框
    private EditText value;
    private EditText price_low;
    private EditText price_high;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
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
        titleTv.setText("景点搜索");
        //编辑框
        value = (EditText) findViewById(R.id.value);



        btn_chooseView = (Button) findViewById(R.id.btn_chooseView);
        btn_StartSearchView = (Button) findViewById(R.id.btn_StartSearchView);

        //点击到开始搜索界面
        btn_StartSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //景点信息爬虫
                mView = new CatLoadingView();
                mView.show(getSupportFragmentManager(), "");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String city = value.getText().toString().trim();
                            String lowprice = price_low.getText().toString().trim();
                            String highprice = price_high.getText().toString().trim();



                            String address = "http://route.showapi.com/52-26?showapi_appid=42305&q=" + "景点" +
                                    "&region=" + city + "&scope=2&page_size=&page_num=&showapi_sign=c069130238404775894ba39caf3ac094";
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url(address)
                                    .build();
                            Response response = client.newCall(request).execute();
                            responseData = response.body().string();
                            Log.e("address",""+address);
                            Log.e("responseData----",""+responseData);
                            Message message = new Message();
                            message.what = UPDATE_TEXT;
                            mHandler.sendEmptyMessage(message.what);

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
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
                startActivityForResult(new Intent(ViewActivity.this, ViewAnswerActivity.class),1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1)
        {
            String phone = data.getStringExtra("city");
            value.setText(phone);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
