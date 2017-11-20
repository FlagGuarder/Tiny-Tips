package com.cartoon.tinytips.Activity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cartoon.tinytips.R;

/**
 * Created by cartoon on 2017/10/25.
 */

public class AddNote extends Fragment {
    //新建笔记页面
    //layout：addnote

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.addnote,container,false);
        initView(view);
        return view;
    }
    private void initView(View view){
        //实例化控件
    }
}
