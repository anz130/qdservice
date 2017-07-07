package com.paopao.roblistapp.model;

/**
 * Created by Administrator on 2017/4/17.
 */

import com.google.gson.annotations.SerializedName;

/**
 "username": "xc62",
 "name": "yt59856b15cf394e7b84a7d48447d16098",   环信id
 "uuid": "0F8EC12223174657B2E842076D54C361",    环信uuid
 "password": "123456"
 "other": "/images/0F8EC12223174657B2E842076D54C361/9B61E85244.jpg",
 "nickname": "555",
 */

public class User {
    @SerializedName("username")
    private String name;
    private String phoneNumber;
    private String password;
    @SerializedName("other")
    private String head_image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHead_image() {
        return head_image;
    }

    public void setHead_image(String head_image) {
        this.head_image = head_image;
    }
}
