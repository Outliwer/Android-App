package com.example.outlier.prictace_1;

import android.os.Bundle;
import android.widget.BaseAdapter;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.outlier.prictace_1.MyTrip.library.EuclidActivity;
import com.example.outlier.prictace_1.MyTrip.library.EuclidListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by outlier on 2017/7/9.
 */

public class SecondFragment extends EuclidActivity {
    /*private List<Tripitem> commentList=new ArrayList<>();
    private ListView listView;
    private ImageView icon_back;
    private int m = 0;

    //数据
    String startday = "";
    String finalday = "";
    String startcity = "";
    String finalcity = "";
    TripAdapter adapter;*/
    private SecondPage exm = new SecondPage();
    private AHBottomNavigation bottomNavigation;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private static int chooseNum = 0;
    private static ArrayList<Integer> avatarsNow = new ArrayList<Integer>();
    private static ArrayList<String> names = new ArrayList<>();
    private static ArrayList<String> longDescription = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected BaseAdapter getAdapter() {
        Map<String, Object> profileMap;
        List<Map<String, Object>> profilesList = new ArrayList<>();

        if (chooseNum == 1) {
            int[] avatarsHistory = {
                    R.drawable.imagepic1,
                    R.drawable.imagepic2,
                    R.drawable.imagepic3,
                    R.drawable.imagepic4,
                    R.drawable.imagepic5,
                    R.drawable.imagepic6,
                    R.drawable.imagepic7
            };
            String[] names = getResources().getStringArray(R.array.array_names_history);
            String[] longDescription = getResources().getStringArray(R.array.intro_history);

            for (int i = 0; i < avatarsHistory.length; i++) {
                profileMap = new HashMap<>();
                profileMap.put(EuclidListAdapter.KEY_AVATAR, avatarsHistory[i]);
                profileMap.put(EuclidListAdapter.KEY_NAME, names[i]);
                profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_SHORT, getString(R.string.lorem_ipsum_short));
                profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_FULL, longDescription[i]);
                profilesList.add(profileMap);
            }
        } else if (chooseNum == 2) {
            for (int i = 0; i < avatarsNow.size(); i++) {
                profileMap = new HashMap<>();
                profileMap.put(EuclidListAdapter.KEY_AVATAR, avatarsNow.get(i));
                profileMap.put(EuclidListAdapter.KEY_NAME, names.get(i));
                profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_SHORT, getString(R.string.lorem_ipsum_short));
                profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_FULL, longDescription.get(i));
                profilesList.add(profileMap);
            }
        } else {
        }
        return new EuclidListAdapter(this, R.layout.list_item, profilesList);
    }

    public static void addNewTrip(int picId, String name, String content) {
        avatarsNow.add(picId);
        names.add(name);
        longDescription.add(content);
    }

    public static void setChooseNum(int newNum) {
        chooseNum = newNum;
    }
}