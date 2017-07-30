package com.example.outlier.prictace_1.HomePage.Search.Bean;

/**
 * Created by outlier on 2017/7/12.
 */

public class SearchHistorysBean {
    public int _id;
    public String historyword;
    public long updatetime;

    public SearchHistorysBean(){

    }

    public SearchHistorysBean(String historyword, long updatetime) {
        this.historyword = historyword;
        this.updatetime = updatetime;
    }

    public SearchHistorysBean(int _id) {
        this._id = _id;
    }


    public SearchHistorysBean(String historyword) {
        this.historyword = historyword;
    }


    public SearchHistorysBean(long updatetime) {
        this.updatetime = updatetime;
    }

    public SearchHistorysBean(int _id, String historyword, long updatetime) {
        this._id = _id;
        this.historyword = historyword;
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {

        return historyword;
    }
}
