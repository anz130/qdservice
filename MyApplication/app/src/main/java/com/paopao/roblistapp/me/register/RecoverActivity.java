package com.paopao.roblistapp.me.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.paopao.roblistapp.R;
import com.paopao.roblistapp.base.BaseActivity;
import com.paopao.roblistapp.commons.RegexUtils;
import com.paopao.roblistapp.main.MainActivity;
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

public class RecoverActivity extends BaseActivity implements View.OnClickListener {
    private EditText namePhone;
    private TextView getCode;
    private EditText mRegisterCode;
    private EditText mRegisterPassWord;
    private EditText mNotarizePassWord;
    private String phoneNumber;
    private int requestCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover);
        //绑定toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView tvToolbar = (TextView) findViewById(R.id.tv_toolbar);
        tvToolbar.setText("修改密码");

        namePhone = (EditText) findViewById(R.id.login_name_phone);
        getCode = (TextView) findViewById(R.id.getcode);
        mRegisterCode = ((EditText) findViewById(R.id.login_code));
        mRegisterPassWord = (EditText) findViewById(R.id.new_password);
        mNotarizePassWord = ((EditText) findViewById(R.id.notarize_password));
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        getCode.setEnabled(false);
        namePhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getCode.setTextColor(getResources().getColor(R.color.gray));
                getCode.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
                phoneNumber = namePhone.getText().toString();
                if ((!TextUtils.isEmpty(phoneNumber)) && (RegexUtils.verifyPhoneNumber(phoneNumber) == RegexUtils.VERIFY_SUCCESS)) {
                    getCode.setTextColor(getResources().getColor(R.color.orange));
                    getCode.setEnabled(true);
                }
            }
        });
        getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/6/30 发送验证码网络请求
                Toast.makeText(RecoverActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            if (CachePreferences.isLogin()) {
                requestCode = 0;
            } else {
                requestCode = 1;
            }
        Intent intent = new Intent();
        setResult(requestCode, intent);
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String invcode = mRegisterCode.getText().toString();
        String registerPassWord = mRegisterPassWord.getText().toString();
        String notarizePassWord = mNotarizePassWord.getText().toString();
        if (TextUtils.isEmpty(invcode) || TextUtils.isEmpty(registerPassWord) || TextUtils.isEmpty(notarizePassWord)) {
            Toast.makeText(this, "验证码、密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(phoneNumber) || !(RegexUtils.verifyPhoneNumber(phoneNumber) == RegexUtils.VERIFY_SUCCESS)) {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        } else if (RegexUtils.verifyPassword(registerPassWord) != RegexUtils.VERIFY_SUCCESS) {
            Toast.makeText(this, "密码以数字或字母开头，长度在6~18之间，只能包含字符、数字和下划线", Toast.LENGTH_SHORT).show();
            return;
        } else if (!TextUtils.equals(registerPassWord, notarizePassWord)) {
            Toast.makeText(this, "两次输入的密码不同！", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO: 2017/6/19
      /*  Call call = ShenLanPaoPaoClient.getInstance().changePassword(phoneNumber, invcode, registerPassWord, notarizePassWord);
        call.enqueue(new UICallback() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                Toast.makeText(RecoverActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponseUI(Call call, String body) {
                UserResult userResult = new Gson().fromJson(body, UserResult.class);
                if (userResult.getCode() == 1){
                    Toast.makeText(RecoverActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
                    CachePreferences.setUser(userResult.getData());
                    CachePreferences.setIsLogin(true);
                    startActivity(RecoverActivity.this, MainActivity.class);
                    if (CachePreferences.isLogin()) {
                        requestCode = 0;
                    } else {
                        requestCode = 1;
                    }
                    Intent intent = new Intent();
                    setResult(requestCode, intent);
                    finish();
                }
            }
        });*/
        CachePreferences.setIsLogin(true);
        startActivity(this, MainActivity.class);
        if (CachePreferences.isLogin()) {
            requestCode = 0;
        } else {
            requestCode = 1;
        }
        Intent intent = new Intent();
        setResult(requestCode, intent);
        finish();
    }
}
