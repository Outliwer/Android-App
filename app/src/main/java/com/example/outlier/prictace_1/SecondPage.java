
package com.example.outlier.prictace_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.outlier.prictace_1.MyTrip.AddTripActivity;

import static com.example.outlier.prictace_1.SecondFragment.addNewTrip;
import static com.example.outlier.prictace_1.SecondFragment.setChooseNum;

public class SecondPage extends Fragment {

    private RelativeLayout goto_1;
    private RelativeLayout goto_2;
    private RelativeLayout goto_3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fg_4,viewGroup,false);
        initView(view);
        addNewTrip(R.drawable.imagepic8,"美国行程","--2017/9/26\n" +
                "               上海 Shanghai 到 洛杉矶 Los Angeles\n" +
                "\n" +
                "            --2017/9/27\n" +
                "                拉斯维加斯\n" +
                "\n" +
                "            --2016/9/28\n" +
                "                内达华州");
        return view;
    }

    private void initView(View view){
        goto_1 = (RelativeLayout) view.findViewById(R.id.goto_1);
        goto_2 = (RelativeLayout) view.findViewById(R.id.goto_2);
        goto_3 = (RelativeLayout) view.findViewById(R.id.goto_3);


        goto_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(),AddTripActivity.class),0);
            }
        });

        goto_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChooseNum(2);
                startActivity(new Intent(getActivity(),SecondFragment.class));
            }
        });

        goto_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChooseNum(1);
                startActivity(new Intent(getActivity(),SecondFragment.class));
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==2){
            Bundle b=data.getExtras();
            String name=b.getString("tripname");
            String startday = b.getString("startday");
            String finalday=b.getString("finalday");
            String startcity=b.getString("startcity");
            String finalcity=b.getString("finalcity");
            String intro=startday+"到"+finalday+"\n\t"+startcity+"到"+finalcity;
            addNewTrip(R.drawable.imagepic9,name,intro);
        }
        else{}
    }


}
