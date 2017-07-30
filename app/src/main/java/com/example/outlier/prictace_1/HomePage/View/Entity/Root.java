package com.example.outlier.prictace_1.HomePage.View.Entity;

/**
 * Created by outlier on 2017/7/16.
 */

public class Root {
    private int showapi_res_code;

    private String showapi_res_error;

    private Showapi_res_body showapi_res_body;

    public void setShowapi_res_code(int showapi_res_code){
        this.showapi_res_code = showapi_res_code;
    }
    public int getShowapi_res_code(){
        return this.showapi_res_code;
    }
    public void setShowapi_res_error(String showapi_res_error){
        this.showapi_res_error = showapi_res_error;
    }
    public String getShowapi_res_error(){
        return this.showapi_res_error;
    }
    public void setShowapi_res_body(Showapi_res_body showapi_res_body){
        this.showapi_res_body = showapi_res_body;
    }
    public Showapi_res_body getShowapi_res_body(){
        return this.showapi_res_body;
    }

}