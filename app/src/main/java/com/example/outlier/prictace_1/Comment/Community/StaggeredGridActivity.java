package com.example.outlier.prictace_1.Comment.Community;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.etsy.android.grid.StaggeredGridView;
import com.example.outlier.prictace_1.R;

import java.util.ArrayList;

public class StaggeredGridActivity extends Activity implements AbsListView.OnScrollListener{

    private static final String TAG = "StaggeredGridActivity";

    private StaggeredGridView mGridView;
    private boolean mHasRequestedMore;
    private SampleAdapter mAdapter;

    private SampleData allItem = new SampleData();//包含了所有的commentItem的实例
    private ArrayList<commentItem> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgv);
        setTitle("SGV");
        mGridView = (StaggeredGridView) findViewById(R.id.grid_view);


        LayoutInflater layoutInflater = getLayoutInflater();

        View header = layoutInflater.inflate(R.layout.list_item_header_footer, null);
        View footer = layoutInflater.inflate(R.layout.list_item_header_footer2, null);
        TextView txtHeaderTitle = (TextView) header.findViewById(R.id.txt_title);
        ImageView iconBack=(ImageView)header.findViewById(R.id.icon_back);
        txtHeaderTitle.setText("我的社区");

        mGridView.addHeaderView(header);
        mGridView.addFooterView(footer);
        mAdapter = new SampleAdapter(this, R.id.comment_content);

        if (mData == null) {
            mData = allItem.generateSampleData();
        }


        for (commentItem data : mData) {
            mAdapter.add(data);
        }

        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mGridView.setAdapter(mAdapter);
        mGridView.setOnScrollListener(this);


    }


    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {
        Log.d(TAG, "onScroll firstVisibleItem:" + firstVisibleItem +
                " visibleItemCount:" + visibleItemCount +
                " totalItemCount:" + totalItemCount);
        // our handling
        if (!mHasRequestedMore) {
            int lastInScreen = firstVisibleItem + visibleItemCount;
            if (lastInScreen >= totalItemCount) {
                Log.d(TAG, "onScroll lastInScreen - so load more");
                mHasRequestedMore = true;
                onLoadMoreItems();
            }
        }
    }

    private void onLoadMoreItems() {
        ArrayList<commentItem> sampleData = allItem.generateSampleData();
        if(sampleData!=null){
            for (commentItem data : sampleData) {
                mAdapter.add(data);
            }
            // stash all the data in our backing store
            mData.addAll(sampleData);
            // notify the adapter that we can update now
            mAdapter.notifyDataSetChanged();
        }
        mHasRequestedMore = false;
    }



//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {
//
//        //定义AlertDialog.Builder对象，当长按列表项的时候弹出确认删除对话框
//        AlertDialog.Builder builder=new AlertDialog.Builder(StaggeredGridActivity.this);
//        builder.setMessage("确定删除?");
//        builder.setTitle("提示");
//        //添加AlertDialog.Builder对象的setPositiveButton()方法
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//
//                Log.e("mData---",""+mData.size());
//                mAdapter.remove(mAdapter.getItem(position+1));
//                mAdapter.notifyDataSetChanged();
//                Toast.makeText(getBaseContext(), "删除列表项", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        //添加AlertDialog.Builder对象的setNegativeButton()方法
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//            }
//        });
//        builder.create().show();
//    }


}
