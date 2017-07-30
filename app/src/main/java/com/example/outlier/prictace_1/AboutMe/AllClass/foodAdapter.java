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

public class foodAdapter extends RecyclerView.Adapter<foodAdapter.viewHolder> {
    private foodAdapter.OnItemLongClickListener mOnItemLongClickListener;
    List<foodItem> mfoodList=new ArrayList<>();


    public foodAdapter(List<foodItem> contents) {
        this.mfoodList = contents;
    }


    @Override
    public int getItemCount() {
        return mfoodList.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder{
        ImageView pictureFood;
        TextView foodContent;

        public viewHolder(View view) {
            super(view);
            pictureFood=(ImageView)view.findViewById(R.id.picture_food);
            foodContent=(TextView)view.findViewById(R.id.food_content);
        }
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item,parent,false);
        viewHolder holder=new viewHolder(view);
        return holder;
    }


    public void setOnItemLongClickListener(foodAdapter.OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }


    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }


    @Override
    public void onBindViewHolder(final viewHolder holder, int position) {
        foodItem food=mfoodList.get(position);
        holder.pictureFood.setImageResource(food.getPictureId());
        holder.foodContent.setText(food.getFoodContent());

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