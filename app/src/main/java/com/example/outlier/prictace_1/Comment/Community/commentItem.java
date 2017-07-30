package com.example.outlier.prictace_1.Comment.Community;

/**
 * Created by Administrator on 2017/7/19 0019.
 */

public class commentItem {
    private  String pictureId;
    private String commentContent;
    private String commentDate;

    public commentItem(String pictureId,String commentContent,String commentDate){
        this.pictureId=pictureId;
        this.commentContent=commentContent;
        this.commentDate=commentDate;
    }

    public String getPictureId(){
        return pictureId;
    }
    public String getCommentContent(){
        return commentContent;
    }
    public String getCommentDate(){return commentDate;}
}
