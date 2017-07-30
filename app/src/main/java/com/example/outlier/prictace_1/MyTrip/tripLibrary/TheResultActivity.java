package com.example.outlier.prictace_1.MyTrip.tripLibrary;

/**
 * Created by outlier on 2017/7/18.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.outlier.prictace_1.AboutMe.AllClass.commentAdapter;
import com.example.outlier.prictace_1.AboutMe.AllClass.commentItem;
import com.example.outlier.prictace_1.MyTrip.tripLibrary.fragment.ForthRecyclerViewFragment;
import com.example.outlier.prictace_1.MyTrip.tripLibrary.fragment.RecyclerViewFragment;
import com.example.outlier.prictace_1.MyTrip.tripLibrary.fragment.SecondRecyclerViewFragment;
import com.example.outlier.prictace_1.MyTrip.tripLibrary.fragment.ThirdRecyclerViewFragment;
import com.example.outlier.prictace_1.R;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TheResultActivity extends DrawerActivity {

    @BindView(R.id.materialViewPager)
    MaterialViewPager mViewPager;
    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private ListView mDrawerList;
    private List<commentItem> commentList=new ArrayList<>();
    private commentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theresult);
        setTitle("我的行程");
        ButterKnife.bind(this);

        //toolbar = (Toolbar) findViewById(R.id.tl_custom);
        toolbar = mViewPager.getToolbar();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        initeComment();
        adapter=new commentAdapter(TheResultActivity.this, R.layout.comment_item,commentList);
        mDrawerList.setAdapter(adapter);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);




        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 4) {
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
                        return "旅店";
                    case 2:
                        return "美食";
                    case 3:
                        return "交通";
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

        final View logo = findViewById(R.id.logo_white);

    }

    private void initeComment(){
        commentItem firstOne=new commentItem("2017/3/4","阿拉斯加","随便写的一段话.......");
        commentList.add(firstOne);
        commentItem secondOne=new commentItem("2017/3/4","纽约","随便写的一段话.......");
        commentList.add(secondOne);
        commentItem thirdOne=new commentItem("2017/3/4","巴黎","随便写的一段话.......");
        commentList.add(thirdOne);
        commentItem forthOne=new commentItem("2017/3/4","孟加拉","随便写的一段话.......");
        commentList.add(forthOne);
        commentItem fifthOne=new commentItem("2017/3/4","新德里","随便写的一段话.......");
        commentList.add(fifthOne);
    }
}
