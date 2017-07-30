package com.example.outlier.prictace_1.MyTrip;

import java.util.List;

/**
 * Created by outlier on 2017/7/16.
 */

public class TripDetail {
    private String date;
    private List<Detail> detail;

    public List<Detail> getDetail() {
        return detail;
    }

    public int getDays() {
        return days;
    }

    private int days;
    private String url_1;
    private String url_2;

    public TripDetail(String date,List<Detail> detail){
        this.date=date;
        this.detail=detail;
    }

    public String getDate(){
        return date;
    }


}
