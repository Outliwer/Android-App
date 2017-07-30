package com.example.outlier.prictace_1.AboutMe.chat;

/**
 * Created by outlier on 2017/7/22.
 */

public class Friend {
    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPortraitUri() {
        return PortraitUri;
    }

    public void setPortraitUri(String portraitUri) {
        PortraitUri = portraitUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String UserId;
    String PortraitUri;
    String name;
    public Friend(String UserId,String name,String PortraitUri){
        this.name = name;
        this.UserId = UserId;
        this.PortraitUri = PortraitUri;
    }
}
