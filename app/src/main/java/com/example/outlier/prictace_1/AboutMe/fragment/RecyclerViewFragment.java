package com.example.outlier.prictace_1.AboutMe.fragment;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.outlier.prictace_1.AboutMe.AllClass.sightAdapter;
import com.example.outlier.prictace_1.AboutMe.AllClass.sightItem;
import com.example.outlier.prictace_1.AboutMe.myFriend.Item.UserItem;
import com.example.outlier.prictace_1.AboutMe.myFriend.Toolkit.JSONHelper;
import com.example.outlier.prictace_1.BaseApplication;
import com.example.outlier.prictace_1.R;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class RecyclerViewFragment extends Fragment {

    private static final boolean GRID_LAYOUT = false;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        //setup materialviewpager

        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setHasFixedSize(true);

        //将“没有XX”的标签显示隐藏
        TextView noItem=(TextView)view.findViewById(R.id.label_noitem);
        noItem.setText("您还没有添加任何景点");
        noItem.setVisibility(View.GONE);


        //初始化景点列表
        final List<sightItem> items = new ArrayList<>();
        if (BaseApplication.get("collection","").equals("")){
            Toast.makeText(getActivity(),"没有收藏",Toast.LENGTH_LONG).show();
        }else {
            JSONArray jsonArray = JSONHelper.getJSONArray(BaseApplication.get("collection", ""));
            //
            Log.e("", "" + BaseApplication.get("collection", ""));
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    String title = JSONHelper.getObjString((JSONObject) jsonArray.get(i), "title");
                    String type = JSONHelper.getObjString((JSONObject) jsonArray.get(i), "type");
                    String picture = JSONHelper.getObjString((JSONObject) jsonArray.get(i), "picture");
                    if (type.equals("jingdian")) {
                        sightItem first = new sightItem(picture, title);
                        items.add(first);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        //将景点的列表列出来
        final sightAdapter adapter=new sightAdapter(items);
        adapter.setOnItemLongClickListener(new sightAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view,final int position) {
                //定义AlertDialog.Builder对象，当长按列表项的时候弹出确认删除对话框
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setMessage("确定删除?");
                builder.setTitle("提示");
                //添加AlertDialog.Builder对象的setPositiveButton()方法
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(items.remove(position)!=null){
                            System.out.println("success");
                        }else {
                            System.out.println("failed");
                        }
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "删除列表项", Toast.LENGTH_SHORT).show();
                    }
                });

                //添加AlertDialog.Builder对象的setNegativeButton()方法
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create().show();
            }
        });
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(adapter);

        if(!(items.size()>0)){
            noItem.setVisibility(View.VISIBLE);
        }
    }
}
