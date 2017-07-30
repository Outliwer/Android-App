package com.example.outlier.prictace_1;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.outlier.prictace_1.HomePage.Search.CycleView.ADInfo;
import com.example.outlier.prictace_1.HomePage.Search.CycleView.CycleViewPager;
import com.example.outlier.prictace_1.HomePage.Search.CycleView.ViewFactory;
import com.example.outlier.prictace_1.HomePage.Search.SearchDetailActivity;
import com.example.outlier.prictace_1.HomePage.Tiket.datetimepicker.SecondSeat;
import com.example.outlier.prictace_1.MyTrip.AddTripActivity;
import com.example.outlier.prictace_1.MyTrip.library.EuclidActivity;
import com.example.outlier.prictace_1.MyTrip.library.EuclidListAdapter;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class MainActivity extends AppCompatActivity {

    //这个变量表明当前是否处于登陆状态
    private static boolean stateLogged=false;
    public static boolean getStateLogged(){return stateLogged;}
    public static void setStateLogged(boolean i){
        stateLogged=i;
    }
    //标题
    private TextView title_text_tv;
    private ImageView icon_back;
    //定义Fragement对象
    private Fragment fg1;
    private Fragment fg2;
    private Fragment fg3;
    private Fragment fg4;

    // 定义FragmentManager对象管理器
    private FragmentManager fragmentManager;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private Handler handler = new Handler();

    // UI
    private AHBottomNavigation bottomNavigation;
    private Toolbar toolbar;
    LinearLayout control;


    //实现轮播
    private List<ImageView> views = new ArrayList<ImageView>();
    private List<ADInfo> infos = new ArrayList<ADInfo>();
    private CycleViewPager cycleViewPager;

    private String[] imageUrls = {
            "http://imglf0.ph.126.net/K2-GL6A2vQf-o0tJwJ2Bpg==/2267562412481183403.jpg",
            "http://imglf1.ph.126.net/ve_LvDBSVwZAHp2rqtDfkQ==/786722560006505764.jpg",
            "http://imglf0.ph.126.net/GBQH6n4fwNowGcRIKf_jzw==/6608202321887883258.jpg",
            "http://imglf1.ph.126.net/vKCDcEqPTdcGhRv9aKPu-w==/6598131894890693017.jpg",
            "http://imglf1.ph.126.net/28vUHQwk9F8pjM62PSr1-A==/6598283627494962208.jpg"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RongIM.init(this);
        setContentView(R.layout.activity_main);
        BaseApplication.set("hostaddress","http://192.168.43.8:8080/trip/");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }

        setSupportActionBar(null);
        fragmentManager = getSupportFragmentManager();
        initImageLoader(this);
        //初始化控件
        cycleViewPager = (CycleViewPager) getFragmentManager()
                .findFragmentById(R.id.fragment_cycle_viewpager_content);
        control = (LinearLayout) findViewById(R.id.control);
        initView();
        //加载第一个选项卡

        setChioceItem(0);
        initUI();
        action();
        configImageLoader();
        initialize();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    /**
     * Init UI
     */
    private void initUI() {
        icon_back = (ImageView) findViewById(R.id.icon_back);
        title_text_tv = (TextView) findViewById(R.id.title_text_tv);
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.buttonhome_06, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.buttonhome_08, R.color.color_tab_2);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.buttonhome_11, R.color.color_tab_1);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(getString(R.string.tab_4), ContextCompat.getDrawable(this, R.drawable.buttonhome_03), ContextCompat.getColor(this, R.color.color_tab_2));

        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        bottomNavigationItems.add(item4);

        bottomNavigation.addItems(bottomNavigationItems);


        bottomNavigation.setTranslucentNavigationEnabled(true);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0:
                        setChioceItem(0);
                        break;
                    case 1:
                        setChioceItem(1);
                        break;
                    case 2:
                        setChioceItem(2);
                        break;
                    case 3:
                        setChioceItem(3);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
    public void action(){
        bottomNavigation.setColored(true);
        bottomNavigation.restoreBottomNavigation(true);
        bottomNavigation.setSelectedBackgroundVisible(true);
        AHBottomNavigation.TitleState state = AHBottomNavigation.TitleState.ALWAYS_SHOW;
        bottomNavigation.setTitleState(state);
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

    private void initView() {
        icon_back = (ImageView) findViewById(R.id.icon_back);
        title_text_tv = (TextView) findViewById(R.id.title_text_tv);
    }
    /**
     * 设置点击选项卡的事件处理
     *
     * @param index 选项卡的标号：0, 1, 2, 3
     */
    private void setChioceItem(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragments(fragmentTransaction);
        switch (index) {
            case 0:
            // firstImage.setImageResource(R.drawable.XXXX); 需要的话自行修改
            // firstLayout.setBackgroundColor(gray);
            // 如果fg1为空，则创建一个并添加到界面上
                if (fg1 == null) {
                    fg1 = new FirstFragment();
                    Log.e("",""+fg1);
                    fragmentTransaction.add(R.id.content, fg1);
                } else {
            // 如果不为空，则直接将它显示出来
                    fragmentTransaction.show(fg1);

                }
                title_text_tv.setText("走山看水行天下");
                icon_back.setVisibility(View.INVISIBLE);
                control.setVisibility(View.VISIBLE);


                break;
            case 1:
                title_text_tv.setText("行程");
                icon_back.setVisibility(View.INVISIBLE);
                control.setVisibility(View.GONE);
                if (fg2 == null) {
                    fg2 = new SecondPage();
                    fragmentTransaction.add(R.id.content, fg2);
                } else {
                    fragmentTransaction.show(fg2);
                }
                break;
            case 2:
            // thirdImage.setImageResource(R.drawable.XXXX);
                // thirdLayout.setBackgroundColor(gray);
                if (fg3 == null) {
                    fg3 = new ThirdFragment();
                    fragmentTransaction.add(R.id.content, fg3);
                } else {
                    fragmentTransaction.show(fg3);
                }
                title_text_tv.setText("点评");
                icon_back.setVisibility(View.INVISIBLE);
                control.setVisibility(View.GONE);


                break;
            case 3:
            // fourthImage.setImageResource(R.drawable.XXXX);
                // fourthLayout.setBackgroundColor(gray);
                if (fg4 == null) {
                    fg4 = new FourthFragment();
                    fragmentTransaction.add(R.id.content, fg4);
                } else {
                    fragmentTransaction.show(fg4);
                }
                title_text_tv.setText("我的");
                icon_back.setVisibility(View.INVISIBLE);
                control.setVisibility(View.GONE);

                break;
        }
        fragmentTransaction.commit(); // 提交
    }
    /**
     * 隐藏Fragment
     *
     * @param fragmentTransaction
     */
    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (fg1 != null) {
            fragmentTransaction.hide(fg1);
        }
        if (fg3 != null) {
            fragmentTransaction.hide(fg3);
        }
        if (fg2 != null){
            fragmentTransaction.hide(fg2);
        }
        if (fg4 != null) {
            fragmentTransaction.hide(fg4);
        }
    }

    @SuppressLint("NewApi")
    private void initialize() {
        for(int i = 0; i < imageUrls.length; i ++){
            ADInfo info = new ADInfo();
            info.setUrl(imageUrls[i]);
            info.setContent("图片-->" + i );
            infos.add(info);
        }

        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(this, infos.get(infos.size() - 1).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ViewFactory.getImageView(this, infos.get(i).getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(this, infos.get(0).getUrl()));

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, infos, mAdCycleViewListener);
        //设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(2000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }

    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {

        @Override
        public void onImageClick(ADInfo info, int position, View imageView) {
            if (cycleViewPager.isCycle()) {
                position = position - 1;
                Toast.makeText(MainActivity.this,
                        "position-->" + info.getContent(), Toast.LENGTH_SHORT)
                        .show();
            }

        }

    };

    /**
     * 配置ImageLoder
     */
    private void configImageLoader() {
        // 初始化ImageLoader
        @SuppressWarnings("deprecation")
        DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.icon_stub) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.icon_empty) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.icon_error) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                // .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }
}


