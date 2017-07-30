package com.example.outlier.prictace_1.AboutMe.chat;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import com.example.outlier.prictace_1.AboutMe.myFriend.AboutActivity;
import com.example.outlier.prictace_1.AboutMe.myFriend.AddActivity;
import com.example.outlier.prictace_1.AboutMe.myFriend.AddFriendActivity;
import com.example.outlier.prictace_1.AboutMe.myFriend.NotifyActivity;
import com.example.outlier.prictace_1.AboutMe.myFriend.PageAdapter.ContactPagerAdapter;
import com.example.outlier.prictace_1.AboutMe.myFriend.PageAdapter.ConversationPagerAdapter;
import com.example.outlier.prictace_1.AboutMe.myFriend.SettingActivity;
import com.example.outlier.prictace_1.AboutMe.myFriend.SlideTookit.SlidingTabLayout;
import com.example.outlier.prictace_1.BaseApplication;
import com.example.outlier.prictace_1.MainActivity;
import com.example.outlier.prictace_1.R;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

public class HomeActivty extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,Toolbar.OnMenuItemClickListener{
    private TextView tv_usernmae;
    private TextView tv_nickname;
    private ViewPager pager;
    private ConversationPagerAdapter conversationPagerAdapter;
    private ContactPagerAdapter contactPagerAdapter;
    private SlidingTabLayout tabs;
    private CharSequence conversationTitles[]={"联系人","联系群"};
    private final CharSequence contactTitles[]={"联系人","联系群"};
    private final CharSequence settingTitles[]={"设置"};
    private final int Numboftabs =2;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private static boolean isExit = false;
    private long exitTime = 0;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activty);
        /**
         * 连接融云服务器
         */
        linkRongCloud();
        initView();
    }
    private void initView(){

        pager = (ViewPager) findViewById(R.id.pager);
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        Log.e("tabs",""+tabs);
        conversationPagerAdapter =  new ConversationPagerAdapter(getSupportFragmentManager(),conversationTitles,Numboftabs);
        contactPagerAdapter = new ContactPagerAdapter(getSupportFragmentManager(),contactTitles,Numboftabs);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        tv_usernmae = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_username);
        tv_nickname = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_nickname);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        pager.setAdapter(conversationPagerAdapter);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("会话");
        tabs.setDistributeEvenly(true);
        /*
            设置菜单&导航Item响应
         */

        toolbar.setOnMenuItemClickListener(this);
        navigationView.setNavigationItemSelectedListener(this);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.color_tab_2);
            }
        });
        tabs.setViewPager(pager);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer)
        {
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        /*
            显示用户名&昵称
         */
        tv_usernmae.setText(BaseApplication.get("username",""));
        tv_nickname.setText(BaseApplication.get("userid",""));
          /*
             刷新用户信息
          */
        RongIM.getInstance().refreshUserInfoCache(new UserInfo(BaseApplication.get("userid",""),
                BaseApplication.get("username",""), Uri.parse("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=735591453,1651804909&fm=117&gp=0.jpg")));


    }
    /*
        退出登陆
     */
    private void exitLogin(){
        Intent intent = new Intent(HomeActivty.this,MainActivity.class);
        finish();
        startActivity(intent);
    }
    private void linkRongCloud() {
        RongIM.connect(BaseApplication.get("rongyun",""), new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Toast.makeText(HomeActivty.this, "身份认证失败", Toast.LENGTH_SHORT).show();
                //exitLogin();
            }

            @Override
            public void onSuccess(String s) {
                Toast.makeText(HomeActivty.this, "成功登陆"+s, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Toast.makeText(HomeActivty.this, "连接服务器失败,请检查网络设置", Toast.LENGTH_SHORT).show();
                //exitLogin();
            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        if(menuItem.isChecked()) menuItem.setChecked(false);
        else menuItem.setChecked(true);
        drawerLayout.closeDrawers();
        switch (menuItem.getItemId())
        {
            case R.id.menu_conversation:
                pager.setAdapter(conversationPagerAdapter);
                tabs.setViewPager(pager);
                break;
            case R.id.menu_contact:
                pager.setAdapter(contactPagerAdapter);
                tabs.setViewPager(pager);
                break;
            case R.id.menu_notification:
                Intent intent = new Intent(HomeActivty.this,NotifyActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_setting:
                intent = new Intent(HomeActivty.this,SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_about:
                intent = new Intent(HomeActivty.this,AboutActivity.class);
                startActivity(intent);
                break;
        }
        return false;
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent = new Intent(this,AddFriendActivity.class);
                startActivity(intent);
                break;
            case R.id.action_notify:
                intent = new Intent(HomeActivty.this,NotifyActivity.class);
                startActivity(intent);
                break;
            case R.id.action_settings:
                intent = new Intent(HomeActivty.this,SettingActivity.class);
                startActivity(intent);
                break;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
        按两次返回键退出登陆
     */
}
