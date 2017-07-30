package com.example.outlier.prictace_1.HomePage.Hotel;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.outlier.prictace_1.BaseApplication;
import com.example.outlier.prictace_1.HomePage.Guide.GuideActivity;
import com.example.outlier.prictace_1.HomePage.Hotel.model.EuclidListAdapter;
import com.example.outlier.prictace_1.HomePage.Hotel.model.StringEuclidActivity;
import com.example.outlier.prictace_1.MyTrip.library.EuclidActivity;
import com.example.outlier.prictace_1.R;
import com.example.outlier.prictace_1.SecondPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelSearchActivity extends StringEuclidActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
    }

    @Override
    protected BaseAdapter getAdapter() {
        Map<String, Object> profileMap;
        List<Map<String, Object>> profilesList = new ArrayList<>();

        Intent intent = getIntent();
        String []lon = intent.getStringArrayExtra("longitude");
        String []lat = intent.getStringArrayExtra("latitude");
        String []imgs = intent.getStringArrayExtra("img");
        String []hotels = intent.getStringArrayExtra("hotelName");
        String []intros = intent.getStringArrayExtra("intro");
        String []starRatedName = intent.getStringArrayExtra("starRatedName");
        String []oneword = intent.getStringArrayExtra("oneword");
        String []highestPrice = intent.getStringArrayExtra("highestPrice");
        String []lowestPrice = intent.getStringArrayExtra("lowestPrice");
        String []nearBy = intent.getStringArrayExtra("nearBy");
//            int[] avatarsHistory = {
//                    R.drawable.imagepic1,
//                    R.drawable.imagepic2,
//                    R.drawable.imagepic3,
//                    R.drawable.imagepic4,
//                    R.drawable.imagepic5,
//                    R.drawable.imagepic6,
//                    R.drawable.imagepic7
//            };
//            String[] names = getResources().getStringArray(R.array.array_names_history);
//            String[] longDescription=getResources().getStringArray(R.array.intro_history);

        BaseApplication.set("type","jiudian");
            for (int i = 0; i < imgs.length; i++) {
                profileMap = new HashMap<>();
                profileMap.put(EuclidListAdapter.KEY_AVATAR, imgs[i]);
                profileMap.put(EuclidListAdapter.KEY_NAME, hotels[i]);
                profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_SHORT, starRatedName[i]
                + "  价格: 从" + highestPrice[i]+ " 到 "+ lowestPrice[i] + "\n" + oneword[i]);
                profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_FULL, intros[i].replaceAll("<br>","\n").trim());
                profileMap.put("lon",lon[i]);
                profileMap.put("lat",lat[i]);
                profileMap.put("address",nearBy[i]);
                profilesList.add(profileMap);
            }

        return new EuclidListAdapter(this, R.layout.list_item, profilesList);
    }
}
