package com.example.outlier.prictace_1.HomePage.Search;

import java.util.List;

/**
 * Created by outlier on 2017/7/14.
 */

public class SearchBodyEntity {
    private String ret_code;

    public String getRet_code() {
        return ret_code;
    }

    public void setRet_code(String ret_code) {
        this.ret_code = ret_code;
    }


    public SearchBeanEntity getPagebean() {
        return pagebean;
    }

    public void setPagebean(SearchBeanEntity pagebean) {
        this.pagebean = pagebean;
    }

    SearchBeanEntity pagebean;
}
