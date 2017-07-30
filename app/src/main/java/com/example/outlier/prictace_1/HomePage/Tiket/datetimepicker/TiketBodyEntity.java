package com.example.outlier.prictace_1.HomePage.Tiket.datetimepicker;

import java.util.List;

/**
 * Created by outlier on 2017/7/15.
 */

public class TiketBodyEntity {
    private String arrCity;
    private String dptCity;
    private String ret_code;
    private String description;
    List<TiketBoDyDataEntity> data;
    private String error_code;
    private String trainSearchType;
    private String subscribePeriod;
    private String dptDate;

    public List<TiketBoDyDataEntity> getData() {
        return data;
    }

    public void setData(List<TiketBoDyDataEntity> data) {
        this.data = data;
    }


    public String getArrCity() {
        return arrCity;
    }

    public void setArrCity(String arrCity) {
        this.arrCity = arrCity;
    }

    public String getDptCity() {
        return dptCity;
    }

    public void setDptCity(String dptCity) {
        this.dptCity = dptCity;
    }

    public String getRet_code() {
        return ret_code;
    }

    public void setRet_code(String ret_code) {
        this.ret_code = ret_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getTrainSearchType() {
        return trainSearchType;
    }

    public void setTrainSearchType(String trainSearchType) {
        this.trainSearchType = trainSearchType;
    }

    public String getSubscribePeriod() {
        return subscribePeriod;
    }

    public void setSubscribePeriod(String subscribePeriod) {
        this.subscribePeriod = subscribePeriod;
    }

    public String getDptDate() {
        return dptDate;
    }

    public void setDptDate(String dptDate) {
        this.dptDate = dptDate;
    }

    public String getRobPeriod() {
        return robPeriod;
    }

    public void setRobPeriod(String robPeriod) {
        this.robPeriod = robPeriod;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    public String getRecommendTrainJoint() {
        return recommendTrainJoint;
    }

    public void setRecommendTrainJoint(String recommendTrainJoint) {
        this.recommendTrainJoint = recommendTrainJoint;
    }

    private String robPeriod;
    private String error_description;
    private String recommendTrainJoint;
}
