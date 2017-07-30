package com.example.outlier.prictace_1.HomePage.Tiket.datetimepicker;

/**
 * Created by outlier on 2017/7/15.
 */

public class TiketBoDyDataEntity {
    private String intervalMiles;
    private String arrStationNo;

    public String getIntervalMiles() {
        return intervalMiles;
    }

    public void setIntervalMiles(String intervalMiles) {
        this.intervalMiles = intervalMiles;
    }

    public String getArrStationNo() {
        return arrStationNo;
    }

    public void setArrStationNo(String arrStationNo) {
        this.arrStationNo = arrStationNo;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public int getTimeDim() {
        return timeDim;
    }

    public void setTimeDim(int timeDim) {
        this.timeDim = timeDim;
    }

    public TiketBodySaleStatusEntity getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(TiketBodySaleStatusEntity saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getControlMsg() {
        return controlMsg;
    }

    public void setControlMsg(String controlMsg) {
        this.controlMsg = controlMsg;
    }

    public int getSubscribePeriod() {
        return subscribePeriod;
    }

    public void setSubscribePeriod(int subscribePeriod) {
        this.subscribePeriod = subscribePeriod;
    }

    public int getPresaleDay() {
        return presaleDay;
    }

    public void setPresaleDay(int presaleDay) {
        this.presaleDay = presaleDay;
    }

    public String getLishiValue() {
        return lishiValue;
    }

    public void setLishiValue(String lishiValue) {
        this.lishiValue = lishiValue;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public int getYpDim() {
        return ypDim;
    }

    public void setYpDim(int ypDim) {
        this.ypDim = ypDim;
    }

    public String getDptStationName() {
        return dptStationName;
    }

    public void setDptStationName(String dptStationName) {
        this.dptStationName = dptStationName;
    }

    public String getDptStationNo() {
        return dptStationNo;
    }

    public void setDptStationNo(String dptStationNo) {
        this.dptStationNo = dptStationNo;
    }

    public String getEndStationName() {
        return endStationName;
    }

    public void setEndStationName(String endStationName) {
        this.endStationName = endStationName;
    }

    public String getDptTime() {
        return dptTime;
    }

    public void setDptTime(String dptTime) {
        this.dptTime = dptTime;
    }

    public String getArrStationName() {
        return arrStationName;
    }

    public void setArrStationName(String arrStationName) {
        this.arrStationName = arrStationName;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public int getRobPeriod() {
        return robPeriod;
    }

    public void setRobPeriod(int robPeriod) {
        this.robPeriod = robPeriod;
    }

    public TiketBodySeatsEntity getSeats() {
        return seats;
    }

    public void setSeats(TiketBodySeatsEntity seats) {
        this.seats = seats;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getStartStationName() {
        return startStationName;
    }

    public void setStartStationName(String startStationName) {
        this.startStationName = startStationName;
    }

    public int getNoteDim() {
        return noteDim;
    }

    public void setNoteDim(int noteDim) {
        this.noteDim = noteDim;
    }

    public String getDayDifference() {
        return dayDifference;
    }

    public void setDayDifference(String dayDifference) {
        this.dayDifference = dayDifference;
    }

    public String getControlFlag() {
        return controlFlag;
    }

    public void setControlFlag(String controlFlag) {
        this.controlFlag = controlFlag;
    }

    private String interval;
    private int timeDim;
    TiketBodySaleStatusEntity saleStatus;
    private String controlMsg;
    private int subscribePeriod;
    private int presaleDay;
    private String lishiValue;
    private String trainNo;
    private int ypDim;
    private String dptStationName;
    private String dptStationNo;
    private String endStationName;
    private String dptTime;
    private String arrStationName;
    private String arrTime;
    private int robPeriod;
    TiketBodySeatsEntity seats;
    private String trainType;
    private String startStationName;
    private int noteDim;
    private String dayDifference;
    private String controlFlag;
}
