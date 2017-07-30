package com.example.outlier.prictace_1.AboutMe;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.outlier.prictace_1.AboutMe.Entity.BitmapEntity;
import com.example.outlier.prictace_1.BaseApplication;
import com.example.outlier.prictace_1.MainActivity;
import com.example.outlier.prictace_1.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.jar.Manifest;

import de.greenrobot.event.EventBus;

import static android.R.attr.data;
import static com.example.outlier.prictace_1.MainActivity.getStateLogged;

public class takephoto extends AppCompatActivity {
    public static final int TAKE_PHOTO=1;
    public static final int CHOOSE_PHOTO=2;
    private Uri imageUri;
    Bitmap bitmap = null;

    private TextView labelTitle;
    private ImageView iconBack;


    private ImageView headIcon;
    private TextView labelUploadPhoto;
    private TextView labelTakephoto;
    private Button useHeadicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takephoto);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        initeView();
    }


    private void initeView(){

        //初始化标题栏
        labelTitle=(TextView)findViewById(R.id.label_title);
        labelTitle.setText("上传图片");

        headIcon=(ImageView)findViewById(R.id.head_icon);


        //定义所有点击事件


        useHeadicon = (Button) findViewById(R.id.btn_useheadicon);
        useHeadicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onClick---","onClick");
                EventBus.getDefault().post(
                        new BitmapEntity(1,bitmap)
                );
//                Intent intent = new Intent();
//                intent.putExtra("bitmap",bitmap);
//                setResult(1,intent);
                finish();
            }
        });

        iconBack=(ImageView)findViewById(R.id.icon_back);
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        labelTakephoto=(TextView) findViewById(R.id.label_takephoto);
        labelTakephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建File对象，用来存储之后拍出的图片
                File outputImage=new File(getExternalCacheDir(),"output_image.jpg");
                try{
                    if(outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT>=24){
                    imageUri= FileProvider.getUriForFile(takephoto.this,"com.example.outlier.prictace_1.provider",outputImage);
                }
                else{
                    imageUri=Uri.fromFile(outputImage);
                }

                //启动相机程序
                Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);

            }
        });


        labelUploadPhoto=(TextView) findViewById(R.id.label_uploadphoto);
        labelUploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbum();
            }
        });

    }
    private void openAlbum(){
        Intent intent=new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/");
        startActivityForResult(intent,CHOOSE_PHOTO);//打开相册

    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK){
                    try{
                        //将拍摄的照片显示出来
                        bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        headIcon.setImageBitmap(bitmap);
                        Log.e("bitmap",""+bitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                //把选择的照片显示出来
                if(resultCode==RESULT_OK){
                    //判断手机版本号是否大于4.4
                    if(Build.VERSION.SDK_INT>=19){
                        handleImageOnKitKat(data);
                    }
                    else{
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath=null;
        Uri uri=data.getData();
        if(DocumentsContract.isDocumentUri(this,uri)){
            //如果是document类型的Uri，则通过document id处理
            String docId=DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id=docId.split(":")[1];//解析出数字格式的id
                String selection=MediaStore.Images.Media._ID+"="+id;
                imagePath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }
            else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri= ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath=getImagePath(contentUri,null);
            }
        }
        else if("content".equalsIgnoreCase(uri.getScheme())){
            //如果是content类型的uri,则使用普通方法处理
            imagePath=getImagePath(uri,null);
        }
        else if("file".equalsIgnoreCase(uri.getScheme())){
            //如果是file类型，直接获取图片路径即可
            imagePath=uri.getPath();
        }
        //根据图片路径显示图片
        displayImage(imagePath);

    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri=data.getData();
        String imagePath=getImagePath(uri,null);
        displayImage(imagePath);
    }


    private String getImagePath(Uri uri,String selection){
        String path=null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor=getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private  void displayImage(String imagePath){
        if(imagePath!=null){
            bitmap=BitmapFactory.decodeFile(imagePath);
            headIcon.setImageBitmap(bitmap);
            Log.e("bitmap",""+bitmap);
        }else{
            Toast.makeText(this,"获取图片失败_(:з」∠)_",Toast.LENGTH_SHORT).show();
        }
    }

}
