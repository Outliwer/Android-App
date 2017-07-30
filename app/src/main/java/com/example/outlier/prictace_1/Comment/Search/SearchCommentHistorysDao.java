package com.example.outlier.prictace_1.Comment.Search;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.outlier.prictace_1.HomePage.Search.Bean.SearchHistorysBean;
import java.util.ArrayList;

/**
 * Created by outlier on 2017/7/12.
 */


public class SearchCommentHistorysDao {

    private DBSearchCommentHelper helper;

    private SQLiteDatabase db;

    public SearchCommentHistorysDao(Context context) {
        helper = new DBSearchCommentHelper(context);
    }


    public void addOrUpdate(String word){
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "select _id from t_historycommentwords where historyword = ? ";
        Cursor cursor = db.rawQuery(sql, new String[]{word});
        if(cursor.getCount()>0){
            String sql_update = "update t_historycommentwords set updatetime = ? where historyword = ? ";
            db.execSQL(sql_update, new String[]{System.currentTimeMillis()+"",word});
        }else{
            String sql_add = "insert into t_historycommentwords(historyword,updatetime) values (?,?);";
            db.execSQL(sql_add, new String[]{word,System.currentTimeMillis()+""});
        }

        cursor.close();
        db.close();
    }


    public ArrayList<SearchHistorysBean> findAll(){
        ArrayList<SearchHistorysBean> data = new ArrayList<SearchHistorysBean>();;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("t_historycommentwords", null, null, null, null, null, "updatetime desc");
        while(cursor.moveToNext()){
            SearchHistorysBean searchDBData = new SearchHistorysBean();
            searchDBData._id =cursor.getInt(cursor.getColumnIndex("_id"));
            searchDBData.historyword = cursor.getString(cursor.getColumnIndex("historyword"));
            searchDBData.updatetime = cursor.getLong(cursor.getColumnIndex("updatetime"));
            data.add(searchDBData);
        }
        cursor.close();
        db.close();
        return data;
    }

    public void deleteAll(){
        SQLiteDatabase db = helper.getReadableDatabase();
        db.execSQL("delete from t_historycommentwords");
        db.close();
    }
}
