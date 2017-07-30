package com.example.outlier.prictace_1.AboutMe.AllClass;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.outlier.prictace_1.R;

import java.util.List;

/**
 * Created by Administrator on 2017/7/16 0016.
 */

public class strategyAdapter extends ArrayAdapter<strategyItem> {
    private int resourceId;
    public strategyAdapter(@NonNull Context context, @LayoutRes int resource,List<strategyItem> objects ) {
        super(context, resource,objects);
        resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        strategyItem strategy=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView labelStrategyTitle=(TextView)view.findViewById(R.id.label_strategy_title);
        labelStrategyTitle.setText(strategy.getTitle());
        ImageView pictureSight=(ImageView)view.findViewById(R.id.picture_sight);
        pictureSight.setImageResource(strategy.getPictureSight());
        TextView strategyContent=(TextView)view.findViewById(R.id.strategy_content);
        strategyContent.setText(strategy.getStrategyContent());
        return view;
    }
}
