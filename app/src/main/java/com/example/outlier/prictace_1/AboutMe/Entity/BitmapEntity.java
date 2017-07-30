package com.example.outlier.prictace_1.AboutMe.Entity;

import android.graphics.Bitmap;

/**
 * Created by outlier on 2017/7/20.
 */

public class BitmapEntity {
    public BitmapEntity(int i, Bitmap bitmap) {
        this.bitmap = bitmap;
        this.i = i;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    Bitmap bitmap;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    int i ;

}
