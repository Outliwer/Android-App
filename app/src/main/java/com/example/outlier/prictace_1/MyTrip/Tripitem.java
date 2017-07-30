package com.example.outlier.prictace_1.MyTrip;

/**
 * Created by outlier on 2017/7/16.
 */

public class Tripitem {
    private String date;
    private String position;

    public int getDays() {
        return days;
    }

    private int days;
    private String url_1;
    private String url_2;

    public Tripitem(String date,String position,int days){
        this.date=date;
        this.position=position;
        this.days =days;
    }

    public String getDate(){
        return date;
    }

    public String getPosition(){
        return position;
    }

}

