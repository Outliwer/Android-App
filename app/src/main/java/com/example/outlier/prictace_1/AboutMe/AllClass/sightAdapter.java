package com.example.outlier.prictace_1.AboutMe.AllClass;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.ImageLoaderUtils;
import com.example.outlier.prictace_1.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/16 0016.
 */

public class sightAdapter extends RecyclerView.Adapter<sightAdapter.viewHolder> {
    private OnItemLongClickListener mOnItemLongClickListener;
    List<sightItem> msightList=new ArrayList<>();


    public sightAdapter(List<sightItem> contents) {
        this.msightList = contents;
    }


    @Override
    public int getItemCount() {
        return msightList.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder{
        ImageView sightPhoto;
        TextView sightContent;

        public viewHolder(View view) {
            super(view);
            sightPhoto=(ImageView)view.findViewById(R.id.picture_sight);
            sightContent=(TextView)view.findViewById(R.id.sight_content);
        }
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sight_item,parent,false);
        viewHolder holder=new viewHolder(view);
        initImageLoader(parent.getContext());
        return holder;
    }


    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }


    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }



    @Override
    public void onBindViewHolder(final viewHolder holder, int position) {
        sightItem sight=msightList.get(position);

        ImageLoaderUtils.displayImage(sight.getPictureId(),holder.sightPhoto);

        holder.sightContent.setText(sight.getSightContent());

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

    private void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50*1024*1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs()
                // Remove for release app
                .memoryCache(new LruMemoryCache(4 * 1024 * 1024))
                .memoryCacheSize(4 * 1024 * 1024).build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

}