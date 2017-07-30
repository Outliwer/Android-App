package com.example.outlier.prictace_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.ImageLoaderUtils;
import com.example.outlier.prictace_1.AboutMe.AllClass.personInfo;
import com.example.outlier.prictace_1.AboutMe.PersonalLoginActivity;
import com.example.outlier.prictace_1.AboutMe.chat.HomeActivty;
import com.example.outlier.prictace_1.AboutMe.personlogin;
import com.example.outlier.prictace_1.AboutMe.mycomment;
import com.example.outlier.prictace_1.AboutMe.mystrategy;
import  com.example.outlier.prictace_1.AboutMe.mycollection;
import  com.example.outlier.prictace_1.AboutMe.myphoto;
import  com.example.outlier.prictace_1.AboutMe.replacemypersoninfo;
import  com.example.outlier.prictace_1.AboutMe.myFriend.myfriendlist;
import com.leon.lib.settingview.LSettingItem;
import com.squareup.picasso.Picasso;


import static android.app.Activity.RESULT_OK;
import static com.example.outlier.prictace_1.AboutMe.personlogin.setPersonInfoList;
import static com.example.outlier.prictace_1.MainActivity.getStateLogged;
import static com.example.outlier.prictace_1.MainActivity.setStateLogged;
import static com.example.outlier.prictace_1.SecondFragment.setChooseNum;


/**
 * Created by outlier on 2017/7/9.
 */

public class FourthFragment extends Fragment{

    //用于储存当时登陆的是哪一个账号
    private String nowAccount="";
    //定义所有的控件
    private ImageView emptyHeadIcon;
    private TextView labelPersonName;
    private LSettingItem labelAccountInfo;

    //修改信息
    private LSettingItem replacePersoninfo;
    //退出登录
    private LSettingItem btnToUnlogged;
    //我的点评
    private LSettingItem labelCommentButton;
    //我的攻略
    private LSettingItem iconToCollection;
    private LSettingItem iconToHistory;
    private LSettingItem iconToPhoto;
    private LSettingItem iconToFriend;
    private LSettingItem labelStrategyButton;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fg_4_personinfo,viewGroup,false);

        initeView(view);
        return view;
    }



    private void initeView(final View view) {

        labelPersonName=(TextView)view.findViewById(R.id.label_personname);
        labelAccountInfo=(LSettingItem)view.findViewById(R.id.label_accountinfo);

        if (BaseApplication.get("username","").equals(""))
            labelPersonName.setText("未设置");
        else
            labelPersonName.setText(BaseApplication.get("username",""));


        //未登录状态下把某些控件隐藏
        replacePersoninfo=(LSettingItem)view.findViewById(R.id.replace_personinfo);
        replacePersoninfo.setVisibility(View.GONE);

        btnToUnlogged=(LSettingItem)view.findViewById(R.id.btn_exit_Logged);
        btnToUnlogged.setVisibility(View.GONE);

        emptyHeadIcon=(ImageView)view.findViewById(R.id.empty_head_icon);
        emptyHeadIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), PersonalLoginActivity.class),0);
                //startActivity(new Intent(getActivity(),personlogin.class));
            }
        });
        if (BaseApplication.get("userpicture","").equals(""))
            Picasso.with(getActivity()).load(R.drawable.girl).transform(new CircleTransform()).into(emptyHeadIcon);
        else
            ImageLoaderUtils.displayImage(BaseApplication.get("userpicture","http://www.nvsay.com/uploads/allimg/160731/42-160I11500240-L.jpg"),emptyHeadIcon);





        //对所有控件设置点击事件
        labelCommentButton=(LSettingItem)view.findViewById(R.id.label_comment_button);
        labelCommentButton.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                startActivity(new Intent(getActivity(), mycomment.class));
            }
        });


        labelStrategyButton=(LSettingItem)view.findViewById(R.id.label_strategy_button);
        labelStrategyButton.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                startActivity(new Intent(getActivity(), mystrategy.class));
            }
        });


        iconToCollection=(LSettingItem)view.findViewById(R.id.icon_to_collection);
        iconToCollection.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                startActivity(new Intent(getActivity(), mycollection.class));
            }
        });


        iconToPhoto=(LSettingItem)view.findViewById(R.id.icon_to_photo);
        iconToPhoto.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                startActivity(new Intent(getActivity(), myphoto.class));
            }
        });

        iconToHistory=(LSettingItem)view.findViewById(R.id.icon_to_history);
        iconToHistory.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                //跳转到足迹模块的历史足迹页面
                SecondFragment.setChooseNum(1);
                startActivity(new Intent(getActivity(), SecondFragment.class));
                //getActivity().findViewById(R.id.second_image).callOnClick();
            }
        });

        iconToFriend=(LSettingItem)view.findViewById(R.id.icon_to_friend);
        iconToFriend.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                startActivity(new Intent(getActivity(), HomeActivty.class));
            }
        });


        replacePersoninfo=(LSettingItem)view.findViewById(R.id.replace_personinfo);
        replacePersoninfo.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                startActivityForResult(new Intent(getActivity(), replacemypersoninfo.class),2);
            }
        });

        btnToUnlogged=(LSettingItem)view.findViewById(R.id.btn_exit_Logged);
        btnToUnlogged.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                setStateLogged(false);
                labelPersonName.setText("未设置");
                Picasso.with(getActivity()).load(R.drawable.girl).transform(new CircleTransform()).into(emptyHeadIcon);
                BaseApplication.set("userid","null");
                //labelAccountInfo.setText("账号：");
                replacePersoninfo.setVisibility(View.GONE);
                btnToUnlogged.setVisibility(View.GONE);
                emptyHeadIcon.setClickable(true);
                setStateLogged(false);
                setSomeBtnState(false);
            }
        });

        //根据当前是否登陆来设定某些控件是否可用
        setSomeBtnState(getStateLogged());
    }

    private void setSomeBtnState(boolean state){
        if(state){
            labelCommentButton.setClickable(true);
            labelStrategyButton.setClickable(true);
            iconToCollection.setClickable(true);
            iconToPhoto.setClickable(true);
            iconToHistory.setClickable(true);
            iconToFriend.setClickable(true);
        }
        else {
            labelCommentButton.setClickable(true);
            labelStrategyButton.setClickable(true);
            iconToCollection.setClickable(true);
            iconToPhoto.setClickable(true);
            iconToHistory.setClickable(true);
            iconToFriend.setClickable(true);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                Bundle b=data.getExtras();
                replacePersoninfo.setVisibility(View.VISIBLE);
                btnToUnlogged.setVisibility(View.VISIBLE);
                labelPersonName.setText(BaseApplication.get("username", "1234"));
                //labelAccountInfo.setText("账号："+b.getString("account"));
                Picasso.with(getActivity()).load(BaseApplication.get("userpicture","http://www.nvsay.com/uploads/allimg/160731/42-160I11500240-L.jpg")).transform(new CircleTransform()).into(emptyHeadIcon);
                emptyHeadIcon.setClickable(false);
                setSomeBtnState(getStateLogged());
                break;
            case 2:
                if(resultCode==RESULT_OK){
                    Bundle b_1=data.getExtras();
                    String newAccount=b_1.getString("account");
                    String newPassword=b_1.getString("password");
                    String newName=b_1.getString("name");
                    //将账户列表中原来的这个人的信息改过来
                    personInfo newOne=new personInfo(newAccount,newPassword,newName,R.drawable.girl);
                    setPersonInfoList(nowAccount,newOne);
                    //将页面中显示的信息更改
                    labelPersonName.setText(BaseApplication.get("username", "1234"));
                    //labelAccountInfo.setText("账户："+newAccount);
                }
                break;
            case 3:
                Bundle b_1=data.getExtras();
                replacePersoninfo.setVisibility(View.VISIBLE);
                btnToUnlogged.setVisibility(View.VISIBLE);
                labelPersonName.setText(BaseApplication.get("username", "1234"));
                //labelAccountInfo.setText("账号："+b.getString("account"));
                ImageLoaderUtils.displayImage(BaseApplication.get("userpicture","http://www.nvsay.com/uploads/allimg/160731/42-160I11500240-L.jpg"),emptyHeadIcon);
                emptyHeadIcon.setClickable(false);
                setSomeBtnState(getStateLogged());
                break;
            default:
                break;
        }
    }

}
