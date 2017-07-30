package com.example.outlier.prictace_1.HomePage.View;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.outlier.prictace_1.HomePage.Tiket.TiketSearchActivity;
import com.example.outlier.prictace_1.HomePage.Tiket.datetimepicker.TiketAdapter;
import com.example.outlier.prictace_1.HomePage.View.item.viewAdapter;
import com.example.outlier.prictace_1.HomePage.View.item.viewitem;
import com.example.outlier.prictace_1.R;

import java.util.ArrayList;
import java.util.List;

public class ViewSearchActivity extends AppCompatActivity {

    //初始化标题栏
    private ImageView titleLeftImv;
    private TextView titleTv;

    private List<viewitem> commentList=new ArrayList<>();
    private ListView listView;
    private viewAdapter adapter;

    private int number;
    private String []prices;
    private String []viewurls;
    private String []viewnames;
    private String []viewaddresses;
    private String []viewdetail_infos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_search);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }

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

        Intent intent = getIntent();      //获取一个意图
        number = intent.getIntExtra("size",0);
        prices = intent.getStringArrayExtra("price");
        viewurls = intent.getStringArrayExtra("viewurl");
        viewnames = intent.getStringArrayExtra("viewname");
        viewaddresses = intent.getStringArrayExtra("viewaddress");
        initeComment();
        listView=(ListView)findViewById(R.id.list_comment);
        viewAdapter adapter=new viewAdapter(ViewSearchActivity.this,R.layout.view_item,commentList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(ViewSearchActivity.this, ViewDetailActivity.class);
                intent.putExtra("viewurls",viewurls[position]);
                intent.putExtra("viewaddresses",viewaddresses[position]);
                Log.e("viewaddresses",""+viewaddresses[position]);
                Log.e("viewurls",""+viewurls[position]);
                startActivity(intent);
                //Toast.makeText(getApplication(), ((CitySortModel) adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initView(){
        listView.findViewById(R.id.list_comment);
    }

    private void initeComment(){
        for(int num=0;num<number;num++){
            Log.e("m",""+number);
            viewitem firstOne=new viewitem(prices[num],viewurls[num],viewnames[num],viewaddresses[num]);
            commentList.add(firstOne);
        }


    }

}
