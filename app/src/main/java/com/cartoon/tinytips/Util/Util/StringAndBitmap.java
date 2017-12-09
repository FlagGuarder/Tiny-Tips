package com.cartoon.tinytips.Util.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by cartoon on 2017/12/9.
 */

public class StringAndBitmap {
    //图片与String之间的转换，便于将图片存储在数据库中
    private Bitmap bitmap;
    private String string;
    public Bitmap stringToBitmap(String string){
        if(!string.equals("")){
            byte[] bytes= Base64.decode(string,Base64.DEFAULT);
            bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            return bitmap;
        }
        else {
            return null;
        }
    }
    public String bitmapToString(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();// 转为byte数组
        string=Base64.encodeToString(bytes,Base64.DEFAULT);
        return string;
    }
}
