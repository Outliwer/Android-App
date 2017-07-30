package com.example.outlier.prictace_1.Comment.RaiderA;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.common.CatLoadingView;
import com.common.UploadUtil;
import com.example.outlier.prictace_1.AboutMe.PersonalLoginActivity;
import com.example.outlier.prictace_1.BaseApplication;
import com.example.outlier.prictace_1.HomePage.Hotel.HotelActivity;
import com.example.outlier.prictace_1.HomePage.View.ViewAnswerActivity.ViewAnswerActivity;
import com.example.outlier.prictace_1.R;
import com.example.outlier.prictace_1.SecondPage;
import com.example.outlier.prictace_1.ThirdFragment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RaiderActivity extends AppCompatActivity {

    //初始化标题栏
    private ImageView titleLeftImv;
    private TextView titleTv;
    String cityId = "";
    CatLoadingView mView;


    private Button btn_chooseView;
    private EditText value;

    private static final int REQUEST_CODE_PICK_IMAGE = 1023;
    private static final int REQUEST_CODE_CAPTURE_CAMEIA = 1022;
    private RichTextEditor editor;
    private View btn1, btn2, btn3;
    private View.OnClickListener btnListener;

    private static final File PHOTO_DIR = new File(
            Environment.getExternalStorageDirectory() + "/DCIM/Camera");
    private File mCurrentPhotoFile;// 照相机拍照得到的图片

    Message message = new Message();
    protected Handler mHandler =  new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 3:
                    mView.dismiss();
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_raider);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }

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
        titleTv.setText("编写攻略");
        editor = (RichTextEditor) findViewById(R.id.richEditor);
        btnListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                editor.hideKeyBoard();
                if (v.getId() == btn1.getId()) {
                    // 打开系统相册
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");// 相片类型
                    startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
                } else if (v.getId() == btn2.getId()) {
                    // 打开相机
                    openCamera();
                } else if (v.getId() == btn3.getId()) {
                    List<RichTextEditor.EditData> editList = editor.buildEditData();
                    // 下面的代码可以上传、或者保存，请自行实现
                    if (cityId.equals("")){
                        new AlertDialog.Builder(RaiderActivity.this).setTitle("错误")//设置对话框标题
                                .setMessage("输入城市")//设置显示的内容
                                .setPositiveButton("返回",new DialogInterface.OnClickListener() {//添加确定按钮
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                                    }
                                }).setNegativeButton("",new DialogInterface.OnClickListener() {//添加返回按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//响应事件

                            }
                        }).show();//在按键响应事件中显示此对话框
                    }else if (BaseApplication.get("userid","") == ""){
                        new AlertDialog.Builder(RaiderActivity.this).setTitle("错误")//设置对话框标题
                                .setMessage("请先登录")//设置显示的内容
                                .setPositiveButton("返回",new DialogInterface.OnClickListener() {//添加确定按钮
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                                    }
                                }).setNegativeButton("",new DialogInterface.OnClickListener() {//添加返回按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//响应事件

                            }
                        }).show();//在按键响应事件中显示此对话框
                    }else {
                        dealEditData(editList);
                    }
                }
            }
        };

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);

        btn1.setOnClickListener(btnListener);
        btn2.setOnClickListener(btnListener);
        btn3.setOnClickListener(btnListener);
        btn_chooseView = (Button) findViewById(R.id.btn_chooseView);
        value = (EditText) findViewById(R.id.value);
        btn_chooseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(RaiderActivity.this, ViewAnswerActivity.class),3);
            }
        });
        cityId = value.getText().toString().trim();
    }

    /**
     * 负责处理编辑数据提交等事宜，请自行实现
     */
    protected void dealEditData(final List<RichTextEditor.EditData> editList) {
        mView = new CatLoadingView();
        mView.show(getSupportFragmentManager(), "");
        final String host = BaseApplication.get("userid","1234")+getPhotoFileName();
        final String time = getPhotoFileName();
        final String hostaddress = BaseApplication.get("hostaddress", "http://169.254.24.205:8080/trip/");

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                for (int i = 0; i < editList.size(); i++) {
                                    final int finalI = i;
                                    Log.e("cityId", "" + cityId);
                                    Log.e("bitmap", "" + editList.get(finalI).bitmap);
                                    Log.e("inputStr", "" + editList.get(finalI).inputStr);
                                    OkHttpClient client = new OkHttpClient();
                                    String responseData = "";

                                    if (editList.get(finalI).bitmap == null) {
                                        Request request = new Request.Builder()
                                                .url(hostaddress + "AddComment?commentlist_id=" + host + "&user_id=" + BaseApplication.get("userid", "1234")
                                                        + "&city=" + cityId + "&string=" + editList.get(finalI).inputStr + "&stringlocation=" + finalI +
                                                        "&photo=&photolocation=n")
                                                .build();
                                        Response response = null;
                                        try {
                                            response = client.newCall(request).execute();
                                            responseData = response.body().string();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    } else if (editList.get(finalI).inputStr == null) {

                                        Request request = new Request.Builder()
                                                .url(hostaddress + "AddComment?commentlist_id=" + host + "&user_id=" + BaseApplication.get("userid", "1234")
                                                        + "&city=" + cityId + "&string=&stringlocation=n" +
                                                        "&photo=comment/" + host + finalI + ".jpg" + "&photolocation=" + finalI)
                                                .build();
                                        Response response = null;
                                        try {
                                            response = client.newCall(request).execute();
                                            responseData = response.body().string();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                        File file = new File("/mnt/sdcard/" + host + ".jpg");
                                        try {
                                            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                                            editList.get(finalI).bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                                            bos.flush();
                                            bos.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                            System.out.println("broken");
                                        }
                                        Log.e("file", "" + file);
                                        String result = UploadUtil.uploadFile(file, hostaddress + "UploadComment");
                                        // + "?id=" + BaseApplication.get("userid", "1234") + "&str=" + editList.get(finalI).inputStr + "&city=" + cityId + "&host=" +getPhotoFileName());
                                        Log.e("result", "" + result);
                                    } else {
                                        Request request = new Request.Builder()
                                                .url(hostaddress + "AddComment?commentlist_id=" + host + "&user_id=" + BaseApplication.get("userid", "1234")
                                                        + "&city=" + cityId + "&string=" + editList.get(finalI).inputStr + "&stringlocation=" + finalI +
                                                        "&photo=comment/" + host + finalI + ".jpg" + "&photolocation=" + finalI)
                                                .build();
                                        Response response = null;
                                        try {
                                            response = client.newCall(request).execute();
                                            responseData = response.body().string();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                        File file = new File("/mnt/sdcard/" + host + ".jpg");
                                        try {
                                            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                                            editList.get(finalI).bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                                            bos.flush();
                                            bos.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                            System.out.println("broken");
                                        }
                                        Log.e("file", "" + file);
                                        String result = UploadUtil.uploadFile(file, hostaddress + "UploadComment");
                                        // + "?id=" + BaseApplication.get("userid", "1234") + "&str=" + editList.get(finalI).inputStr + "&city=" + cityId + "&host=" +getPhotoFileName());
                                        Log.e("result", "" + result);
                                    }
                                }

                                String url = BaseApplication.get("hostaddress", "") + "getComment?id=" + BaseApplication.get("userid", "");

                                OkHttpClient httpClient = new OkHttpClient();
                                Request request = new Request.Builder()
                                        .url(url)
                                        .build();
                                Response response = null;
                                try {
                                    response = httpClient.newCall(request).execute();
                                    Log.e("response", "" + response);
                                    String text = response.body().string().trim();
                                    BaseApplication.set("text", text);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                message.what = 3;
                                mHandler.sendEmptyMessage(message.what);
                            }
                        }).start();
                }




    protected void openCamera() {
        try {
            // Launch camera to take photo for selected contact
            PHOTO_DIR.mkdirs();// 创建照片的存储目录
            mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());// 给新照的照片文件命名
            final Intent intent = getTakePickIntent(mCurrentPhotoFile);
            startActivityForResult(intent, REQUEST_CODE_CAPTURE_CAMEIA);
        } catch (ActivityNotFoundException e) {
        }
    }

    public static Intent getTakePickIntent(File f) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        return intent;
    }

    /**
     * 用当前时间给取得的图片命名
     */
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyy-MM-ddHH:mm:ss");
        return  dateFormat.format(date);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 3){
            String phone = data.getStringExtra("city");
            value.setText(phone);
            cityId = phone;
        }

        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_PICK_IMAGE) {
            Uri uri = data.getData();
            insertBitmap(getRealFilePath(uri));
        } else if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA) {
            insertBitmap(mCurrentPhotoFile.getAbsolutePath());
        }
    }

    /**
     * 添加图片到富文本剪辑器
     *
     * @param imagePath
     */
    private void insertBitmap(String imagePath) {
        editor.insertImage(imagePath);
    }

    /**
     * 根据Uri获取图片文件的绝对路径
     */
    public String getRealFilePath(final Uri uri) {
        if (null == uri) {
            return null;
        }

        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = getContentResolver().query(uri,
                    new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

}

