package com.example.outlier.prictace_1.MyTrip;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.outlier.prictace_1.AboutMe.AllClass.commentItem;
import com.example.outlier.prictace_1.R;

import java.util.List;

/**
 * Created by outlier on 2017/7/16.
 */

public class TripAdapter extends ArrayAdapter<Tripitem> {

    private int resourceId;
    public TripAdapter(@NonNull Context context, @LayoutRes int resource, List<Tripitem> objects ) {
        super(context, resource,objects);
        resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Tripitem comment=getItem(position);
        final View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView labelCommentdate=(TextView)view.findViewById(R.id.label_commentdate);
        labelCommentdate.setText(comment.getDate());
        TextView labelCommentposition=(TextView)view.findViewById(R.id.label_commentposition);
        labelCommentposition.setText(comment.getPosition());


        return view;
    }
}
