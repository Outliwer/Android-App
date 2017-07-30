package com.example.outlier.prictace_1.AboutMe.AllClass;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.outlier.prictace_1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/16 0016.
 */

public class otherStrategyAdapter extends RecyclerView.Adapter<otherStrategyAdapter.viewHolder> {
    private otherStrategyAdapter.OnItemLongClickListener mOnItemLongClickListener;
    List<otherStrategyItem> motherStratgyList=new ArrayList<>();


    public otherStrategyAdapter(List<otherStrategyItem> contents) {
        this.motherStratgyList = contents;
    }


    @Override
    public int getItemCount() {
        return motherStratgyList.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder{
        TextView otherStrategyName;
        ImageView pictureOtherStrategy;
        TextView otherStrategyContent;

        public viewHolder(View view) {
            super(view);
            otherStrategyName=(TextView)view.findViewById(R.id.strategy_title);
            pictureOtherStrategy=(ImageView)view.findViewById(R.id.picture_strategy);
            otherStrategyContent=(TextView)view.findViewById(R.id.strategy_content);
        }
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.other_strategy_item,parent,false);
        viewHolder holder=new viewHolder(view);
        return holder;
    }

    public void setOnItemLongClickListener(otherStrategyAdapter.OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }


    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }

    @Override
    public void onBindViewHolder(final viewHolder holder, int position) {
        otherStrategyItem otherStrategy=motherStratgyList.get(position);
        holder.otherStrategyName.setText(otherStrategy.getTitle());
        holder.pictureOtherStrategy.setImageResource(otherStrategy.getPictureStrategy());
        holder.otherStrategyContent.setText(otherStrategy.getStrategyContent());

        //判断是否设置了监听器
        if(mOnItemLongClickListener != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView,position);
                    //返回true 表示消耗了事件 事件不会继续传递
                    return true;
                }
            });
        }
    }
}