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
import android.widget.TextView;

import com.cartoon.tinytips.R;
import com.cartoon.tinytips.Util.HomePage.NoteAdapter;
import com.cartoon.tinytips.data.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cartoon on 2017/10/25.
 */

public class HomePage extends Fragment implements View.OnClickListener{
    //主页，笔记的滑动列表
    //layout:homepage
    //将数据传进noteList即可实现RecyclerView数据的更新
    //RecyclerView的点击事件在NoteAdapter进行处理
    //搜索与分类按钮的点击事件在onOptionsItemSelected中处理

    private View view;

    private TextView search;        //标题栏搜索按钮
    private TextView classify;      //标题栏分类按钮
    private RecyclerView note;      //笔记滑动列表
    private FloatingActionButton addNote;
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
        search.setOnClickListener(this);
        classify.setOnClickListener(this);
        addNote.setOnClickListener(this);     //处理分类按钮点击事件
        refreshNote.setColorSchemeResources(R.color.colorPrimary);      //设置下拉标志颜色
        refreshNote.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            //刷新note列表
            @Override
            public void onRefresh() {
                refreshNotes();
            }
        });
        return view;
    }
    private void initView(View view){
        //实例化控件
        search=(TextView)view.findViewById(R.id.homePageSearch);
        classify=(TextView)view.findViewById(R.id.homePageClassify);
        note=(RecyclerView)view.findViewById(R.id.homePageNote);
        addNote=(FloatingActionButton)view.findViewById(R.id.homePageAddNote);
        refreshNote=(SwipeRefreshLayout)view.findViewById(R.id.homePageRefreshNote);
    }
    private void initNotes(){
        //初始化RecyclerView数据
        noteList.clear();
        Note note=new Note();
        note.setTitle("JAVA");
        note.setWordDetails("输入输出流的操作");
        note.setImageDetails1("");
        note.setImageDetails2("");
        note.setImageDetails3("");
        note.setClassify1("JAVA");
        note.setClassify2("InputStream");
        note.setClassify3("OutStream");
        note.setDate("2017年11月28日");
        note.setCollect(true);
        note.setAuthor("cartoon");
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
            case R.id.homePageAddNote:{
                Intent intent=new Intent(getActivity(),HomePageAddNote.class);
                getActivity().startActivity(intent);
                break;
            }
            case R.id.homePageSearch:{
                break;
            }
            case R.id.homePageClassify:{
                break;
            }
        }
    }
}
