package com.example.outlier.prictace_1.HomePage.View.Entity;

/**
 * Created by outlier on 2017/7/16.
 */

public class Results {
    private String street_id;

    private String uid;

    private int detail;

    private Location location;

    private String address;

    private String name;

    private String telephone;

    private Detail_info detail_info;

    public void setStreet_id(String street_id){
        this.street_id = street_id;
    }
    public String getStreet_id(){
        return this.street_id;
    }
    public void setUid(String uid){
        this.uid = uid;
    }
    public String getUid(){
        return this.uid;
    }
    public void setDetail(int detail){
        this.detail = detail;
    }
    public int getDetail(){
        return this.detail;
    }
    public void setLocation(Location location){
        this.location = location;
    }
    public Location getLocation(){
        return this.location;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return this.address;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }
    public String getTelephone(){
        return this.telephone;
    }
    public void setDetail_info(Detail_info detail_info){
        this.detail_info = detail_info;
    }
    public Detail_info getDetail_info(){
        return this.detail_info;
    }

}
