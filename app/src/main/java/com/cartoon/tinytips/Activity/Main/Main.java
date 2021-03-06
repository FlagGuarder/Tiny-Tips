package com.cartoon.tinytips.Activity.Main;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.cartoon.tinytips.Activity.HomePage.HomePage;
import com.cartoon.tinytips.Activity.MyAccount.MyAccount;
import com.cartoon.tinytips.R;

public class Main extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    //主页面，内有首页，发现（未实现），关注（未实现），新建笔记，我的页面，页面都为fragment，切换fragment实现功能
    //layout:main

    private BottomNavigationView bottomNavigationView;

    private int switchFrage;         //根据值的不同切换fragement
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        Intent intent=new Intent();
        switchFrage=intent.getIntExtra("data_return",0);
        switchFragment(switchFrage);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
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
            case R.id.mainMe:{
                switchFragment(1);
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
                MyAccount myAccount=new MyAccount();
                transaction.replace(R.id.mainFrame,myAccount);
                break;
            }
        }
        transaction.commit();
    }
}
