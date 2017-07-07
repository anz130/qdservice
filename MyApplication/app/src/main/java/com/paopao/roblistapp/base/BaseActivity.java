package com.paopao.roblistapp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.paopao.roblistapp.model.CachePreferences;

/**
 * Created by Administrator on 2017/6/16.
 */

public class BaseActivity extends AppCompatActivity {
    private boolean isLogin = CachePreferences.isLogin();

    public boolean isLogin() {
        return isLogin;
    }

    public void startActivity(Context context, Class zlass){
        Intent intent = new Intent(context, zlass);
        startActivity(intent);
    }
    public void setToast(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
}
