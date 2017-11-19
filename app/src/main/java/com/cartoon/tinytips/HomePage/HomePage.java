package com.cartoon.tinytips.HomePage;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cartoon.tinytips.R;

/**
 * Created by cartoon on 2017/10/25.
 */

public class HomePage extends Fragment{
    //主页，笔记的滑动列表
    //layout:homepage

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.homepage,container,false);
        return view;
    }
}
