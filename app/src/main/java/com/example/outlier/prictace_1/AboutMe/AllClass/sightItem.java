package com.example.outlier.prictace_1.AboutMe.AllClass;

/**
 * Created by Administrator on 2017/7/16 0016.
 */

public class sightItem {
    private String pictureId;
    private String sightContent;

    public sightItem(String pictureId,String sightContent){
        this.pictureId=pictureId;
        this.sightContent=sightContent;
    }

    public String getPictureId(){
        return pictureId;
    }
    public String getSightContent(){
        return sightContent;
    }

}
