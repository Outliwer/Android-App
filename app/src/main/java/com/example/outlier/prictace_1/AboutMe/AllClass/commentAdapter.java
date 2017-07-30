package com.example.outlier.prictace_1.AboutMe.AllClass;

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
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class commentAdapter extends ArrayAdapter<commentItem> {

    private int resourceId;
    public commentAdapter(@NonNull Context context, @LayoutRes int resource,List<commentItem> objects ) {
        super(context, resource,objects);
        resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        commentItem comment=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView labelCommentdate=(TextView)view.findViewById(R.id.label_commentdate);
        labelCommentdate.setText(comment.getDate());
        TextView labelCommentposition=(TextView)view.findViewById(R.id.label_commentposition);
        labelCommentposition.setText(comment.getPosition());
        TextView labelCommentcontent=(TextView)view.findViewById(R.id.label_commentcontent);
        labelCommentcontent.setText(comment.getComment());
        return view;
    }


}
