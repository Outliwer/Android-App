package com.example.outlier.prictace_1.AboutMe.AllClass;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/7/17 0017.
 */

public class photoItem {
    private  Bitmap photoId;
    private  String photoName;

    public photoItem(Bitmap photoId,String photoName){
        this.photoId=photoId;
        this.photoName=photoName;
    }
    public Bitmap getPhotoId(){
        return photoId;
    }
    public String getPhotoName(){
        return photoName;
    }


}
