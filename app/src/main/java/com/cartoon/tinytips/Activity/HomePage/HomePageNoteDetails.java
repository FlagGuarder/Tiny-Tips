package com.cartoon.tinytips.Activity.HomePage;


import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cartoon.tinytips.Activity.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.Util.Util.LogUtil;
import com.cartoon.tinytips.Util.Util.StringAndBitmap;
import com.cartoon.tinytips.data.Note;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HomePageNoteDetails extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{
    //笔记详情页
    //layout：homepage_note_details
    //将数据库中noteFromHomePage替换成note即可实现修改功能
    //还差一个是否收藏按钮

    private TextView back;               //标题栏的返回按钮
    private TextView save;               //标题栏的保存按钮
    private EditText title;              //标题
    private CheckBox collect;             //收藏按钮
    private TextView author;             //作者
    private TextView date;               //日期
    private TextView classify1;          //分类1
    private TextView classify2;          //分类2
    private TextView classify3;          //分类3
    private EditText wordDetails;        //文字性内容
    private ImageView imageView1;        //图片1
    private ImageView imageView2;        //图片2
    private ImageView imageView3;        //图片3

    private Note noteFromHomePage;        //在首页点击后intent传递过来的对象
    private Note note;                    //修改之后的note对象
    private StringAndBitmap stringAndBitmap;

    private Uri imageUri;
    private String[] imageString={"","",""};     //将显示的图片转化成string进行存储
    private int flagForChoosePhoto;                   //根据具体的值选择显示图片

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_note_details);
        initView();                   //控件实例化
        noteFromHomePage=new Note();
        note=new Note();
        stringAndBitmap=new StringAndBitmap();
        getDataFromHomePage();        //获取从首页传递下来的数据
        putDataToUI();                //将从首页获得的数据显示在UI上
        back.setOnClickListener(this);
        save.setOnClickListener(this);
        title.setOnClickListener(this);
        collect.setOnCheckedChangeListener(this);
        classify1.setOnClickListener(this);
        classify2.setOnClickListener(this);
        classify3.setOnClickListener(this);
        wordDetails.setOnClickListener(this);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);

    }
    private void initView() {
        //控件实例化
        back=(TextView)findViewById(R.id.homePageNoteDetailsBack);
        save=(TextView)findViewById(R.id.homePageNoteDetailsSave);
        title=(EditText)findViewById(R.id.homePageNoteDetailsTitle);
        collect=(CheckBox)findViewById(R.id.homePageNoteDetailsCollect);
        author=(TextView)findViewById(R.id.homePageNoteDetailsAuthor);
        date=(TextView)findViewById(R.id.homePageNoteDetailsDate);
        classify1=(TextView)findViewById(R.id.homePageNoteDetailsClassify1);
        classify2=(TextView)findViewById(R.id.homePageNoteDetailsClassify2);
        classify3=(TextView)findViewById(R.id.homePageNoteDetailsClassify3);
        wordDetails=(EditText) findViewById(R.id.homePageNoteDetailsWordDetails);
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
        collect.setChecked(noteFromHomePage.getCollect());
        author.setText(noteFromHomePage.getAuthor());
        date.setText(noteFromHomePage.getDate());
        classify1.setText(noteFromHomePage.getClassify1());
        classify2.setText(noteFromHomePage.getClassify2());
        classify3.setText(noteFromHomePage.getClassify3());
        wordDetails.setText(noteFromHomePage.getWordDetails());
        imageView1.setImageBitmap(stringAndBitmap.stringToBitmap(noteFromHomePage.getImageDetails1()));
        imageView2.setImageBitmap(stringAndBitmap.stringToBitmap(noteFromHomePage.getImageDetails2()));
        imageView3.setImageBitmap(stringAndBitmap.stringToBitmap(noteFromHomePage.getImageDetails3()));
    }
    @Override
    public void onBackPressed(){
        Intent intent=new Intent( this,Main.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onClick(View view){
        //各种组件的点击函数
        switch (view.getId()){
            case R.id.homePageNoteDetailsBack:{
                handleClickBack();
                break;
            }
            case R.id.homePageNoteDetailsSave:{
                handleClickSave();
                break;
            }
            case R.id.homePageNoteDetailsTitle:{
                handleClickTitle();
                break;
            }
            case R.id.homePageNoteDetailsWordDetails:{
                handleClickWordDetails();
                break;
            }
            case R.id.homePageNoteDetailsImage1:{
                handleClickImage(1);
                break;
            }
            case R.id.homePageNoteDetailsImage2:{
                handleClickImage(2);
                break;
            }
            case R.id.homePageNoteDetailsImage3:{
                handleClickImage(3);
                break;
            }
            case R.id.homePageNoteDetailsClassify1:{
                break;
            }
            case R.id.homePageNoteDetailsClassify2:{
                break;
            }
            case R.id.homePageNoteDetailsClassify3:{
                break;
            }
        }
    }
    @Override
    public void onCheckedChanged(CompoundButton compoundButton,boolean b){
        //复选框等选项处理函数
        switch (compoundButton.getId()){
            case R.id.homePageNoteDetailsCollect:{
                if(collect.isChecked()){
                    note.setCollect(true);
                }
                else{
                    note.setCollect(false);
                }
                break;
            }
        }
    }
    private void handleClickBack(){
        //点击返回按钮具体处理逻辑
        Intent intent=new Intent( this,Main.class);
        startActivity(intent);
        finish();
    }
    private void handleClickSave(){
        //点击保存按钮具体处理逻辑
        Bitmap[] bitmap=new Bitmap[3];
        bitmap[0]=((BitmapDrawable)imageView1.getDrawable()).getBitmap();
        bitmap[1]=((BitmapDrawable)imageView2.getDrawable()).getBitmap();
        bitmap[2]=((BitmapDrawable)imageView3.getDrawable()).getBitmap();
        note.setTitle(title.getText().toString());
        note.setWordDetails(wordDetails.getText().toString());
        note.setImageDetails1(stringAndBitmap.bitmapToString(bitmap[0]));
        note.setImageDetails2(stringAndBitmap.bitmapToString(bitmap[1]));
        note.setImageDetails3(stringAndBitmap.bitmapToString(bitmap[2]));
        note.setClassify1(classify1.getText().toString());
        note.setClassify2(classify2.getText().toString());
        note.setClassify3(classify3.getText().toString());
        note.setDate(date.getText().toString());
        note.setAuthor(author.getText().toString());
    }
    private void handleClickTitle(){
        //点击标题具体处理逻辑
        title.setEnabled(true);
        title.setInputType(InputType.TYPE_CLASS_TEXT);
        title.setSelection(title.getText().length()); // 光标移动最后
    }
    private void handleClickWordDetails(){
        //点击文字描述具体处理逻辑
        wordDetails.setEnabled(true);
        wordDetails.setInputType(InputType.TYPE_CLASS_TEXT);
        wordDetails.setSelection(wordDetails.getText().length());
    }
    private void handleClickImage(int flag){
        //点击图片后处理具体逻辑
        final LayoutInflater inflater=LayoutInflater.from(this);
        final View view= inflater.inflate(R.layout.homepage_note_details_takephoto,null);
        final TextView camera=(TextView)view.findViewById(R.id.homePageNoteDetailsTakePhotoFromCamera);
        final TextView album=(TextView)view.findViewById(R.id.homePageNoteDetailsTakePhotoFromAlbum);
        final TextView cancel=(TextView)view.findViewById(R.id.homePageNoteDetailsTakePhotoCancel);
        final AlertDialog takePhoto=new AlertDialog.Builder(this).create();
        takePhoto.setView(view);
        takePhoto.setCancelable(true);
        takePhoto.show();
        Window window=takePhoto.getWindow();
        window.setGravity(Gravity.BOTTOM);        //将弹出框固定在底部
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = takePhoto.getWindow().getAttributes(); //自适应屏幕宽度
        lp.width = (int)(display.getWidth());
        takePhoto.getWindow().setAttributes(lp);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //使用摄像头上传图片
                File outputImage = new File(getExternalCacheDir(), "homePageAddNotePhoto.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile
                            (HomePageNoteDetails.this, "com.cartoon.tinytips.fileprovider", outputImage);
                } else {
                    imageUri = Uri.fromFile(outputImage);
                }
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, 1);
                takePhoto.cancel();
            }
        });
        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //选取图片进行上传图片
                if(ContextCompat.checkSelfPermission
                        (HomePageNoteDetails.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(HomePageNoteDetails.this, new String[]{ Manifest.permission. WRITE_EXTERNAL_STORAGE }, 1);
                    takePhoto.cancel();
                }
                else{
                    Intent intent = new Intent("android.intent.action.GET_CONTENT");
                    intent.setType("image/*");
                    startActivityForResult(intent,2);
                    takePhoto.cancel();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto.cancel();
            }
        });
        flagForChoosePhoto=flag;
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode) {
            case 1: {
                //使用摄像头进行上传图片回调函数
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        if(flagForChoosePhoto==1){
                            imageView1.setImageBitmap(bitmap);
                            imageString[0]=stringAndBitmap.bitmapToString(bitmap);
                            flagForChoosePhoto=2;
                        }
                        else{
                            if(flagForChoosePhoto==2){
                                imageView2.setImageBitmap(bitmap);
                                imageString[1]=stringAndBitmap.bitmapToString(bitmap);
                                flagForChoosePhoto=3;
                            }
                            else{
                                if(flagForChoosePhoto==3){
                                    imageView3.setImageBitmap(bitmap);
                                    imageString[2]=stringAndBitmap.bitmapToString(bitmap);
                                    flagForChoosePhoto=1;
                                }
                            }
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            case 2:{
                //选择图片进行上传图片回调函数
                if(resultCode==RESULT_OK){
                    if(Build.VERSION.SDK_INT>=19){
                        handleImageOnKitKat(data);
                    }
                    else{
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            }
        }
    }
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        //安卓4.4之后获取本地图片
        String imagePath = null;
        Uri uri = data.getData();
        LogUtil.d("TAG", "handleImageOnKitKat: uri is " + uri);
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            }
            else {
                if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                    Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                    imagePath = getImagePath(contentUri, null);
                }
            }
        }
        else {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                // 如果是content类型的Uri，则使用普通方式处理
                imagePath = getImagePath(uri, null);
            }
            else {
                if ("file".equalsIgnoreCase(uri.getScheme())) {
                    // 如果是file类型的Uri，直接获取图片路径即可
                    imagePath = uri.getPath();
                }
            }
        }
        displayImage(imagePath); // 根据图片路径显示图片
    }
    private void handleImageBeforeKitKat(Intent data) {
        //安卓4.4之前获取本地图片
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }
    private String getImagePath(Uri uri, String selection) {
        // 通过Uri和selection来获取真实的图片路径
        String path = null;

        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    private void displayImage(String imagePath){
        //选择图库中照片显示页面中
        if(imagePath!=null){
            Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
            if(flagForChoosePhoto==1){
                imageView1.setImageBitmap(bitmap);
                imageString[0]=stringAndBitmap.bitmapToString(bitmap);
                flagForChoosePhoto=2;
            }
            else{
                if(flagForChoosePhoto==2){
                    imageView2.setImageBitmap(bitmap);
                    imageString[1]=stringAndBitmap.bitmapToString(bitmap);
                    flagForChoosePhoto=3;
                }
                else{
                    if(flagForChoosePhoto==3){
                        imageView3.setImageBitmap(bitmap);
                        imageString[2]=stringAndBitmap.bitmapToString(bitmap);
                        flagForChoosePhoto=1;
                    }
                }
            }
        }
        else{
            Toast.makeText(HomePageNoteDetails.this,"上传图片失败，请重试!",Toast.LENGTH_SHORT).show();
        }
    }
}
