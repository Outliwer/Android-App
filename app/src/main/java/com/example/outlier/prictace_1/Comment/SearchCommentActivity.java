package com.example.outlier.prictace_1.Comment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.outlier.prictace_1.Comment.Search.SearchAnswerActivity;
import com.example.outlier.prictace_1.Comment.Search.SearchCommentHistorysDao;
import com.example.outlier.prictace_1.HomePage.Search.Bean.SearchHistorysBean;
import com.example.outlier.prictace_1.HomePage.Search.SearchActivity;
import com.example.outlier.prictace_1.HomePage.Search.SearchBeanEntity;
import com.example.outlier.prictace_1.HomePage.Search.SearchBodyEntity;
import com.example.outlier.prictace_1.HomePage.Search.SearchDetailActivity;
import com.example.outlier.prictace_1.HomePage.Search.SearchEntity;
import com.example.outlier.prictace_1.HomePage.Search.SearchListEntity;
import com.example.outlier.prictace_1.HomePage.Search.ToastUtils;
import com.example.outlier.prictace_1.HomePage.Search.database.SearchHistorysDao;
import com.example.outlier.prictace_1.HomePage.Search.picList;
import com.example.outlier.prictace_1.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchCommentActivity extends AppCompatActivity implements View.OnClickListener  {

    //声明控件
    private EditText et_search_keyword;
    private Button btn_search;
    private TextView tv_back;
    private TextView tv_clear;
    private LinearLayout ll_content;
    private ListView lv_history_word;
    //实现历史搜索记录
    private SearchCommentHistorysDao dao;
    private ArrayList<SearchHistorysBean> historywordsList = new ArrayList<SearchHistorysBean>();
    private SearchHistoryAdapter mAdapter_1;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_comment);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        dao = new SearchCommentHistorysDao(this);
        historywordsList = dao.findAll();
        initView();
    }

    private void initView(){
        et_search_keyword = (EditText) findViewById(R.id.et_search_keyword);

        btn_search = (Button) findViewById(R.id.btn_search);
        btn_search.setOnClickListener(this);

        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);

        tv_clear = (TextView) findViewById(R.id.tv_clear);
        tv_clear.setOnClickListener(this);

        ll_content = (LinearLayout) findViewById(R.id.ll_content);
        lv_history_word = (ListView) findViewById(R.id.lv_history_word);
        mAdapter_1 = new SearchCommentActivity.SearchHistoryAdapter(historywordsList);
        count = mAdapter_1.getCount();
        if (count > 0) {

            tv_clear.setVisibility(View.VISIBLE);
        } else {
            tv_clear.setVisibility(View.GONE);
        }
        lv_history_word.setAdapter(mAdapter_1);
        lv_history_word.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SearchHistorysBean bean = (SearchHistorysBean) mAdapter_1.getItem(position);

                et_search_keyword.setText(bean.historyword);

                mAdapter_1.notifyDataSetChanged();
                btn_search.performClick();
            }
        });
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_search:
                String searchWord = et_search_keyword.getText().toString().trim();
                if (TextUtils.isEmpty(searchWord)) {
                    ToastUtils.show(this, "请输入");
                } else {
                    dao.addOrUpdate(searchWord);
                    //事件
                    Intent intent = new Intent(SearchCommentActivity.this, SearchAnswerActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_clear:
                dao.deleteAll();
                historywordsList.clear();
                mAdapter_1.notifyDataSetChanged();
                tv_clear.setVisibility(View.GONE);
                break;
            default:
                break;

        }
    }


    class SearchHistoryAdapter extends BaseAdapter {

        private ArrayList<SearchHistorysBean> historywordsList;

        public SearchHistoryAdapter(ArrayList<SearchHistorysBean> historywordsList) {
            this.historywordsList = historywordsList;
        }

        @Override
        public int getCount() {

            return historywordsList == null ? 0 : historywordsList.size();
        }

        @Override
        public Object getItem(int position) {

            return historywordsList.get(position);
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            SearchCommentActivity.ViewHolder holder = null;
            if (convertView == null) {
                holder = new SearchCommentActivity.ViewHolder();
                convertView = LayoutInflater.from(SearchCommentActivity.this).inflate(R.layout.item_search_history_word, null);
                holder.tv_word = (TextView) convertView.findViewById(R.id.tv_search_record);
                convertView.setTag(holder);
            } else {
                holder = (SearchCommentActivity.ViewHolder) convertView.getTag();
            }

            holder.tv_word.setText(historywordsList.get(position).toString());
            return convertView;
        }

    }

    class ViewHolder {
        TextView tv_word;
    }
}

