package com.example.outlier.prictace_1.AboutMe;

import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.outlier.prictace_1.AboutMe.AllClass.commentAdapter;
import com.example.outlier.prictace_1.AboutMe.AllClass.commentItem;
import com.example.outlier.prictace_1.AboutMe.AllClass.strategyAdapter;
import com.example.outlier.prictace_1.AboutMe.AllClass.strategyItem;
import com.example.outlier.prictace_1.R;

import java.util.ArrayList;
import java.util.List;

public class mystrategy extends AppCompatActivity {
    private List<strategyItem> strategyList=new ArrayList<>();
    private ListView listView;
    private strategyAdapter adapter;
    private TextView labelTitle;
    private ImageView iconBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystrategy);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }

        initeStrategy();
        adapter=new strategyAdapter(mystrategy.this,R.layout.strategy_item,strategyList);
        listView=(ListView)findViewById(R.id.list_strategy);
        listView.setAdapter(adapter);


        initeView();
    }

    private void initeStrategy(){
        strategyItem firstOne=new strategyItem("你去过的最远的地方？","随便写写....",R.drawable.btn_inrecommend);
        strategyList.add(firstOne);
        strategyItem secondOne=new strategyItem("带你玩转海南","随便写写....",R.drawable.button_55);
        strategyList.add(secondOne);
    }


    private void initeView(){
        //初始化标题栏
        labelTitle=(TextView)findViewById(R.id.label_title);
        labelTitle.setText("我的个人攻略");

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
                AlertDialog.Builder builder=new AlertDialog.Builder(mystrategy.this);
                builder.setMessage("确定删除?");
                builder.setTitle("提示");
                //添加AlertDialog.Builder对象的setPositiveButton()方法
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(strategyList.remove(position)!=null){
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
