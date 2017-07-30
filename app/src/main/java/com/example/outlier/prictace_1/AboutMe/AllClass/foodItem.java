package com.example.outlier.prictace_1.AboutMe.AllClass;

/**
 * Created by Administrator on 2017/7/16 0016.
 */

public class foodItem {
    private int pictureId;
    private String foodContent;

    public foodItem(int pictureId,String foodContent){
        this.pictureId=pictureId;
        this.foodContent=foodContent;
    }

    public int getPictureId(){
        return pictureId;
    }
    public String getFoodContent(){
        return foodContent;
    }
}
