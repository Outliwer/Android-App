package com.example.outlier.prictace_1.Comment.Community;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.outlier.prictace_1.AboutMe.myFriend.Toolkit.JSONHelper;
import com.example.outlier.prictace_1.BaseApplication;
import com.example.outlier.prictace_1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class SampleData {
    String text = "";
    private ArrayList<commentItem> mdata = new ArrayList<commentItem>();
    private int nowItemNum=0;
    private int getMDataSize(){
        return mdata.size();
    }

    public SampleData() {
        text = BaseApplication.get("text", "");
        if (text == "") {
        } else {
            JSONArray jsonArray = JSONHelper.getJSONArray(text);
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    String title = JSONHelper.getObjString((JSONObject) jsonArray.get(i), "title");
                    String neirong = JSONHelper.getObjString((JSONObject) jsonArray.get(i), "neirong");
                    Log.e("title", "" + title);
                    Log.e("neirong", "" + neirong);
                    Log.e("picture", "" + BaseApplication.get("hostaddress", "")
                            + "comment/" + title + ".jpg");
                    if (neirong != null) {
                        commentItem x = new commentItem(BaseApplication.get("hostaddress", "")
                                + "comment/" + title + ".jpg", neirong, title);
                        mdata.add(x);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.e(" response.body().string()", "" + text);
        }
    }


    public  ArrayList<commentItem> generateSampleData() {
        ArrayList<commentItem> data=new ArrayList<>();

        for(int i=nowItemNum;i<nowItemNum+2;i++){
            if(i<getMDataSize()){
                commentItem em=mdata.get(i);
                data.add(em);
            }
            else{
                //说明已经全部读完
            }
        }
        nowItemNum=nowItemNum+2;
        return data;
    }

}
