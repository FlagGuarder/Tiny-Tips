package com.cartoon.tinytips.Activity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cartoon.tinytips.R;

/**
 * Created by cartoon on 2017/10/25.
 */

public class AddNote extends Fragment implements View.OnClickListener{
    //新建笔记页面
    //layout：addnote
    //通过string以及boolean等变量获取输入的数据
    //通过onClick函数处理相关点击事件

    private EditText addTitle;         //输入标题
    private EditText addWordDetails;   //输入文字描述
    private TextView addPhoto;         //添加图片
    private ImageView photo1;         //photo1--photo7为上传的照片
    private ImageView photo2;
    private ImageView photo3;
    private ImageView photo4;
    private ImageView photo5;
    private ImageView photo6;
    private ImageView photo7;
    private TextView addClassify;     //添加分类
    private TextView classify1;      //classify1--classify3为选择的分类
    private TextView classify2;
    private TextView classify3;
    private CheckBox isShare;            //选择是否允许分享
    private TextView comfirm;            //确定，储存输入信息

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.addnote,container,false);
        initView(view);
        addPhoto.setOnClickListener(this);
        addClassify.setOnClickListener(this);
        comfirm.setOnClickListener(this);
        if(isShare.isChecked()){
            //选中分享框
        }
        return view;
    }
    private void initView(View view){
        //实例化控件
        addTitle=(EditText)view.findViewById(R.id.addNoteTitle);
        addWordDetails=(EditText)view.findViewById(R.id.addNoteWordDetails);
        addPhoto=(TextView)view.findViewById(R.id.addNot1eTakePhoto);
        photo1=(ImageView)view.findViewById(R.id.addNotePhoto1);
        photo2=(ImageView)view.findViewById(R.id.addNotePhoto2);
        photo3=(ImageView)view.findViewById(R.id.addNotePhoto3);
        photo4=(ImageView)view.findViewById(R.id.addNotePhoto4);
        photo5=(ImageView)view.findViewById(R.id.addNotePhoto5);
        photo6=(ImageView)view.findViewById(R.id.addNotePhoto6);
        photo7=(ImageView)view.findViewById(R.id.addNotePhoto7);
        addClassify=(TextView)view.findViewById(R.id.addNoteAddClassify);
        classify1=(TextView)view.findViewById(R.id.addNoteClassify1);
        classify2=(TextView)view.findViewById(R.id.addNoteClassify2);
        classify3=(TextView)view.findViewById(R.id.addNoteClassify3);
        isShare=(CheckBox)view.findViewById(R.id.addNoteIsShare);
        comfirm=(TextView)view.findViewById(R.id.addNoteComfirm);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.addNot1eTakePhoto:{
                break;
            }
            case R.id.addNoteAddClassify:{
                break;
            }
            case R.id.addNoteComfirm:{
                break;
            }
        }
    }
}
