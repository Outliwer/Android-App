package com.example.outlier.prictace_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.outlier.prictace_1.HomePage.Guide.GuideActivity;
import com.example.outlier.prictace_1.HomePage.Guide.WayGuideActivity;
import com.example.outlier.prictace_1.HomePage.Hotel.HotelActivity;
import com.example.outlier.prictace_1.HomePage.Recommend.RecommendActivity;
import com.example.outlier.prictace_1.HomePage.Search.SearchActivity;
import com.example.outlier.prictace_1.HomePage.Tiket.TiketActivity;
import com.example.outlier.prictace_1.HomePage.Topic.TopicActivity;
import com.example.outlier.prictace_1.HomePage.View.ViewActivity;

import java.util.ArrayList;


/**
 * Created by outlier on 2017/7/9.
 */

public class FirstFragment extends Fragment {


    private AHBottomNavigation bottomNavigation;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();

    private ImageButton btn_inRecommend;
    private ImageButton btn_inTopic_2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_1, viewGroup, false);
        initView(view);
        initUI();
        action();
        return view;
    }

    private void initView(View view){
        //点击到搜索界面


        /*btn_inHotel = (Button) view.findViewById(R.id.btn_inHotel);
        btn_inMap = (Button) view.findViewById(R.id.btn_inMap);
        btn_inView = (Button) view.findViewById(R.id.btn_inView);
        btn_inTiket = (Button) view.findViewById(R.id.btn_inTiket);*/
        //点击到专题界面


        btn_inRecommend = (ImageButton) view.findViewById(R.id.btn_inRecommend);
        //点击到专题界面
        btn_inRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RecommendActivity.class));
            }
        });

        btn_inTopic_2 = (ImageButton) view.findViewById(R.id.btn_inTopic_2);
        //点击到测试界面
        btn_inTopic_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WayGuideActivity.class));
            }
        });

        bottomNavigation = (AHBottomNavigation) view.findViewById(R.id.bottom_navigation_1);
        Log.e("bottomNavigation",""+bottomNavigation);

    }

        //相关事件


    private void initUI() {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("景点", R.drawable.buttonview_03, R.color.color_tab_2);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("酒店", R.drawable.buttonhotel, R.color.color_tab_1);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("车票", R.drawable.buttontik, R.color.color_tab_2);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("导航", ContextCompat.getDrawable(getActivity(), R.drawable.buttonmap), ContextCompat.getColor(getActivity(), R.color.color_tab_1));
        AHBottomNavigationItem item5 = new AHBottomNavigationItem("搜索", R.drawable.buttonsearch, R.color.color_tab_2);
        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        bottomNavigationItems.add(item4);
        bottomNavigationItems.add(item5);
        Log.e("bottomNavigationItemssize",""+bottomNavigationItems.size());
        bottomNavigation.addItems(bottomNavigationItems);


        bottomNavigation.setTranslucentNavigationEnabled(true);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getActivity(), ViewActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), HotelActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), TiketActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), TopicActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), SearchActivity.class));
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
}
