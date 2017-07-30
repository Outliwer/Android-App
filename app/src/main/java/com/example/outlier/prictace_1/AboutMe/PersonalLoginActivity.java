package com.example.outlier.prictace_1.AboutMe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputEditText;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.common.CatLoadingView;
import com.common.UploadUtil;
import com.example.outlier.prictace_1.AboutMe.Entity.BitmapEntity;
import com.example.outlier.prictace_1.AboutMe.chat.util.Entity;
import com.example.outlier.prictace_1.AboutMe.myFriend.userEntity;
import com.example.outlier.prictace_1.BaseApplication;
import com.example.outlier.prictace_1.CircleTransform;
import com.example.outlier.prictace_1.FourthFragment;
import com.example.outlier.prictace_1.MainActivity;
import com.example.outlier.prictace_1.R;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.squareup.picasso.Picasso;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;

import de.greenrobot.event.EventBus;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PersonalLoginActivity extends AppCompatActivity {
    private boolean isSigninScreen = true;
    private TextView tvSignupInvoker;
    private LinearLayout llSignup;
    private TextView tvSigninInvoker;
    private LinearLayout llSignin;
    private Button btnSignup;
    private Button btnSignin;
    Bitmap bitmap;
    private TextInputEditText inputBoxAccount;
    private TextInputEditText inputBoxPassword;
    private TextInputEditText registerAccount;
    private TextInputEditText registerPassword;
    private TextInputEditText registerName;
    public static final int UPDATE_TEXT = 1;
    public String []user = new String[4];
    String responseData;
    String m = "0";
    private ImageView head_icon;
    Message message = new Message();
    CatLoadingView mView;



    protected Handler mHandler =  new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    MainActivity.setStateLogged(true);
                    Intent intent=new Intent(PersonalLoginActivity.this,FourthFragment.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("account",user[0]);
                    bundle.putString("name",user[1]);
                    bundle.putString("headId",user[2]);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK,intent);
                    mView.dismiss();
                    finish();
                    break;
                case 2:
                    new AlertDialog.Builder(PersonalLoginActivity.this).setTitle("错误")//设置对话框标题
                            .setMessage("用户名或密码错误")//设置显示的内容
                            .setPositiveButton("返回",new DialogInterface.OnClickListener() {//添加确定按钮
                                @Override
                                public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                                }
                            }).setNegativeButton("",new DialogInterface.OnClickListener() {//添加返回按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {//响应事件

                        }
                    }).show();//在按键响应事件中显示此对话框
                    mView.dismiss();
                    break;
                case 3:
                    new AlertDialog.Builder(PersonalLoginActivity.this).setTitle("错误")//设置对话框标题
                            .setMessage("账户已经存在")//设置显示的内容
                            .setPositiveButton("返回",new DialogInterface.OnClickListener() {//添加确定按钮
                                @Override
                                public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                                }
                            }).setNegativeButton("",new DialogInterface.OnClickListener() {//添加返回按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {//响应事件

                        }
                    }).show();//在按键响应事件中显示此对话框
                    mView.dismiss();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_login);
        initImageLoader(this);
        EventBus.getDefault().register(this);

        tvSignupInvoker = (TextView) findViewById(R.id.tvSignupInvoker);
        tvSigninInvoker = (TextView) findViewById(R.id.tvSigninInvoker);

        btnSignup= (Button) findViewById(R.id.btnSignup);
        btnSignin= (Button) findViewById(R.id.btnSignin);

        head_icon = (ImageView) findViewById(R.id.head_icon);
        Picasso.with(PersonalLoginActivity.this).load(R.drawable.girl).transform(new CircleTransform()).into(head_icon);

        registerAccount = (TextInputEditText) findViewById(R.id.account);
        registerPassword = (TextInputEditText) findViewById(R.id.password);
        registerName = (TextInputEditText) findViewById(R.id.name);
        inputBoxAccount=(TextInputEditText)findViewById(R.id.inputBox_account);
        inputBoxPassword=(TextInputEditText)findViewById(R.id.inputBox_password);

        llSignup = (LinearLayout) findViewById(R.id.llSignup);
        llSignin = (LinearLayout) findViewById(R.id.llSignin);

        head_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //转到takephoto界面
                startActivity(new Intent(PersonalLoginActivity.this,takephoto.class));
            }
        });

        tvSignupInvoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSigninScreen = false;
                showSignupForm();
            }
        });

        tvSigninInvoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSigninScreen = true;
                showSigninForm();
            }
        });
        showSigninForm();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mView = new CatLoadingView();
                mView.show(getSupportFragmentManager(), "");
                Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_right_to_left);
                if(isSigninScreen)
                    btnSignup.startAnimation(clockwise);
                Log.e("--register--","adhkjajhdsjkhakjasdkhk");
                final String enteredAccount=registerAccount.getText().toString();
                final String enteredPassword=registerPassword.getText().toString();
                final String enteredName=registerName.getText().toString();
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
                            BaseApplication.set("username", enteredName);
                            BaseApplication.set("userid", enteredAccount);
                            BaseApplication.set("password", enteredPassword);
                            BaseApplication.set("choose", m);
                            if (bitmap != null){
                                File file = new File("/mnt/sdcard/" + enteredAccount + ".jpg");
                            Log.e("file----", "" + file);
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
                                mView.dismiss();
                                finish();
                        }
                        } else {
                            Log.e("responseData----", "" + responseData);
                            message.what = 3;
                            mHandler.sendEmptyMessage(message.what);
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

        btnSignin.setOnClickListener(new View.OnClickListener() {
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
                            mView = new CatLoadingView();
                            mView.show(getSupportFragmentManager(), "");
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url(address)
                                    .build();
                            Response response = client.newCall(request).execute();
                            String responseData = response.body().string();
                            if (responseData.equals("false")){
                                Log.e("-----","密码错误");
                                message.what = 2;
                                mHandler.sendEmptyMessage(message.what);
                            }else {
                                Log.e("responseData----", "" + responseData);
                                userEntity entity;
                                Class<userEntity> entityClass = userEntity.class;
                                Gson gs = new Gson();
                                entity = gs.fromJson(responseData, entityClass);
                                Log.e("entity", "" + entity);

                                user[0] = entity.getName();
                                user[1] = entity.getAccount();
                                user[2] = entity.getPicture();
                                user[3] = entity.getRongyun();
                                BaseApplication.set("username", user[0]);
                                BaseApplication.set("userid", user[1]);
                                BaseApplication.set("rongyun", user[3]);
                                Log.e("rongyun", "" + user[3].trim());
                                BaseApplication.set("userpicture", BaseApplication.get("hostaddress", "http://169.254.24.205:8080/trip/") + "image/" + user[1] + ".jpg");
                                Log.e("picture", BaseApplication.get("hostaddress", "http://169.254.24.205:8080/trip/") + "image/" + user[1] + ".jpg");
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
    }

    public void onEventMainThread(BitmapEntity entity){
        if(entity.getI() == 1){
            bitmap = entity.getBitmap();
            Log.e("bitmap",""+bitmap);
            File file = new File("/mnt/sdcard/" +"Test"+ ".jpg");
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
            Picasso.with(PersonalLoginActivity.this).load(file).transform(new CircleTransform()).into(head_icon);
        }
    }

    private void showSignupForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.15f;
        llSignin.requestLayout();


        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.85f;
        llSignup.requestLayout();

        tvSignupInvoker.setVisibility(View.GONE);
        tvSigninInvoker.setVisibility(View.VISIBLE);
        Animation translate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_right_to_left);
        llSignup.startAnimation(translate);

        Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_right_to_left);
        btnSignup.startAnimation(clockwise);

    }

    private void showSigninForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.85f;
        llSignin.requestLayout();


        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.15f;
        llSignup.requestLayout();

        Animation translate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_left_to_right);
        llSignin.startAnimation(translate);

        tvSignupInvoker.setVisibility(View.VISIBLE);
        tvSigninInvoker.setVisibility(View.GONE);
        Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_left_to_right);
        btnSignin.startAnimation(clockwise);
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

}