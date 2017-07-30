package com.example.outlier.prictace_1.HomePage.Tiket.datetimepicker;

/**
 * Created by outlier on 2017/7/15.
 */

public class TiketBodySeatsEntity {
    FirstSeat 一等座;

    public FirstSeat get一等座() {
        return 一等座;
    }

    public void set一等座(FirstSeat 一等座) {
        this.一等座 = 一等座;
    }

    public SecondSeat get二等座() {
        return 二等座;
    }

    public void set二等座(SecondSeat 二等座) {
        this.二等座 = 二等座;
    }

    public BusinessSeat get商务座() {
        return 商务座;
    }

    public void set商务座(BusinessSeat 商务座) {
        this.商务座 = 商务座;
    }

    SecondSeat 二等座;
    BusinessSeat 商务座;
}
