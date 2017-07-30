package com.example.outlier.prictace_1;

/**
 * Created by outlier on 2017/7/14.
 */

public interface HttpCallbackListener {
    void onFinish(String response);

    void onError (Exception e);
}
