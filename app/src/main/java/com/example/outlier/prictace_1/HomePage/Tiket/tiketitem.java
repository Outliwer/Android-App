package com.example.outlier.prictace_1.HomePage.Tiket;

/**
 * Created by outlier on 2017/7/17.
 */

public class tiketitem {
    private String trainNo;

    public String getTrainNo() {
        return trainNo;
    }

    public String getUse_time() {
        return use_time;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getStartStationName() {
        return startStationName;
    }

    public String getEndStationName() {
        return endStationName;
    }

    private String use_time;
    private String startTime;
    private String startStationName;
    private String endStationName;

    public tiketitem(String trainNo,String use_time,String startTime,String startStationName,String endStationName){
        this.trainNo=trainNo;
        this.use_time=use_time;
        this.startTime=startTime;
        this.startStationName = startStationName;
        this.endStationName = endStationName;
    }

}
