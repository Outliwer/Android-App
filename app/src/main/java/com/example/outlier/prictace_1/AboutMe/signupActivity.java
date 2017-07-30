package com.example.outlier.prictace_1.AboutMe;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.common.UploadUtil;
import com.example.outlier.prictace_1.AboutMe.AllClass.personInfo;
import com.example.outlier.prictace_1.AboutMe.AllClass.photoAdapter;
import com.example.outlier.prictace_1.AboutMe.AllClass.photoItem;
import com.example.outlier.prictace_1.AboutMe.Entity.BitmapEntity;
import com.example.outlier.prictace_1.AboutMe.chat.util.Entity;
import com.example.outlier.prictace_1.AboutMe.chat.util.GetToken;
import com.example.outlier.prictace_1.AboutMe.personlogin;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.outlier.prictace_1.BaseApplication;
import com.example.outlier.prictace_1.FourthFragment;
import com.example.outlier.prictace_1.R;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.outlier.prictace_1.AboutMe.personlogin.getPersonInfoList;

public class signupActivity extends AppCompatActivity {
    //定义所有的控件
    Bitmap bitmap;
    private TextView labelTitle;
    private ImageView iconBack;
    private TextView LabelUploadPictureBtn;
    private EditText inputBoxAccount;
    private EditText inputBoxPassword;
    private EditText inputBoxName;
    private TextView labelUploadPictureBtn;
    private Button btnSignupOver;
    private ImageView head_icon;
    String m = "0";
    String responseData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initImageLoader(this);
        EventBus.getDefault().register(this);
        initeView();
    }

    public void onEventMainThread(BitmapEntity entity){
        if(entity.getI() == 1){
            bitmap = entity.getBitmap();
            Log.e("bitmap",""+bitmap);
            head_icon.setImageBitmap(bitmap);
        }
    }

    private void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50*1024*1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs()
                // Remove for release app
                .memoryCache(new LruMemoryCache(4 * 1024 * 1024))
                .memoryCacheSize(4 * 1024 * 1024).build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

    private void initeView(){
        inputBoxAccount=(EditText)findViewById(R.id.inputBox_account);
        inputBoxPassword=(EditText)findViewById(R.id.inputBox_password);
        inputBoxName=(EditText)findViewById(R.id.inputBox_name);
        head_icon = (ImageView) findViewById(R.id.head_icon);

        //初始化标题框
        labelTitle=(TextView)findViewById(R.id.label_title);
        labelTitle.setText("注册");

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
               //转到takephoto界面
                startActivity(new Intent(signupActivity.this,takephoto.class));
            }
        });

        btnSignupOver=(Button)findViewById(R.id.btn_signupover);
        btnSignupOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//这里是注册完毕回到登陆界面，和点击返回键是不一样的
                final String enteredAccount=inputBoxAccount.getText().toString();
                final String enteredPassword=inputBoxPassword.getText().toString();
                final String enteredName=inputBoxName.getText().toString();
                final String url = "https://api.cn.ronghub.com/user/getToken.json";
                final String App_Key = "0vnjpoad0eyrz"; //开发者平台分配的 App Key。
                String App_Secret = "ZlpdEliqgZGyF";
                final String Timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳，从 1970 年 1 月 1 日 0 点 0 分 0 秒开始到现在的秒数。
                final String Nonce = String.valueOf(Math.floor(Math.random() * 1000000));//随机数，无长度限制。
                final String Signature = sha1(App_Secret + Nonce + Timestamp);//数据签名。



                if (bitmap == null)
                    m = "1";

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            OkHttpClient httpClient = new OkHttpClient();
                            RequestBody responsebody = new FormBody.Builder()
                                    .add("name",enteredName)
                                    .add("userId",enteredAccount)
                                    .add("portraitUri","")
                                    .build();
                            Request request = new Request.Builder()
                                    .url(url)
                                    .addHeader("App-Key", App_Key)
                                    .addHeader("Timestamp", Timestamp)
                                    .addHeader("Nonce", Nonce)
                                    .addHeader("Signature", Signature)
                                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                                    .post(responsebody)
                                    .build();
                            Response response = null;
                            response = httpClient.newCall(request).execute();
                            Log.e("response",""+response);
                            String text = response.body().string().trim();
                            Log.e(" response.body().string()",""+ text);
                            Gson gs = new Gson();
                            Entity entity;
                            Class<Entity> entityClass = Entity.class;
                            entity= gs.fromJson(text,entityClass);



                            BaseApplication.set("rongyun",entity.getToken().trim());


                            final String address = BaseApplication.get("hostaddress", "http://169.254.24.205:8080/trip/") +
                                    "register?userid_register=" + enteredAccount + "&pwd_login=" + enteredPassword + "&username_register="
                                    + enteredName + "&rongyun=" + BaseApplication.get("rongyun",
                                    "eeixs2SVI1K+Lh4MqtxsxMkXrDeL5RMTh5UEVEanmosjepnrFgzgjaQsqrAjdxLnin8mD4PPl1muksuRELbwdw==") + "&choose=" + m;
                            System.out.println(address);
                            Log.e("address----", "" + address);
                            OkHttpClient client = new OkHttpClient();
                            Request request_1 = new Request.Builder()
                                    .url(address)
                                    .build();
                            Response response_1 = client.newCall(request_1).execute();
                            responseData = response_1.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                            Log.e("responseData----", "" + responseData);

                            if (responseData.equals("true")) {
                                File file = new File("/mnt/sdcard/" + enteredAccount + ".jpg");
                                Log.e("file----",""+file);
                                try {
                                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                                    bos.flush();
                                    bos.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    System.out.println("broken");
                                }
                                Log.e("file", "" + file);
                                String result = UploadUtil.uploadFile(file, BaseApplication.get("hostaddress", "http://169.254.24.205:8080/trip/") + "UploadShipServlet");
                                Log.e("result", "" + result);

                                BaseApplication.set("username", enteredName);
                                BaseApplication.set("userid", enteredAccount);
                                BaseApplication.set("password", enteredPassword);
                                BaseApplication.set("choose",m);
                                finish();
                            } else {
                                Log.e("responseData----", "" + responseData);
                                //Toast.makeText(signupActivity.this, "用户名存在了", Toast.LENGTH_LONG).show();
                            }
                            /*
                            if (responseData == "true") {
                                BaseApplication.set("username", enteredName);
                                BaseApplication.set("userid", enteredAccount);
                                BaseApplication.set("userpicture", String.valueOf(bitmap));
                                Intent intent=new Intent(signupActivity.this,FourthFragment.class);
                                Bundle bundle=new Bundle();
                                bundle.putString("name",enteredName);
                                intent.putExtras(bundle);
                                intent.putExtra("bitmap",bitmap);
                                setResult(3);
                                finish();
                            }*/
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                    }
                }).start();


            }
        });


    }

    //检查要创建的账号是否和已经存在的账号重复
    private boolean checkOldAccount(String enteredAccount,String enteredPassword,String enteredName){
        boolean x=false;
        ArrayList<personInfo> tmp=getPersonInfoList();
        for(personInfo em:tmp){
            if(em.getAccount().equals(enteredAccount)){
                x=true;
                break;
            }
            else{
                if(em.getPassword().equals(enteredPassword)){
                    x=true;
                    break;
                }
                else{
                    if(em.getName().equals(enteredName)){
                        x=true;
                        break;
                    }
                    else{}
                }
            }
        }
        return x;
    }

    private static String sha1(String data){
        StringBuffer buf = new StringBuffer();
        try{
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(data.getBytes());
            byte[] bits = md.digest();
            for(int i = 0 ; i < bits.length;i++){
                int a = bits[i];
                if(a<0) a+=256;
                if(a<16) buf.append("0");
                buf.append(Integer.toHexString(a));
            }
        }catch(Exception e){

        }
        return buf.toString();
    }



}
