package com.example.outlier.prictace_1.AboutMe;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.outlier.prictace_1.AboutMe.AllClass.commentAdapter;
import com.example.outlier.prictace_1.AboutMe.AllClass.commentItem;
import com.example.outlier.prictace_1.R;

import java.util.ArrayList;
import java.util.List;

public class mycomment extends Activity {



    private List<commentItem> commentList=new ArrayList<>();
    private ListView listView;
    private commentAdapter adapter;

    private TextView labelTitle;
    private ImageView iconBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycomment);
        //初始化评论数据
        initeComment();
        adapter=new commentAdapter(mycomment.this, R.layout.comment_item,commentList);
        listView=(ListView)findViewById(R.id.list_comment);
        listView.setAdapter(adapter);


        initeView();
    }


    private void initeComment(){
        commentItem firstOne=new commentItem("2017/3/4","阿拉斯加","随便写的一段话.......");
        commentList.add(firstOne);
        commentItem secondOne=new commentItem("2017/3/4","纽约","随便写的一段话.......");
        commentList.add(secondOne);
        commentItem thirdOne=new commentItem("2017/3/4","巴黎","随便写的一段话.......");
        commentList.add(thirdOne);
        commentItem forthOne=new commentItem("2017/3/4","孟加拉","随便写的一段话.......");
        commentList.add(forthOne);
        commentItem fifthOne=new commentItem("2017/3/4","新德里","随便写的一段话.......");
        commentList.add(fifthOne);
    }



    private void initeView(){

        //初始化标题栏
        labelTitle=(TextView)findViewById(R.id.label_title);
        labelTitle.setText("我的点评");

        //定义所有点击事件
        iconBack=(ImageView)findViewById(R.id.icon_back);
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //对于listView中的长按事件删除功能
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {
                //定义AlertDialog.Builder对象，当长按列表项的时候弹出确认删除对话框
                AlertDialog.Builder builder=new AlertDialog.Builder(mycomment.this);
                builder.setMessage("确定删除?");
                builder.setTitle("提示");
                //添加AlertDialog.Builder对象的setPositiveButton()方法
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(commentList.remove(position)!=null){
                            System.out.println("success");
                        }else {
                            System.out.println("failed");
                        }
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getBaseContext(), "删除列表项", Toast.LENGTH_SHORT).show();
                    }
                });

                //添加AlertDialog.Builder对象的setNegativeButton()方法
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create().show();
                return false;
            }
        });
    }

}
