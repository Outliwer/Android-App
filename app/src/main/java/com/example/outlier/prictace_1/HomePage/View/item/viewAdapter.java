package com.example.outlier.prictace_1.HomePage.View.item;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.outlier.prictace_1.R;

import java.util.List;

import static com.baidu.mapapi.BMapManager.getContext;

/**
 * Created by outlier on 2017/7/17.
 */

public class viewAdapter extends ArrayAdapter<viewitem> {

    private int resourceId;
    public viewAdapter(@NonNull Context context, @LayoutRes int resource, List<viewitem> objects ) {
        super(context, resource,objects);
        resourceId=resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        viewitem comment=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        TextView labelCommentdate=(TextView)view.findViewById(R.id.textView14);
        labelCommentdate.setText(comment.getPrice());
        TextView labelCommentposition=(TextView)view.findViewById(R.id.textView15);
        labelCommentposition.setText(comment.getViewaddress());
        TextView labelCommentcontent=(TextView)view.findViewById(R.id.textView16);
        labelCommentcontent.setText(comment.getViewname());
        TextView labelCommentposition_1=(TextView)view.findViewById(R.id.textView17);
        labelCommentposition_1.setText(comment.getViewurl());

        return view;
    }
}
