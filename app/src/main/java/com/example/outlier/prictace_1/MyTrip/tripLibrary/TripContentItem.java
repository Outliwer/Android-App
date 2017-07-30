package com.example.outlier.prictace_1.MyTrip.tripLibrary;

/**
 * Created by Administrator on 2017/7/24 0024.
 */

public class TripContentItem {
    private int pictureId;
    private String tripContent;

    public TripContentItem(int pictureId,String tripContent){
        this.pictureId=pictureId;
        this.tripContent=tripContent;
    }

    public int getPictureId(){
        return pictureId;
    }
    public String getTripContent(){
        return tripContent;
    }
}
