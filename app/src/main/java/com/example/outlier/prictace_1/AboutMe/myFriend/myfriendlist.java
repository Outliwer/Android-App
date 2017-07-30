package com.example.outlier.prictace_1.AboutMe.myFriend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.outlier.prictace_1.R;

public class myfriendlist extends Fragment {
    private TextView labelTitle;
    private ImageView iconBack;
    private static ExpandableListView expandableListView;
    private static ExpandableListAdapter adapter;
    private static myfriendlist mInstance;


    public static myfriendlist getInstance(){
        if(mInstance == null){
            mInstance = new myfriendlist();
        }
        return mInstance;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {        super.onCreate(savedInstanceState);
        View view = View.inflate(getActivity(),R.layout.activity_myfriendlist, null);
        expandableListView = (ExpandableListView) view.findViewById(R.id.simple_expandable_listview);
        // Setting group indicator null for custom indicator
        expandableListView.setGroupIndicator(null);

        setItems();
        //setListener();
        initeView(view);
        return view;
    }
    private void initeView(View view){
        //初始化标题栏
        labelTitle=(TextView)view.findViewById(R.id.label_title);
        labelTitle.setText("我的驴友");
        //定义所有点击事件
        iconBack=(ImageView)view.findViewById(R.id.icon_back);
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(),AddFriendActivity.class),1);
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Setting headers and childs to expandable listview
    void setItems(){

        // Array list for header
        final ArrayList<String> header = new ArrayList<String>();

        // Array list for child items
        List<String> child1 = new ArrayList<String>();
        List<String> child2 = new ArrayList<String>();

        // Hash map for both header and child
        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();

        // Adding headers to list
        header.add("我的驴友");
        header.add("同行者");

//        // Adding child data
//        for (int i = 1; i < 5; i++) {
//            child1.add("Group 1  " + " : Child" + i);
//        }
//        // Adding child data
//        for (int i = 1; i < 5; i++) {
//            child2.add("Group 2  " + " : Child" + i);
//        }
//        // Adding child data
//        for (int i = 1; i < 6; i++) {
//            child3.add("Group 3  " + " : Child" + i);
//        }
//        // Adding child data
//        for (int i = 1; i < 7; i++) {
//            child4.add("Group 4  " + " : Child" + i);
//        }

        // Adding header and childs to hash map
        child1.add("孙悟空");
        child2.add("孙悟空");
        hashMap.put(header.get(0), child1);
        hashMap.put(header.get(1), child2);


        adapter = new ExpandableListAdapter(getActivity(), header, hashMap);
        // Setting adpater over expandablelistview
        expandableListView.setAdapter(adapter);
        expandableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),header.get(position),Toast.LENGTH_LONG).show();
            }
        });

    }
}

