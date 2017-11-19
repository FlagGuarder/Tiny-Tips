package com.cartoon.tinytips.Main;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.cartoon.tinytips.AddNote.AddNote;
import com.cartoon.tinytips.HomePage.HomePage;
import com.cartoon.tinytips.MyAccount.MyAccount;
import com.cartoon.tinytips.R;

public class Main extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    //主页面，内有首页，发现（未实现），关注（未实现），新建笔记，我的页面，页面都为fragment，切换fragment实现功能
    //layout:main

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        switchFragment(0);
    }
    private void initView(){
        //实例化组件
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.mainBottomNavigationView);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        //点击底部导航栏按钮处理函数
        switch (item.getItemId()){
            case R.id.mainHomePage:{
                switchFragment(0);
                break;
            }
            case R.id.mainAddNote:{
                switchFragment(1);
                break;
            }
            case R.id.mainMe:{
                switchFragment(2);
                break;
            }
        }
        return true;
    }
    private void switchFragment(int flag){
        //跳转Fragment
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        switch (flag){
            case 0:{
                HomePage homePage=new HomePage();
                transaction.replace(R.id.mainFrame,homePage);
                break;
            }
            case 1:{
                AddNote addNote=new AddNote();
                transaction.replace(R.id.mainFrame,addNote);
                break;
            }
            case 2:{
                MyAccount myAccount=new MyAccount();
                transaction.replace(R.id.mainFrame,myAccount);
                break;
            }
        }
        transaction.commit();
    }
}
