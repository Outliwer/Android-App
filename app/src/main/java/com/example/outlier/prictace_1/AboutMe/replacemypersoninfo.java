package com.example.outlier.prictace_1.AboutMe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.outlier.prictace_1.AboutMe.AllClass.personInfo;
import com.example.outlier.prictace_1.R;
import com.example.outlier.prictace_1.FourthFragment;

public class replacemypersoninfo extends AppCompatActivity {

    //定义所有控件
    private TextView labelTitle;
    private ImageView iconBack;
    private TextView labelUploadPictureBtn;
    private Button btnReplaceOver;
    private ImageView headIcon;
    private EditText inputBoxAccount;
    private EditText inputBoxPassword;
    private EditText inputBoxName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replacemypersoninfo);
        initeView();
    }

    private void initeView(){
        //初始化标题和按钮
        labelTitle=(TextView)findViewById(R.id.label_title);
        labelTitle.setText("修改信息");

        inputBoxAccount=(EditText)findViewById(R.id.inputBox_account);
        inputBoxPassword=(EditText)findViewById(R.id.inputBox_password);
        inputBoxName=(EditText)findViewById(R.id.inputBox_name);

        //定义所有点击事件
        iconBack=(ImageView)findViewById(R.id.icon_back);
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        labelUploadPictureBtn=(TextView)findViewById(R.id.label_uploadpicture_button);
        labelUploadPictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里会定义一个连接到图片库的API，用来导入头像图片
            }
        });

        btnReplaceOver=(Button)findViewById(R.id.btn_replaceover);
        btnReplaceOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newAccount= inputBoxAccount.getText().toString();
                String newPassword=inputBoxPassword.getText().toString();
                String newName=inputBoxName.getText().toString();


                Intent intent=new Intent(replacemypersoninfo.this,FourthFragment.class);
                Bundle bundle=new Bundle();

                bundle.putString("account",newAccount);
                bundle.putString("password",newPassword);
                bundle.putString("name",newName);
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();//这里是修改完毕回到个人信息界面，和按返回键回到是不一样的
            }
        });



    }

}
