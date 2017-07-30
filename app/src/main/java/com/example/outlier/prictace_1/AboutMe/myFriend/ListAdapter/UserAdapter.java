package com.example.outlier.prictace_1.AboutMe.myFriend.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.outlier.prictace_1.AboutMe.myFriend.Item.UserItem;
import com.example.outlier.prictace_1.R;

import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * Created by mummyding on 15-9-3.
 */
public class UserAdapter extends ArrayAdapter<UserItem>{
    public UserAdapter(Context context, int resource, List<UserItem> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        UserItem userItem = getItem(position);
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.useritem_view,null);
            viewHolder = new ViewHolder();
            viewHolder.userName = (TextView) view.findViewById(R.id.username);
            viewHolder.userName.setText(userItem.getUserName());
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
            viewHolder.userName.setText(userItem.getUserName());
        }
        return view;
    }



    private class ViewHolder{
        TextView userName;
    }
}
