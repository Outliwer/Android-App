package com.example.outlier.prictace_1.AboutMe.AllClass;

/**
 * Created by Administrator on 2017/7/16 0016.
 */

public class hotelItem {
    private String hotelName;
    private String pictureId;
    public hotelItem(String hotelName,String pictureId){
        this.hotelName=hotelName;
        this.pictureId=pictureId;
    }

    public String getHotelName(){
        return hotelName;
    }
    public String getPictureId(){
        return pictureId;
    }
}
