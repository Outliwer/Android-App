package com.example.outlier.prictace_1.AboutMe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.common.ImageLoaderUtils;
import com.example.outlier.prictace_1.AboutMe.AllClass.commentAdapter;
import com.example.outlier.prictace_1.AboutMe.AllClass.photoAdapter;
import com.example.outlier.prictace_1.AboutMe.AllClass.photoItem;
import com.example.outlier.prictace_1.AboutMe.Entity.BitmapEntity;
import com.example.outlier.prictace_1.AboutMe.myFriend.myfriendlist;
import com.example.outlier.prictace_1.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class myphoto extends AppCompatActivity {
    private TextView labelTitle;
    private ImageView iconBack;
    private ImageView btnAddphoto;
    Bitmap bitmap;

    private List<photoItem> photoItemList=new ArrayList<>();

    private ListView listView;
    private photoAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myphoto);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        initImageLoader(this);
        EventBus.getDefault().register(this);

        //初始化相片列表
        initePhoto();
        adapter=new photoAdapter(myphoto.this,R.layout.photo_item,photoItemList);
        listView=(ListView)findViewById(R.id.list_photo);
        listView.setAdapter(adapter);
        initeView();
    }

    public void onEventMainThread(BitmapEntity entity){
        if(entity.getI() == 1){
            bitmap = entity.getBitmap();
            Log.e("bitmap",""+bitmap);
            photoItem third = new photoItem(bitmap, "云南");
            photoItemList.add(third);
            Log.e("photoItemList",""+photoItemList);
            adapter = new photoAdapter(myphoto.this, R.layout.photo_item, photoItemList);
            listView.setAdapter(adapter);
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

    private void initePhoto(){

        new Thread(runnable).start();


        /*for(int i=0;i<2;i++){
            photoItem second=new photoItem(ImageLoaderUtils.GetLocalOrNetBitmap("http://imglf0.ph.126.net/MhcQfnYutmaCbWmsbRYwZA==/2859504288504494007.jpg"),"夏威夷");
            photoItemList.add(second);

        }
        for(int i=0;i<2;i++){
            photoItem third=new photoItem(ImageLoaderUtils.GetLocalOrNetBitmap("http://imglf0.ph.126.net/MhcQfnYutmaCbWmsbRYwZA==/2859504288504494007.jpg"),"云南");
            photoItemList.add(third);

        }*/

    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.e("ImageLoaderUtils.GetLocalOrNetBitmap(\"assets/button_50.png\")",""+ImageLoaderUtils.GetLocalOrNetBitmap("/assets/button_50.png"));
            photoItem first=new photoItem(ImageLoaderUtils.GetLocalOrNetBitmap("/assets/button_50.png"),"三亚");
            photoItemList.add(first);
        }
    };

    private void initeView(){
        //初始化标题栏
        labelTitle=(TextView)findViewById(R.id.label_title);
        labelTitle.setText("我的照片");

        //定义所有点击事件
        iconBack=(ImageView)findViewById(R.id.icon_back);
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAddphoto=(ImageView)findViewById(R.id.btn_addphoto);
        btnAddphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到上传图片界面
                startActivity(new Intent(myphoto.this,takephoto.class));
            }
        });

        //对于listView中的长按事件删除功能
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {
                //定义AlertDialog.Builder对象，当长按列表项的时候弹出确认删除对话框
                AlertDialog.Builder builder=new AlertDialog.Builder(myphoto.this);
                builder.setMessage("确定删除?");
                builder.setTitle("提示");
                //添加AlertDialog.Builder对象的setPositiveButton()方法
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(photoItemList.remove(position)!=null){
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
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == 1){
//            bitmap = data.getParcelableExtra("bitmap");
//            photoItem third=new photoItem(bitmap,"云南");
//            photoItemList.add(third);
//        }
//    }
//

}
