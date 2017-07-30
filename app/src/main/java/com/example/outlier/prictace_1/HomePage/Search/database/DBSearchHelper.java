package com.example.outlier.prictace_1.HomePage.Search.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by outlier on 2017/7/12.
 */

public class DBSearchHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "searchhistory.db";
    private static final int DATABASE_VERSION = 1;

    public DBSearchHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS t_historywords" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, historyword VARCHAR, updatetime LONG)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

}
