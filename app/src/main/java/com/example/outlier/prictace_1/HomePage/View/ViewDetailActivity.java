package com.example.outlier.prictace_1.HomePage.View;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.outlier.prictace_1.HomePage.Guide.WayGuideActivity;
import com.example.outlier.prictace_1.HomePage.Search.SearchDetailActivity;
import com.example.outlier.prictace_1.R;

public class ViewDetailActivity extends AppCompatActivity {
    //初始化标题栏
    private ImageView titleLeftImv;
    private TextView titleTv;
    //初始化浏览器
    private WebView web_view;

    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }

        Intent intent = getIntent();
        String address = "";
        address = intent.getStringExtra("viewaddresses");
        // 初始化页面标题栏
        titleTv = (TextView) findViewById(R.id.homepage_title_text_tv);
        titleTv.setText("景点结果");
        titleLeftImv = (ImageView) findViewById(R.id.homepage_title_imv);
        titleLeftImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //
        web_view = (WebView) findViewById(R.id.web_view);
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.setWebViewClient(new WebViewClient());
        Log.e("viewurlsindetail",intent.getStringExtra("viewurls"));
        web_view.loadUrl(intent.getStringExtra("viewurls"));
        web_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        button4 = (Button) findViewById(R.id.button4);
        final String finalAddress = address;
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewDetailActivity.this,WayGuideActivity.class);
                intent.putExtra("my_address", finalAddress);
                startActivity(intent);
            }
        });

    }
}
