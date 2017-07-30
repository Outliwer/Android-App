package com.example.outlier.prictace_1.AboutMe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.example.outlier.prictace_1.AboutMe.AllClass.foodAdapter;
import com.example.outlier.prictace_1.AboutMe.AllClass.foodItem;
import com.example.outlier.prictace_1.AboutMe.AllClass.hotelAdapter;
import com.example.outlier.prictace_1.AboutMe.AllClass.hotelItem;
import com.example.outlier.prictace_1.AboutMe.AllClass.otherStrategyAdapter;
import com.example.outlier.prictace_1.AboutMe.AllClass.otherStrategyItem;
import com.example.outlier.prictace_1.AboutMe.AllClass.sightAdapter;
import com.example.outlier.prictace_1.AboutMe.AllClass.sightItem;
import com.example.outlier.prictace_1.AboutMe.fragment.ForthRecyclerViewFragment;
import com.example.outlier.prictace_1.AboutMe.fragment.RecyclerViewFragment;
import com.example.outlier.prictace_1.AboutMe.fragment.SecondRecyclerViewFragment;
import com.example.outlier.prictace_1.AboutMe.fragment.ThirdRecyclerViewFragment;
import com.example.outlier.prictace_1.BaseApplication;
import com.example.outlier.prictace_1.R;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class mycollection extends DrawerActivity {


    @BindView(R.id.materialViewPager)
     MaterialViewPager mViewPager;


    private List<sightItem> sightItemList=new ArrayList<>();
    private ListView sightListView;
    private sightAdapter firstAdapter;

    private List<hotelItem> hotelItemList=new ArrayList<>();
    private ListView hotelListView;
    private hotelAdapter secondAdapter;

    private List<foodItem> foodItemList=new ArrayList<>();
    private ListView foodListView;
    private foodAdapter thirdAdapter;

    private List<otherStrategyItem> otherStrategyItemList=new ArrayList<>();
    private ListView otherStrategyListView;
    private otherStrategyAdapter forthAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollection);
        setTitle("");
        initeView();

    }






    private void initeView(){
        ButterKnife.bind(this);

        final Toolbar toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                Log.e("position",""+position);
                switch (position) {
                    case 0:
                        return RecyclerViewFragment.newInstance();
                    case 1:
                        return SecondRecyclerViewFragment.newInstance();
                    case 2:
                        return ThirdRecyclerViewFragment.newInstance();
                    case 3:
                        return ForthRecyclerViewFragment.newInstance();
                    default:
                        return RecyclerViewFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 4) {
                    case 0:
                        return "景点";
                    case 1:
                        return "酒店";
                    case 2:
                        return "美食";
                    case 3:
                        return "指南";
                }
                return "";
            }
        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                "http://phandroid.s3.amazonaws.com/wp-content/uploads/2014/06/android_google_moutain_google_now_1920x1080_wallpaper_Wallpaper-HD_2560x1600_www.paperhi.com_-640x400.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "http://www.hdiphonewallpapers.us/phone-wallpapers/540x960-1/540x960-mobile-wallpapers-hd-2218x5ox3.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        
    }
}
