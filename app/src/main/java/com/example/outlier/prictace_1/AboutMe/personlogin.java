package com.example.outlier.prictace_1.AboutMe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.outlier.prictace_1.AboutMe.AllClass.personInfo;
import com.example.outlier.prictace_1.AboutMe.myFriend.userEntity;
import com.example.outlier.prictace_1.BaseApplication;
import com.example.outlier.prictace_1.FourthFragment;
import com.example.outlier.prictace_1.HomePage.Search.SearchActivity;
import com.example.outlier.prictace_1.HomePage.Search.SearchBeanEntity;
import com.example.outlier.prictace_1.HomePage.Search.SearchBodyEntity;
import com.example.outlier.prictace_1.HomePage.Search.SearchDetailActivity;
import com.example.outlier.prictace_1.HomePage.Search.SearchListEntity;
import com.example.outlier.prictace_1.HomePage.Search.picList;
import com.example.outlier.prictace_1.MainActivity;
import com.example.outlier.prictace_1.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class personlogin extends AppCompatActivity {
    //一个用来存放所有用户的基础信息的数组
    private static ArrayList<personInfo> personInfoList=new ArrayList<personInfo>();

    userEntity entity;
    Class<userEntity> entityClass = userEntity.class;

    public String []user = new String[4];

    public static final int UPDATE_TEXT = 1;
    protected Handler mHandler =  new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    MainActivity.setStateLogged(true);
                    Intent intent=new Intent(personlogin.this,FourthFragment.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("account",user[0]);
                    bundle.putString("name",user[1]);
                    bundle.putString("headId",user[2]);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK,intent);
                    finish();
                    break;
            }
        }
    };


    private ImageView iconBack;
    private TextView labelTitle;
    private EditText inputBoxAccount;
    private EditText inputBoxPassword;
    private Button btnToPersoninfo;
    private  TextView labelToSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personlogin);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        //初始化所有用户的个人信息
        initeAllPersonInfo();
        initeView();
    }

    private void initeAllPersonInfo() {
        personInfo firstperson= new personInfo("12345678","12345678","xcv",R.drawable.my);
        personInfoList.add(firstperson);
    }

    private void initeView(){
        inputBoxAccount=(EditText)findViewById(R.id.inputBox_account);
        inputBoxPassword=(EditText)findViewById(R.id.inputBox_password);


        //初始化标题栏
        labelTitle=(TextView)findViewById(R.id.label_title);
        labelTitle.setText("登陆");

        //定义所有点击事件
        iconBack=(ImageView)findViewById(R.id.icon_back);
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnToPersoninfo=(Button)findViewById(R.id.btn_to_personinfo);//这个控件是登陆按钮,与返回按钮是不同的
        btnToPersoninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredAccount=inputBoxAccount.getText().toString();
                String enteredPassword=inputBoxPassword.getText().toString();
                final String address  =  BaseApplication.get("hostaddress","http://169.254.24.205:8080/trip/") +"selectuser?username_login="+enteredAccount+ "&pwd_login=" +enteredPassword ;
                System.out.println(address);
                Log.e("address----",""+address);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url(address)
                                    .build();
                            Response response = client.newCall(request).execute();
                            String responseData = response.body().string();
                            Log.e("responseData----",""+responseData);

                            Gson gs = new Gson();
                            entity = gs.fromJson(responseData,entityClass);
                            Log.e("entity",""+entity);
                            user[0] = entity.getName();
                            user[1] = entity.getAccount();
                            user[2]= entity.getPicture();
                            user[3] = entity.getRongyun();
                            BaseApplication.set("username",user[0]);
                            BaseApplication.set("userid",user[1]);
                            BaseApplication.set("rongyun",user[3]);
                            Log.e("rongyun",""+user[3].trim());
                            BaseApplication.set("userpicture",BaseApplication.get("hostaddress","http://169.254.24.205:8080/trip/")+"image/"+user[1]+".jpg");
                            Log.e("picture",BaseApplication.get("hostaddress","http://169.254.24.205:8080/trip/")+"image/"+user[1]+".jpg");
                            if (user[0] != null){
                                Message message = new Message();
                                message.what = UPDATE_TEXT;
                                mHandler.sendEmptyMessage(message.what);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        labelToSignup=(TextView)findViewById(R.id.label_to_signup);
        labelToSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(personlogin.this, signupActivity.class),0);
            }
        });


    }

    //判断用户输入的账号和密码是否正确
    private boolean checkAccount(String enteredAccount,String enteredPassword){
        boolean x=false;
        for(personInfo em:personInfoList){
            if(em.getAccount().equals(enteredAccount)){
                if(em.getPassword().equals(enteredPassword)){
                    x=true;
                    break;
                }
            }
        }
        return x;
    }
    //根据账号查找到对应的personInfo对象
    private personInfo findPersonInfo(String enteredAccount){
        personInfo x=null;
        for(personInfo em:personInfoList){
            if(em.getAccount().equals(enteredAccount)){
                x=em;
            }
        }
        return x;
    }

    //给其它类访问personInfoList的接口
    public static ArrayList<personInfo> getPersonInfoList(){
        return personInfoList;
    }
    public static void setPersonInfoList(String oldAccount,personInfo newOne){
        for(personInfo em:personInfoList){
            if(em.getAccount().equals(oldAccount)){
                em.setAccount(newOne.getAccount());
                em.setPassword(newOne.getPassword());
                em.setName(newOne.getName());
                break;
            }
        }
    }



}