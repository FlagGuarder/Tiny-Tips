package com.cartoon.tinytips.Activity.HomePage;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cartoon.tinytips.Activity.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.data.Note;

public class HomePageAddNote extends AppCompatActivity implements View.OnClickListener{

    //新建笔记页面
    //layout：homepage_addnote
    //通过string以及boolean等变量获取输入的数据
    //通过onClick函数处理相关点击事件
    //输入的数据全放在private成员note对象里

    private TextView back;             //标题栏返回按钮
    private EditText addTitle;         //输入标题
    private EditText addWordDetails;   //输入文字描述
    private TextView addPhoto;         //添加图片
    private ImageView photo1;         //photo1--photo3为上传的照片
    private ImageView photo2;
    private ImageView photo3;
    private TextView addClassify;     //添加分类
    private TextView classify1;      //classify1--classify3为选择的分类
    private TextView classify2;
    private TextView classify3;
    private CheckBox isShare;            //选择是否允许分享
    private TextView comfirm;            //确定，储存输入信息

    private Note note;                 //输入的数据全都通过note的set方法存进类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_note_details);
        initView();
        back.setOnClickListener(this);
        addPhoto.setOnClickListener(this);
        addClassify.setOnClickListener(this);
        comfirm.setOnClickListener(this);
        if(isShare.isChecked()){
            //选中分享框
        }
    }
    private void initView(){
        //实例化控件
        back=(TextView)findViewById(R.id.homePageAddNoteBack);
        addTitle=(EditText)findViewById(R.id.homePageAddNoteTitle);
        addWordDetails=(EditText)findViewById(R.id.homePageAddNoteWordDetails);
        addPhoto=(TextView)findViewById(R.id.homePageAddNot1eTakePhoto);
        photo1=(ImageView)findViewById(R.id.homePageAddNotePhoto1);
        photo2=(ImageView)findViewById(R.id.homePageAddNotePhoto2);
        photo3=(ImageView)findViewById(R.id.homePageAddNotePhoto3);
        addClassify=(TextView)findViewById(R.id.homePageAddNoteAddClassify);
        classify1=(TextView)findViewById(R.id.homePageAddNoteClassify1);
        classify2=(TextView)findViewById(R.id.homePageAddNoteClassify2);
        classify3=(TextView)findViewById(R.id.homePageAddNoteClassify3);
        isShare=(CheckBox)findViewById(R.id.homePageAddNoteIsShare);
        comfirm=(TextView)findViewById(R.id.homePageAddNoteComfirm);
    }
    private void getDataFromUI(){
        note.setTitle(addTitle.getText().toString());
        note.setWordDetails(addWordDetails.getText().toString());
        note.setImageDetails1(photo1.getId());
        note.setImageDetails2(photo2.getId());
        note.setImageDetails3(photo3.getId());
        note.setClassify1(classify1.getText().toString());
        note.setClassify2(classify2.getText().toString());
        note.setClassify3(classify3.getText().toString());
        note.setCollect(isShare.getFreezesText());
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.homePageAddNoteBack:{
                AlertDialog.Builder builder=new AlertDialog.Builder(HomePageAddNote.this);
                builder.setTitle("你将退出编辑");
                builder.setMessage("是否保存");
                builder.setCancelable(false);
                builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getDataFromUI();
                        Intent intent=new Intent(HomePageAddNote.this, Main.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent(HomePageAddNote.this, Main.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.show();
                break;
            }
            case R.id.homePageAddNot1eTakePhoto:{
                break;
            }
            case R.id.homePageAddNoteAddClassify:{
                break;
            }
            case R.id.homePageAddNoteComfirm:{
                getDataFromUI();
                break;
            }
        }
    }
    @Override
    public void onBackPressed(){
        Intent intent=new Intent(this, Main.class);
        startActivity(intent);
        finish();
    }
}
