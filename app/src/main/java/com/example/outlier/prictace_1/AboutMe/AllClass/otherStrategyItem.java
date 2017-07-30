package com.example.outlier.prictace_1.AboutMe.AllClass;

import android.widget.ImageView;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class otherStrategyItem {
    private String title;
    private String strategyContent;
    private int pictureId;

    public otherStrategyItem(String title,int pictureId,String strategyContent){
        this.title=title;
        this.pictureId=pictureId;
        this.strategyContent=strategyContent;
    }


    public String getTitle(){
        return title;
    }
    public String getStrategyContent(){
        return strategyContent;
    }
    public int getPictureStrategy(){
        return pictureId;
    }


}
