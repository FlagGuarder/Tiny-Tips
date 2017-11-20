package com.cartoon.tinytips.Activity.MyAccount;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cartoon.tinytips.R;

/**
 * Created by cartoon on 2017/10/25.
 */

public class MyAccount extends Fragment {
    //我的账号页面，含有账号信息以及app的设置等功能
    //layout:myaccount

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myaccount, container, false);
        initView(view);
        return view;
    }
    private void initView(View view){

    }
}
