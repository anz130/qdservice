package com.paopao.roblistapp.logo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.paopao.roblistapp.R;
import com.paopao.roblistapp.base.BaseActivity;
import com.paopao.roblistapp.main.MainActivity;
import com.paopao.roblistapp.me.login.LoginActivity;

/**
 * Created by Administrator on 2017/6/17.
 */

public class LogoActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               if (isLogin()){
                   startActivity(LogoActivity.this,MainActivity.class);
               }else {
               startActivity(LogoActivity.this,LoginActivity.class);}
               finish();
           }
       },2000);
    }
}
