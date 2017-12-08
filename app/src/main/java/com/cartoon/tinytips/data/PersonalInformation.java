package com.cartoon.tinytips.data;

import android.media.Image;

import java.io.Serializable;

/**
 * Created by cartoon on 2017/11/20.
 */

public class PersonalInformation implements Serializable{
    //个人信息类

    private String account;     //账号
    private String password;    //密码
    private String nickName;    //昵称
    private Image headPortrait;  //头像
    private String school;       //高校
    private String sex;         //性别
    private String personalDecription;    //个人描述

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return nickName;
    }

    public Image getHeadPortrait() {
        return headPortrait;
    }

    public String getSchool() {
        return school;
    }

    public String getSex() {
        return sex;
    }

    public String getPersonalDecription() {
        return personalDecription;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setHeadPortrait(Image headPortrait) {
        this.headPortrait = headPortrait;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPersonalDecription(String personalDecription) {
        this.personalDecription = personalDecription;
    }
}
