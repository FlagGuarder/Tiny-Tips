package com.cartoon.tinytips.Activity.HomePage;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cartoon.tinytips.R;
import com.cartoon.tinytips.Util.GetContext;
import com.cartoon.tinytips.Util.HomePage.NoteAdapter;
import com.cartoon.tinytips.data.Note;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;

/**
 * Created by cartoon on 2017/10/25.
 */

public class HomePage extends Fragment implements View.OnClickListener{
    //主页，笔记的滑动列表
    //layout:homepage
    //将数据传进noteList即可实现RecyclerView数据的更新
    //RecyclerView的点击事件在NoteAdapter进行处理
    //分类按钮的点击事件在公有函数onClick中处理

    private View view;

    private RecyclerView note;      //笔记滑动列表
    private FloatingActionButton sortNote;
    private SwipeRefreshLayout refreshNote;    //下拉更新note

    private GridLayoutManager manager;
    private List<Note> noteList=new ArrayList<>();
    private NoteAdapter adapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view=inflater.inflate(R.layout.homepage,container,false);
        initView(view);       //控件实例化
        initNotes();          //初始化RecyclerView数据
        manager=new GridLayoutManager(view.getContext(),1);
        adapter=new NoteAdapter(noteList);      //将noteList传入NoteAdapter进行处理
        note.setLayoutManager(manager);         //为RecyclerView提供layout依赖
        note.setAdapter(adapter);               //为RecyclerView进行适配
        sortNote.setOnClickListener(this);     //处理分类按钮点击事件
        refreshNote.setColorSchemeResources(R.color.colorPrimary);      //设置下拉标志颜色
        refreshNote.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshNotes();
            }
        });
        return view;
    }
    private void initView(View view){
        //实例化控件
        note=(RecyclerView)view.findViewById(R.id.homePageNote);
        sortNote=(FloatingActionButton)view.findViewById(R.id.homePageSortNote);
        refreshNote=(SwipeRefreshLayout)view.findViewById(R.id.homePageRefreshNote);
    }
    private void initNotes(){
        //初始化RecyclerView数据
        noteList.clear();
        Note note=new Note("JAVA","输入输出流的操作",
                R.drawable.apple,R.drawable.apple,R.drawable.apple,
                "JAVA","InputStream","OutStream",
                "2017年11月28日",TRUE,"cartoon");
        for(int i=0;i<20;i++){
            noteList.add(note);
        }
    }
    private void refreshNotes(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initNotes();
                        adapter.notifyDataSetChanged();
                        refreshNote.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.homePageSortNote:{
                break;
            }
        }
    }
}
