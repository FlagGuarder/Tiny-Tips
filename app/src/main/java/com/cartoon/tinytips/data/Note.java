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
    private Image imageDetails;     //图像式内容
    private String classify[];      //笔记的分类
    private String date[];           //笔记创建的日期
    private boolean isCollect;       //是否收藏
    private String author;           //笔记的发布人
    public Note
            (String title,String wordDetails,Image imageDetails,String classify[],String date[],boolean isCollect,String author){
        this.title=title;
        this.wordDetails=wordDetails;
        this.imageDetails=imageDetails;
        this.classify=classify;
        this.date=date;
        this.isCollect=isCollect;
        this.author=author;
    }

    public String getTitle() {
        return title;
    }

    public String getWordDetails() {
        return wordDetails;
    }

    public Image getImageDetails() {
        return imageDetails;
    }

    public String[] getClassify() {
        return classify;
    }

    public String[] getDate() {
        return date;
    }

    public boolean isCollect() {
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

    public void setImageDetails(Image imageDetails) {
        this.imageDetails = imageDetails;
    }

    public void setClassify(String[] classify) {
        this.classify = classify;
    }

    public void setDate(String[] date) {
        this.date = date;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
