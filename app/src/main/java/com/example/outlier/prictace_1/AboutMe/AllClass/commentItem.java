package com.example.outlier.prictace_1.AboutMe.AllClass;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class commentItem {
    private String date;
    private String position;
    private String commentContent;

    public commentItem(String date,String position,String comment){
        this.date=date;
        this.position=position;
        this.commentContent=comment;
    }

    public String getDate(){
        return date;
    }

    public String getPosition(){
        return position;
    }

    public String getComment(){
        return commentContent;
    }
}
