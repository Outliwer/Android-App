package com.example.outlier.prictace_1.HomePage.View.Entity;

/**
 * Created by outlier on 2017/7/16.
 */

public class Detail_info {
    private String image_num;

    private String detail_url;

    private String price;

    private String tag;

    private Navi_location navi_location;

    private String comment_num;

    private String type;

    private String overall_rating;

    public void setImage_num(String image_num){
        this.image_num = image_num;
    }
    public String getImage_num(){
        return this.image_num;
    }
    public void setDetail_url(String detail_url){
        this.detail_url = detail_url;
    }
    public String getDetail_url(){
        return this.detail_url;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public String getPrice(){
        return this.price;
    }
    public void setTag(String tag){
        this.tag = tag;
    }
    public String getTag(){
        return this.tag;
    }
    public void setNavi_location(Navi_location navi_location){
        this.navi_location = navi_location;
    }
    public Navi_location getNavi_location(){
        return this.navi_location;
    }
    public void setComment_num(String comment_num){
        this.comment_num = comment_num;
    }
    public String getComment_num(){
        return this.comment_num;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setOverall_rating(String overall_rating){
        this.overall_rating = overall_rating;
    }
    public String getOverall_rating(){
        return this.overall_rating;
    }

}
