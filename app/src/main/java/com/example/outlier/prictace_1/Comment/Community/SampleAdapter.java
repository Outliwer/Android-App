package com.example.outlier.prictace_1.Comment.Community;


import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

/***
 * ADAPTER
 */

public class SampleAdapter extends ArrayAdapter<commentItem> {

    private static final String TAG = "SampleAdapter";

    static class ViewHolder {
        ImageView commentPicture;
        TextView commentContent;
        TextView commentDate;
    }

    private final LayoutInflater mLayoutInflater;
    private final ArrayList<Integer> mBackgroundColors;

    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();

    public SampleAdapter(final Context context, final int textViewResourceId) {
        super(context, textViewResourceId);
        mLayoutInflater = LayoutInflater.from(context);
        mBackgroundColors = new ArrayList<Integer>();
        mBackgroundColors.add(R.color.orange);
        mBackgroundColors.add(R.color.green);
        mBackgroundColors.add(R.color.blue);
        mBackgroundColors.add(R.color.color_tab_1);
        mBackgroundColors.add(R.color.color_tab_2);
        initImageLoader(context);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        commentItem now=getItem(position);
        Log.e("now---",""+now);
        ViewHolder vh;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.mycomment_item, parent, false);
            vh = new ViewHolder();
            vh.commentContent=(TextView)convertView.findViewById(R.id.comment_content);

            vh.commentPicture=(ImageView)convertView.findViewById(R.id.picture_comment);

            vh.commentDate=(TextView)convertView.findViewById(R.id.comment_date);

            convertView.setTag(vh);
        }
        else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.commentContent.setText(now.getCommentContent());
        ImageLoaderUtils.displayImage(now.getPictureId(),vh.commentPicture);
        vh.commentDate.setText(now.getCommentDate());
        int backgroundIndex = position >= mBackgroundColors.size() ?
                position % mBackgroundColors.size() : position;

        convertView.setBackgroundResource(mBackgroundColors.get(backgroundIndex));

        return convertView;
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