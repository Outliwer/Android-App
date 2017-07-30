package com.example.outlier.prictace_1.AboutMe.AllClass;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.outlier.prictace_1.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class photoAdapter extends ArrayAdapter<photoItem> {

    private int resourceId;
    public photoAdapter(@NonNull Context context, @LayoutRes int resource,List<photoItem> objects ) {
        super(context, resource,objects);
        resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        photoItem photo=getItem(position);
        Log.e("photo--",""+photo);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView picturePhoto=(ImageView)view.findViewById(R.id.picture_photo);
        picturePhoto.setImageBitmap(photo.getPhotoId());
        Log.e("photo.getPhotoId()--",""+photo.getPhotoId());

        TextView photoContent=(TextView)view.findViewById(R.id.photo_content);
        photoContent.setText(photo.getPhotoName());
        return view;
    }






}
