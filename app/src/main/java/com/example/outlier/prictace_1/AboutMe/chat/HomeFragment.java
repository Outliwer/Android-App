package com.example.outlier.prictace_1.AboutMe.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by hecun on 2016/7/13.
 */
public class HomeFragment extends Fragment{

    private static HomeFragment mInstance;

    public static HomeFragment getInstance(){
        if(mInstance == null){
            mInstance = new HomeFragment();
        }
        return mInstance;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("第二个页面");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        return textView;
    }
}
