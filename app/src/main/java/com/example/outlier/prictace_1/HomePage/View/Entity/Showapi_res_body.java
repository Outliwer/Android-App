package com.example.outlier.prictace_1.HomePage.View.Entity;

/**
 * Created by outlier on 2017/7/16.
 */

import java.util.List;
public class Showapi_res_body {
    private String message;

    private int total;

    private int ret_code;

    private List<Results> results ;

    private int status;

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void setTotal(int total){
        this.total = total;
    }
    public int getTotal(){
        return this.total;
    }
    public void setRet_code(int ret_code){
        this.ret_code = ret_code;
    }
    public int getRet_code(){
        return this.ret_code;
    }
    public void setResults(List<Results> results){
        this.results = results;
    }
    public List<Results> getResults(){
        return this.results;
    }
    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }

}
