package com.example.outlier.prictace_1.MyTrip;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.outlier.prictace_1.R;

import java.util.List;

/**
 * Created by outlier on 2017/7/16.
 */

public class TripDetailAdapter extends ArrayAdapter<TripDetail> {
    private int resourceId;
    public TripDetailAdapter(@NonNull Context context, @LayoutRes int resource, List<TripDetail> objects ) {
        super(context, resource,objects);
        resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        TripDetail comment=getItem(position);


        final View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView labelCommentdate=(TextView)view.findViewById(R.id.label_commentdate);
        labelCommentdate.setText(comment.getDate());
        ListView labelCommentposition=(ListView) view.findViewById(R.id.list_comment);

        return view;
    }
}
