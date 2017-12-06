package com.cartoon.tinytips.data;

import android.media.Image;

import java.io.Serializable;

/**
 * Created by cartoon on 2017/11/20.
 */

public class Note implements Serializable{
    //笔记类
    private String title;           //标题
    private String  wordDetails;         //文字性内容
    private int imageDetails1;     //图像式内容1
    private int imageDetails2;     //图像式内容2
    private int imageDetails3;     //图像式内容3
    private String classify1;      //笔记的分类1
    private String classify2;      //笔记的分类2
    private String classify3;      //笔记的分类3
    private String date;           //笔记创建的日期

    private boolean isCollect;       //是否收藏
    private String author;           //笔记的发布人

    public String getTitle() {
        return title;
    }

    public String getWordDetails() {
        return wordDetails;
    }

    public int getImageDetails1() {
        return imageDetails1;
    }

    public int getImageDetails2() {
        return imageDetails2;
    }

    public int getImageDetails3() {
        return imageDetails3;
    }

    public String getClassify1() {
        return classify1;
    }

    public String getClassify2() {
        return classify2;
    }

    public String getClassify3() {
        return classify3;
    }

    public String getDate() {
        return date;
    }

    public boolean getCollect() {
        return isCollect;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWordDetails(String wordDetails) {
        this.wordDetails = wordDetails;
    }

    public void setImageDetails1(int imageDetails1) {
        this.imageDetails1 = imageDetails1;
    }

    public void setImageDetails2(int imageDetails2) {
        this.imageDetails2 = imageDetails2;
    }

    public void setImageDetails3(int imageDetails3) {
        this.imageDetails3 = imageDetails3;
    }

    public void setClassify1(String classify1) {
        this.classify1 = classify1;
    }

    public void setClassify2(String classify2) {
        this.classify2 = classify2;
    }

    public void setClassify3(String classify3) {
        this.classify3 = classify3;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
