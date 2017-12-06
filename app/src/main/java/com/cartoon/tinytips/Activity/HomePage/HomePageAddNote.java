package com.cartoon.tinytips.Activity.HomePage;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cartoon.tinytips.Activity.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.Util.LogUtil;
import com.cartoon.tinytips.data.Note;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    private Uri imageUri;           //上传的图片的Uri

    private Note note=new Note();          //输入的数据全都通过note的set方法存进类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_addnote);
        initView();
        back.setOnClickListener(this);
        addPhoto.setOnClickListener(this);
        addClassify.setOnClickListener(this);
        comfirm.setOnClickListener(this);
        isShare.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(isShare.isChecked()){
                    //当分享按钮被选中时
                    note.setCollect(true);
                }
                else{
                    note.setCollect(false);
                }
            }
        });
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
        note.setWordDetails(addWordDetails.getText().toString());
        note.setImageDetails1(photo1.getId());
        note.setImageDetails2(photo2.getId());
        note.setImageDetails3(photo3.getId());
        note.setClassify1(classify1.getText().toString());
        note.setClassify2(classify2.getText().toString());
        note.setClassify3(classify3.getText().toString());
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.homePageAddNoteBack:{
                final AlertDialog.Builder builder=new AlertDialog.Builder(HomePageAddNote.this);
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
                final LayoutInflater inflater=LayoutInflater.from(this);
                final View view= inflater.inflate(R.layout.homepage_addnote_takephoto,null);
                final TextView camera=(TextView)view.findViewById(R.id.homePageAddNoteTakePhotoFromCamera);
                final TextView album=(TextView)view.findViewById(R.id.homePageAddNoteTakePhotoFromAlbum);
                final TextView cancel=(TextView)view.findViewById(R.id.homePageAddNoteTakePhotoCancel);
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
                camera.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        //使用摄像头上传图片
                        File outputImage=new File(getExternalCacheDir(),"homePageAddNotePhoto.jpg");
                        try{
                            if(outputImage.exists()){
                                outputImage.delete();
                            }
                            outputImage.createNewFile();
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                        if(Build.VERSION.SDK_INT>=24){
                            imageUri= FileProvider.getUriForFile
                                    (HomePageAddNote.this,"com.cartoon.tinytips.fileprovider",outputImage);
                        }
                        else {
                            imageUri= Uri.fromFile(outputImage);
                        }
                        Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                        startActivityForResult(intent,1);
                        takePhoto.cancel();
                    }
                });
                album.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //选取图片进行上传图片
                        if(ContextCompat.checkSelfPermission
                                (HomePageAddNote.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(HomePageAddNote.this, new String[]{ Manifest.permission. WRITE_EXTERNAL_STORAGE }, 1);
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
                break;
            }
            case R.id.homePageAddNoteAddClassify:{

                break;
            }
            case R.id.homePageAddNoteComfirm:{
                getDataFromUI();
                Intent intent=new Intent(HomePageAddNote.this, Main.class);
                startActivity(intent);
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
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode) {
            case 1: {
                //使用摄像头进行上传图片回调函数
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        photo1.setImageBitmap(bitmap);

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
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
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
            photo1.setImageBitmap(bitmap);
        }
        else{
            Toast.makeText(HomePageAddNote.this,"上传图片失败，请重试!",Toast.LENGTH_SHORT).show();
        }
    }
}
