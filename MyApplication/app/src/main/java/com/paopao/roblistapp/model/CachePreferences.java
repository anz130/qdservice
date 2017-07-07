package com.paopao.roblistapp.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 对用户信息本地保存
 */
public class CachePreferences {
    private static boolean isLogin = false;
    private static boolean isAuthentication = false;
    private static boolean isOnType = false;
    private static final String NAME = CachePreferences.class.getSimpleName();
    private static final String KEY_USER_NAME = "userName";
    private static final String KEY_USER_PWD = "userPwd";
    private static final String KEY_USER_PHONE_NUMBER = "phoneNumber";
    private static final String KEY_USER_HEAD_IMAGE = "userHeadImage";

    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    public static boolean isOnType() {
        return isOnType;
    }

    public static void setIsOnType(boolean isOnType) {
        CachePreferences.isOnType = isOnType;
    }

    private CachePreferences() {
    }

    @SuppressLint("CommitPrefEdits")
    public static void init(Context context) {
        preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static void clearAllData() {
        editor.clear();
        editor.apply();
    }

    public static boolean isLogin() {
        return isLogin;
    }

    public static void setIsLogin(boolean isLogin) {
        CachePreferences.isLogin = isLogin;
    }

    public static boolean isAuthentication() {
        return isAuthentication;
    }

    public static void setIsAuthentication(boolean isAuthentication) {
        CachePreferences.isAuthentication = isAuthentication;
    }

    public static void setUser(User user) {
        editor.putString(KEY_USER_NAME, user.getName());
        editor.putString(KEY_USER_PHONE_NUMBER, user.getPhoneNumber());
        editor.putString(KEY_USER_PWD, user.getPassword());
        editor.putString(KEY_USER_HEAD_IMAGE, user.getHead_image());
        editor.apply();
    }

    public static User getUser() {
        User user = new User();
        user.setName(preferences.getString(KEY_USER_NAME, null));
        user.setPassword(preferences.getString(KEY_USER_PWD, null));
        user.setPhoneNumber(preferences.getString(KEY_USER_PHONE_NUMBER, null));
        user.setHead_image(preferences.getString(KEY_USER_HEAD_IMAGE, null));
        return user;
    }

}
