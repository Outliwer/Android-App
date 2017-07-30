package com.example.outlier.prictace_1.AboutMe.myFriend;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.outlier.prictace_1.AboutMe.myFriend.Toolkit.DataHelper;
import com.example.outlier.prictace_1.AboutMe.myFriend.Toolkit.IntentHelper;
import com.example.outlier.prictace_1.AboutMe.myFriend.Toolkit.JSONHelper;
import com.example.outlier.prictace_1.AboutMe.myFriend.Toolkit.ListHelper;
import com.example.outlier.prictace_1.AboutMe.myFriend.Value.Values;
import com.example.outlier.prictace_1.BaseApplication;
import com.example.outlier.prictace_1.R;

import org.json.JSONObject;

import io.rong.imkit.RongIM;

public class AddFriendActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView labelTitle;
    private ImageView iconBack;
    private ImageButton createGroup;
    private Button addContact,addGroup;
    private EditText et_username;
    private EditText et_groupId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        setContentView(R.layout.activity_add_friend);
        //初始化标题栏
        labelTitle=(TextView)findViewById(R.id.label_title);
        labelTitle.setText("添加好友");
        createGroup = (ImageButton) findViewById(R.id.imgbtn_createGroup);
        addContact = (Button) findViewById(R.id.btn_add_contact);
        addGroup = (Button) findViewById(R.id.btn_addGroup);
        et_username = (EditText) findViewById(R.id.et_username);
        et_groupId = (EditText) findViewById(R.id.et_groupId);
        createGroup.setOnClickListener(this);
        addContact.setOnClickListener(this);
        addGroup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        switch (v.getId()){
            case R.id.btn_add_contact:
                //隐藏软键盘
                String contactname = et_username.getText().toString();
                ListHelper.addData("userId", BaseApplication.get("userid",""));
                ListHelper.addData("contact_id", contactname);
                String res = IntentHelper.getData(Values.addContact, ListHelper.getList());
                ListHelper.clearData();

                /*
                    获取数据失败
                 */
                if(res == null){
                    Toast.makeText(AddFriendActivity.this, "获取网路数据失败,请检查网络设置", Toast.LENGTH_SHORT).show();
                    return ;
                }

                if(res.equals("true")){
                    Toast.makeText(AddFriendActivity.this,"添加成功!",Toast.LENGTH_SHORT).show();
                    //RongIM.getInstance().startPrivateChat(AddFriendActivity.this,contactname,"单聊");
                }else{
                    Toast.makeText(AddFriendActivity.this,"失败:用户名错误或已添加!",Toast.LENGTH_SHORT).show();
                }
                break;


            case R.id.btn_addGroup:

                //隐藏软键盘
                String groupId = et_groupId.getText().toString();
                ListHelper.addData("userId",BaseApplication.get("userid",""));
                ListHelper.addData("groupId", groupId);
                res = IntentHelper.getData(Values.addGroupURL, ListHelper.getList());
                ListHelper.clearData();
                /*
                    获取数据失败
                 */
                if(res == null) {
                    Toast.makeText(AddFriendActivity.this, "获取网路数据失败,请检查网络设置", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(res.equals("true")){
                    Toast.makeText(AddFriendActivity.this,"添加成功!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddFriendActivity.this,"失败:群Id错误或已添加!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imgbtn_createGroup:

                break;
        }
    }
}

