package com.example.outlier.prictace_1.HomePage.Recommend;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.outlier.prictace_1.R;

public class RecommendActivity extends AppCompatActivity {

    //初始化标题栏
    private ImageView titleLeftImv;
    private TextView titleTv;

    //初始化浏览器
    private WebView web_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        initView();
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
        titleTv.setText("推荐");
        web_view = (WebView) findViewById(R.id.web_view);
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.setWebViewClient(new WebViewClient());
        web_view.loadUrl("https://www.tripadvisor.cn/Restaurant_Review-g294212-d2428737-Reviews-TRB_Hutong-Beijing.html");
    }
}
