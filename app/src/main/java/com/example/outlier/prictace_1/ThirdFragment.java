package com.example.outlier.prictace_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.outlier.prictace_1.Comment.Community.StaggeredGridActivity;
import com.example.outlier.prictace_1.Comment.RaiderA.RaiderActivity;
import com.example.outlier.prictace_1.Comment.SearchCommentActivity;

/**
 * Created by outlier on 2017/7/9.
 */

public class ThirdFragment extends Fragment {

    private RelativeLayout button1;
    private RelativeLayout button2;
    private RelativeLayout button3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fg_3,viewGroup,false);
        initView(view);
        return view;
    }

    private void initView(View view){
        button1 = (RelativeLayout) view.findViewById(R.id.goto_1);
        button2 = (RelativeLayout) view.findViewById(R.id.goto_2);
        button3 = (RelativeLayout) view.findViewById(R.id.goto_3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), StaggeredGridActivity.class));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchCommentActivity.class));
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RaiderActivity.class));
            }
        });
    }
}