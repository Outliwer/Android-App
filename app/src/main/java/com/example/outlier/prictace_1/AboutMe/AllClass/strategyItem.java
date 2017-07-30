package com.example.outlier.prictace_1.AboutMe.AllClass;

import android.widget.ImageView;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class strategyItem {
    private String date;
    private String title;
    private String strategyContent;
    private int pictureId;

    public strategyItem(String title,String strategyContent,int pictureId){
        this.title=title;
        this.strategyContent=strategyContent;
        this.pictureId=pictureId;
    }


    public String getTitle(){
        return title;
    }
    public String getStrategyContent(){
        return strategyContent;
    }
    public int getPictureSight(){
        return pictureId;
    }


}
