package com.example.outlier.prictace_1.HomePage.Hotel.Entity;

import java.util.List;

/**
 * Created by outlier on 2017/7/17.
 */

public class Showapi_res_body {
    private int ret_code;

    private String totalCount;

    private String page;

    private String pageSize;

    private List<List_1> list ;

    private String totalPage;

    public void setRet_code(int ret_code){
        this.ret_code = ret_code;
    }
    public int getRet_code(){
        return this.ret_code;
    }
    public void setTotalCount(String totalCount){
        this.totalCount = totalCount;
    }
    public String getTotalCount(){
        return this.totalCount;
    }
    public void setPage(String page){
        this.page = page;
    }
    public String getPage(){
        return this.page;
    }
    public void setPageSize(String pageSize){
        this.pageSize = pageSize;
    }
    public String getPageSize(){
        return this.pageSize;
    }
    public void setList(List<List_1> list){
        this.list = list;
    }
    public List<List_1> getList(){
        return this.list;
    }
    public void setTotalPage(String totalPage){
        this.totalPage = totalPage;
    }
    public String getTotalPage(){
        return this.totalPage;
    }

}
