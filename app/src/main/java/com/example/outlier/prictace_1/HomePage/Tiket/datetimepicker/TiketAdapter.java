package com.example.outlier.prictace_1.HomePage.Tiket.datetimepicker;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.outlier.prictace_1.AboutMe.AllClass.commentItem;
import com.example.outlier.prictace_1.HomePage.Tiket.TiketSearchActivity;
import com.example.outlier.prictace_1.HomePage.Tiket.tiketitem;
import com.example.outlier.prictace_1.R;

import java.util.List;

/**
 * Created by outlier on 2017/7/17.
 */

public class TiketAdapter extends ArrayAdapter<tiketitem> {

    private int resourceId;
    public TiketAdapter(@NonNull Context context, @LayoutRes int resource, List<tiketitem> objects ) {
        super(context, resource,objects);
        resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        tiketitem comment=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        TextView labelCommentdate=(TextView)view.findViewById(R.id.textView14);
        labelCommentdate.setText(comment.getTrainNo());
        TextView labelCommentposition=(TextView)view.findViewById(R.id.textView15);
        labelCommentposition.setText(comment.getUse_time());
        TextView labelCommentcontent=(TextView)view.findViewById(R.id.textView16);
        labelCommentcontent.setText(comment.getStartTime());
        TextView labelCommentposition_1=(TextView)view.findViewById(R.id.textView17);
        labelCommentposition_1.setText(comment.getStartStationName());
        TextView labelCommentcontent_1=(TextView)view.findViewById(R.id.textView18);
        labelCommentcontent_1.setText(comment.getEndStationName());
        return view;
    }
}
