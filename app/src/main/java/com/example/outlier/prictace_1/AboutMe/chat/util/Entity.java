package com.example.outlier.prictace_1.AboutMe.chat.util;

/**
 * Created by outlier on 2017/7/22.
 */

public class Entity {
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String userId;
    private String token;
}
