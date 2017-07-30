package com.example.outlier.prictace_1.HomePage.View.item;

/**
 * Created by outlier on 2017/7/17.
 */

public class viewitem {
    private String price;
    private String viewurl;
    private String viewname;

    public String getPrice() {
        return price;
    }

    public String getViewurl() {
        return viewurl;
    }

    public String getViewname() {
        return viewname;
    }

    public String getViewaddress() {
        return viewaddress;
    }

    private String viewaddress;

    public viewitem(String price, String viewurl,String viewname,String viewaddress){
        this.price = price;
        this.viewurl = viewurl;
        this.viewname = viewname;
        this.viewaddress = viewaddress;
    }
}
