package com.example.outlier.prictace_1.MyTrip.tripLibrary.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.outlier.prictace_1.AboutMe.AllClass.sightAdapter;
import com.example.outlier.prictace_1.MyTrip.tripLibrary.TripContentItem;
import com.example.outlier.prictace_1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/24 0024.
 */

public class TripContentAdapter extends RecyclerView.Adapter<TripContentAdapter.viewHolder>{
    private sightAdapter.OnItemLongClickListener mOnItemLongClickListener;
    List<TripContentItem> mtripList=new ArrayList<>();


    public TripContentAdapter(List<TripContentItem> contents) {
        this.mtripList = contents;
    }


    @Override
    public int getItemCount() {
        return mtripList.size();
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view, int position);
    }


    static class viewHolder extends RecyclerView.ViewHolder{
        ImageView tripPhoto;
        TextView tripContent;

        public viewHolder(View view) {
            super(view);
            tripPhoto=(ImageView)view.findViewById(R.id.picture_trip);
            tripContent=(TextView)view.findViewById(R.id.trip_content);
        }
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_content_layout,parent,false);
        viewHolder holder=new viewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final viewHolder holder, int position) {
        TripContentItem trip=mtripList.get(position);
        holder.tripPhoto.setImageResource(trip.getPictureId());
        holder.tripContent.setText(trip.getTripContent());
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


    public void setOnItemLongClickListener(sightAdapter.OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }





}
