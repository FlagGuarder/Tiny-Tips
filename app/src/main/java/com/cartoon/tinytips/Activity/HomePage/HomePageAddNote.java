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
import android.util.Base64;
import android.util.Log;
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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.Activity.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.Util.Util.GetCurrentTime;
import com.cartoon.tinytips.Util.Util.LogUtil;
import com.cartoon.tinytips.Util.Util.StringAndBitmap;
import com.cartoon.tinytips.data.Note;

import java.io.ByteArrayOutputStream;
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
    private TextView confirm;            //确定，储存输入信息

    private Uri imageUri;           //上传的图片的Uri

    private Note note;          //输入的数据全都通过note的set方法存进类
    private int flagForChoosePhoto;                   //根据具体的值选择显示图片
    private String[] imageString={"","",""};     //将显示的图片转化成string进行存储

    private int flagForChooseClassify;        //根据具体的值选择显示在UI的位置
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_addnote);
        initView();
        note=new Note();
        flagForChoosePhoto=1;
        flagForChooseClassify=1;
        back.setOnClickListener(this);
        addPhoto.setOnClickListener(this);
        addClassify.setOnClickListener(this);
        confirm.setOnClickListener(this);
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
        confirm=(TextView)findViewById(R.id.homePageAddNoteConfirm);
    }
    @Override
    public void onClick(View v){
        //各种组件的点击函数
        switch (v.getId()){
            case R.id.homePageAddNoteBack:{
                handleClickBack();
                break;
            }
            case R.id.homePageAddNot1eTakePhoto:{
                handleClickTakePhoto();
                break;
            }
            case R.id.homePageAddNoteAddClassify:{
                handleClickAddClassify();
                break;
            }
            case R.id.homePageAddNoteConfirm:{
                handleClickConfirm();
                break;
            }
        }
    }
    private void handleClickBack(){
        //点击返回按钮后处理具体逻辑
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
    }
    private void handleClickTakePhoto(){
        //点击添加图片按钮后处理具体逻辑
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
    }
    private void handleClickAddClassify(){
        //点击添加分类按钮后处理具体逻辑
        final LayoutInflater inflater=LayoutInflater.from(this);
        final View view= inflater.inflate(R.layout.homepage_addnote_addclassify,null);
        final TextView refresh=(TextView)view.findViewById(R.id.homePageAddNoteAddClassifyRefresh);
        final TextView addClassify1=(TextView) view.findViewById(R.id.homePageAddNoteAddClassify1);
        final TextView addClassify2=(TextView) view.findViewById(R.id.homePageAddNoteAddClassify2);
        final TextView addClassify3=(TextView) view.findViewById(R.id.homePageAddNoteAddClassify3);
        final EditText newClassfy=(EditText)view.findViewById(R.id.homePageAddNoteAddClassifyNewClassify);
        final TextView cancel=(TextView)view.findViewById(R.id.homePageAddNoteAddClassifyCancel);
        final TextView confirm=(TextView)view.findViewById(R.id.homePageAddNoteAddClassifyConfirm);
        final AlertDialog addClassify=new AlertDialog.Builder(this).create();
        addClassify.setView(view);
        addClassify.setCancelable(true);
        addClassify.show();
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        addClassify1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flagForChooseClassify==1){
                    classify1.setText(addClassify1.getText().toString());
                    flagForChooseClassify=2;
                }
                else{
                    if(flagForChooseClassify==2){
                        classify2.setText(addClassify1.getText().toString());
                        flagForChooseClassify=3;
                    }
                    else{
                        if(flagForChooseClassify==3){
                            classify3.setText(addClassify1.getText());
                            flagForChooseClassify=1;
                        }
                    }
                }
            }
        });
        addClassify2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flagForChooseClassify==1){
                    classify1.setText(addClassify2.getText().toString());
                    flagForChooseClassify=2;
                }
                else{
                    if(flagForChooseClassify==2){
                        classify2.setText(addClassify2.getText().toString());
                        flagForChooseClassify=3;
                    }
                    else{
                        if(flagForChooseClassify==3){
                            classify3.setText(addClassify2.getText().toString());
                            flagForChooseClassify=1;
                        }
                    }
                }
            }
        });
        addClassify3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(flagForChooseClassify==1){
                    classify1.setText(addClassify3.getText().toString());
                    flagForChooseClassify=2;
                }
                else{
                    if(flagForChooseClassify==2){
                        classify2.setText(addClassify3.getText().toString());
                        flagForChooseClassify=3;
                    }
                    else{
                        if(flagForChooseClassify==3){
                            classify3.setText(addClassify3.getText().toString());
                            flagForChooseClassify=1;
                        }
                    }
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addClassify.cancel();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flagForChooseClassify==1){
                    classify1.setText(newClassfy.getText().toString());
                    flagForChooseClassify=2;
                }
                else{
                    if(flagForChooseClassify==2){
                        classify2.setText(newClassfy.getText().toString());
                        flagForChooseClassify=3;
                    }
                    else{
                        if(flagForChooseClassify==3){
                            classify3.setText(newClassfy.getText().toString());
                            flagForChooseClassify=1;
                        }
                    }
                }
                Toast.makeText(HomePageAddNote.this,"新建成功！",Toast.LENGTH_SHORT).show();
                addClassify.cancel();
            }
        });
    }
    private void handleClickConfirm(){
        //点击确定按钮后处理具体逻辑
        getDataFromUI();
        Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(HomePageAddNote.this, Main.class);
        startActivity(intent);
        finish();
    }
    private void getDataFromUI(){
        //获取输入的数据，并将它赋值给一个对象
        GetCurrentTime time=new GetCurrentTime();
        note.setTitle(addTitle.getText().toString());
        note.setWordDetails(addWordDetails.getText().toString());
        note.setImageDetails1(imageString[0]);
        note.setImageDetails2(imageString[1]);
        note.setImageDetails3(imageString[2]);
        note.setClassify1(classify1.getText().toString());
        note.setClassify2(classify2.getText().toString());
        note.setClassify3(classify3.getText().toString());
        note.setDate(time.getCurrentTime());
        note.setAuthor("cartoon");
        LogUtil.d("asdf",note.getDate());
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
                StringAndBitmap stringAndBitmap=new StringAndBitmap();
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        if(flagForChoosePhoto==1){
                            photo1.setImageBitmap(bitmap);
                            imageString[0]=stringAndBitmap.bitmapToString(bitmap);
                            flagForChoosePhoto=2;
                        }
                        else{
                            if(flagForChoosePhoto==2){
                                photo2.setImageBitmap(bitmap);
                                imageString[1]=stringAndBitmap.bitmapToString(bitmap);
                                flagForChoosePhoto=3;
                            }
                            else{
                                if(flagForChoosePhoto==3){
                                    photo3.setImageBitmap(bitmap);
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
        StringAndBitmap stringAndBitmap=new StringAndBitmap();
        if(imagePath!=null){
            Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
            if(flagForChoosePhoto==1){
                photo1.setImageBitmap(bitmap);
                imageString[0]=stringAndBitmap.bitmapToString(bitmap);
                flagForChoosePhoto=2;
            }
            else{
                if(flagForChoosePhoto==2){
                    photo2.setImageBitmap(bitmap);
                    imageString[1]=stringAndBitmap.bitmapToString(bitmap);
                    flagForChoosePhoto=3;
                }
                else{
                    if(flagForChoosePhoto==3){
                        photo3.setImageBitmap(bitmap);
                        imageString[2]=stringAndBitmap.bitmapToString(bitmap);
                        flagForChoosePhoto=1;
                    }
                }
            }
        }
        else{
            Toast.makeText(HomePageAddNote.this,"上传图片失败，请重试!",Toast.LENGTH_SHORT).show();
        }
    }
}
