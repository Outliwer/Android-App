package com.example.outlier.prictace_1.Comment.Search;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.outlier.prictace_1.AboutMe.AllClass.commentAdapter;
import com.example.outlier.prictace_1.AboutMe.AllClass.commentItem;
import com.example.outlier.prictace_1.AboutMe.mycomment;
import com.example.outlier.prictace_1.R;

import java.util.ArrayList;
import java.util.List;

public class SearchAnswerActivity extends AppCompatActivity {

    //点评
    private List<commentItem> commentList=new ArrayList<>();
    private ListView listView;
    //初始化标题栏
    private ImageView titleLeftImv;
    private TextView titleTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_answer);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        //初始化评论数据
        initeComment();
        commentAdapter adapter=new commentAdapter(SearchAnswerActivity.this,R.layout.comment_item,commentList);
        listView=(ListView)findViewById(R.id.list_comment);
        listView.setAdapter(adapter);
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
        titleTv.setText("搜索结果");
    }

    private void initeComment(){
        for(int num=0;num<5;num++){
            commentItem firstOne=new commentItem("2017/3/"+ (num+1),"阿拉斯加","随便写的一段话.......");
            commentList.add(firstOne);
        }
    }

}
