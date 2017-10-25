package com.cartoon.tinytips;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cartoon on 2017/10/25.
 */

public class AddNote extends Fragment {
    //新建笔记页面
    //layout：addnote

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.addnote,container,false);
        return view;
    }
}
