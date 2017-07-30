package com.example.outlier.prictace_1.HomePage.Search;

import java.util.List;

/**
 * Created by outlier on 2017/7/14.
 */

public class SearchListEntity {


    private String summary;
    private String proId;
    private String cityId;
    location location;
    private String areaId;
    private String price;
    List<picList> picList;
    List<SearchPriceListEntity> priceList;
    private String content;
    private String id;
    private String proName;
    private String address;
    private String name;
    private String attention;
    private String coupon;
    private String opentime;

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public com.example.outlier.prictace_1.HomePage.Search.location getLocation() {
        return location;
    }

    public void setLocation(com.example.outlier.prictace_1.HomePage.Search.location location) {
        this.location = location;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<com.example.outlier.prictace_1.HomePage.Search.picList> getPicList() {
        return picList;
    }

    public void setPicList(List<com.example.outlier.prictace_1.HomePage.Search.picList> picList) {
        this.picList = picList;
    }





    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<SearchPriceListEntity> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<SearchPriceListEntity> priceList) {
        this.priceList = priceList;
    }


    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

}
