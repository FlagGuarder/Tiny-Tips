package com.cartoon.tinytips.Activity.HomePage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cartoon.tinytips.Activity.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.Util.Util.LogUtil;
import com.cartoon.tinytips.data.Note;

public class HomePageNoteDetails extends AppCompatActivity implements View.OnClickListener{
    //笔记详情页
    //layout：homepage_note_details
    //还差一个是否收藏按钮

    private TextView back;               //标题栏的返回按钮
    private TextView save;               //标题栏的保存按钮
    private TextView title;              //标题
    private TextView author;             //作者
    private TextView date;               //日期
    private TextView classify1;          //分类1
    private TextView classify2;          //分类2
    private TextView classify3;          //分类3
    private TextView wordDetails;        //文字性内容
    private ImageView imageView1;        //图片1
    private ImageView imageView2;        //图片2
    private ImageView imageView3;        //图片3

    private Note noteFromHomePage;        //在首页点击后intent传递过来的对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_note_details);
        initView();                   //控件实例化
        getDataFromHomePage();        //获取从首页传递下来的数据
        putDataToUI();                //将从首页获得的数据显示在UI上
        back.setOnClickListener(this);
        save.setOnClickListener(this);
    }
    private void initView(){
        //控件实例化
        back=(TextView)findViewById(R.id.homePageNoteDetailsBack);
        save=(TextView)findViewById(R.id.homePageNoteDetailsSave);
        title=(TextView)findViewById(R.id.homePageNoteDetailsTitle);
        author=(TextView)findViewById(R.id.homePageNoteDetailsAuthor);
        date=(TextView)findViewById(R.id.homePageNoteDetailsDate);
        classify1=(TextView)findViewById(R.id.homePageNoteDetailsClassify1);
        classify2=(TextView)findViewById(R.id.homePageNoteDetailsClassify2);
        classify3=(TextView)findViewById(R.id.homePageNoteDetailsClassify3);
        wordDetails=(TextView)findViewById(R.id.homePageNoteDetailsWordDetails);
        imageView1=(ImageView)findViewById(R.id.homePageNoteDetailsImage1);
        imageView2=(ImageView)findViewById(R.id.homePageNoteDetailsImage2);
        imageView3=(ImageView)findViewById(R.id.homePageNoteDetailsImage3);
    }
    private void getDataFromHomePage(){
        //获取从首页传递下来的数据
        noteFromHomePage=(Note)getIntent().getSerializableExtra("dataFromHomePage");
    }
    private void putDataToUI(){
        //将从首页获得的数据显示在UI上
        title.setText(noteFromHomePage.getTitle());
        author.setText(noteFromHomePage.getAuthor());
        date.setText(noteFromHomePage.getDate());
        classify1.setText(noteFromHomePage.getClassify1());
        classify2.setText(noteFromHomePage.getClassify2());
        classify3.setText(noteFromHomePage.getClassify3());
        wordDetails.setText(noteFromHomePage.getWordDetails());
    }
    @Override
    public void onClick(View view){
        //各种组件的点击函数
        switch (view.getId()){
            case R.id.homePageNoteDetailsBack:{
                Intent intent=new Intent( this,Main.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.homePageNoteDetailsSave:{
                break;
            }
        }
    }
    @Override
    public void onBackPressed(){
        //重写返回键的处理函数
        Intent intent=new Intent( this,Main.class);
        startActivity(intent);
        finish();
    }
}
