package com.paopao.roblistapp.me.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paopao.roblistapp.R;
import com.paopao.roblistapp.base.BaseActivity;
import com.paopao.roblistapp.commons.RegexUtils;
import com.paopao.roblistapp.main.MainActivity;
import com.paopao.roblistapp.me.register.RecoverActivity;
import com.paopao.roblistapp.me.register.RegisterActivity;
import com.paopao.roblistapp.model.CachePreferences;
import com.paopao.roblistapp.model.User;
import com.paopao.roblistapp.model.UserResult;
import com.paopao.roblistapp.network.ShenLanPaoPaoClient;
import com.paopao.roblistapp.network.UICallback;

import java.io.IOException;

import okhttp3.Call;


/**
 * Created by Administrator on 2017/6/16.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText loginNamePhone;
    private EditText loginPassword;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((TextView) findViewById(R.id.tv_toolbar)).setText("登录");
        loginNamePhone = (EditText) findViewById(R.id.login_name_phone);
        loginPassword = (EditText) findViewById(R.id.login_password);
        TextView forgetPassword = (TextView) findViewById(R.id.forget_password);
        TextView tvRegisterEntrance = (TextView) findViewById(R.id.tv_register_entrance);
        Button button = (Button) findViewById(R.id.button);
        forgetPassword.setOnClickListener(this);
        tvRegisterEntrance.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forget_password:
                //忘记密码，打开找回密码界面
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RecoverActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_register_entrance:
                //进入注册界面
                Intent intent1 = new Intent();
                intent1.setClass(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent1, 1);
                break;
            case R.id.button:
                String phoneNumber = loginNamePhone.getText().toString();
                String passWord = loginPassword.getText().toString();
                if (TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(passWord)) {
                    Toast.makeText(LoginActivity.this, "手机号、密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (RegexUtils.verifyPhoneNumber(phoneNumber) != RegexUtils.VERIFY_SUCCESS) {
                    Toast.makeText(LoginActivity.this, "手机号码有误", Toast.LENGTH_SHORT).show();
                    return;
                } else if (RegexUtils.verifyPassword(passWord) != RegexUtils.VERIFY_SUCCESS) {
                    Toast.makeText(LoginActivity.this, "密码以数字或字母开头，长度在6~18之间，只能包含字符、数字和下划线", Toast.LENGTH_SHORT).show();
                    return;
                }
                // TODO: 2017/7/2
          /*      Call call = ShenLanPaoPaoClient.getInstance().passwordlogin(phoneNumber, passWord);
                call.enqueue(new UICallback() {
                    @Override
                    public void onFailureUI(Call call, IOException e) {
                        Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponseUI(Call call, String body) {
                        UserResult userResult = new Gson().fromJson(body, UserResult.class);
                        if (userResult.getCode()==1){
                            CachePreferences.setUser(userResult.getData());
                            CachePreferences.setIsLogin(true);
                            startActivity(LoginActivity.this, MainActivity.class);
                            finish();
                        }else if (userResult.getCode()==2){
                            Toast.makeText(LoginActivity.this,userResult.getMessage(),Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(LoginActivity.this,"未知错误",Toast.LENGTH_SHORT).show();
                        }

                    }
                });*/
                CachePreferences.setIsLogin(true);
                startActivity(LoginActivity.this, MainActivity.class);
                finish();
                break;

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 0:
                finish();
                break;
            case 1:

                break;
            default:
                break;
        }
    }

}
