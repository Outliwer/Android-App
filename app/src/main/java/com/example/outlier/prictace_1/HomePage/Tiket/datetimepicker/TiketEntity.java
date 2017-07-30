package com.example.outlier.prictace_1.HomePage.Tiket.datetimepicker;

/**
 * Created by outlier on 2017/7/15.
 */

public class TiketEntity {
    public String getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(String showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public TiketBodyEntity getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(TiketBodyEntity showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    private String showapi_res_code;
    private String showapi_res_error;
    TiketBodyEntity showapi_res_body;
}
