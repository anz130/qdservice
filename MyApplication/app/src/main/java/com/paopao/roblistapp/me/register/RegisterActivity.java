package com.paopao.roblistapp.me.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.paopao.roblistapp.R;
import com.paopao.roblistapp.base.BaseActivity;
import com.paopao.roblistapp.commons.RegexUtils;
import com.paopao.roblistapp.main.MainActivity;
import com.paopao.roblistapp.me.login.LoginActivity;
import com.paopao.roblistapp.model.CachePreferences;
import com.paopao.roblistapp.model.Result;
import com.paopao.roblistapp.model.UserResult;
import com.paopao.roblistapp.network.ShenLanPaoPaoClient;
import com.paopao.roblistapp.network.UICallback;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/16.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private TextView getCode;
    private EditText registerCode;
    private EditText PassWord;
    private EditText name;
    private EditText phoneNumber;
    private EditText location;
    private EditText remark;
    private String mPhoneNumber;
    private CheckBox checkbox;
    private int requestCode;
    private File[] files;
    private EditText invitationCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //绑定toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView tvToolbar = (TextView) findViewById(R.id.tv_toolbar);
        tvToolbar.setText("注册账号");
        name = (EditText) findViewById(R.id.name);
        phoneNumber = (EditText) findViewById(R.id.phone_number);
        getCode = (TextView) findViewById(R.id.getcode);
        registerCode = ((EditText) findViewById(R.id.register_code));
        PassWord = (EditText) findViewById(R.id.password);
        location = (EditText) findViewById(R.id.location);
        remark = (EditText) findViewById(R.id.remark);
        checkbox = (CheckBox) findViewById(R.id.checkbox);
        invitationCode = (EditText) findViewById(R.id.invitation_code);
        Button button = (Button) findViewById(R.id.button);
        getCode.setTextColor(getResources().getColor(R.color.gray));
        getCode.setEnabled(false);
        button.setOnClickListener(this);

        phoneNumber.addTextChangedListener(new TextWatcher() {
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
                mPhoneNumber = phoneNumber.getText().toString();
                if ((!TextUtils.isEmpty(mPhoneNumber)) && (RegexUtils.verifyPhoneNumber(mPhoneNumber) == RegexUtils.VERIFY_SUCCESS)) {
                    getCode.setTextColor(getResources().getColor(R.color.orange));
                    getCode.setEnabled(true);
                }
            }
        });
        getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
                // TODO: 2017/7/2
                /*Call call = ShenLanPaoPaoClient.getInstance().getCode(mPhoneNumber);
                call.enqueue(new UICallback() {
                    @Override
                    public void onFailureUI(Call call, IOException e) {
                        Toast.makeText(RegisterActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponseUI(Call call, String body) {
                        Result result = new Gson().fromJson(body, Result.class);
                        if (result.getCode()==1){
                            Toast.makeText(RegisterActivity.this,"验证码已发送",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RegisterActivity.this,"验证码发送失败，请重新获取",Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/
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
        String mName = name.getText().toString();
        String mRegisterCode = registerCode.getText().toString();
        String mInvitationCode = invitationCode.getText().toString();
        String mPassWord = PassWord.getText().toString();
        String mLocation = location.getText().toString();
        String mRemark = remark.getText().toString();

        if (TextUtils.isEmpty(mName) || TextUtils.isEmpty(mRegisterCode) || TextUtils.isEmpty(mInvitationCode) || TextUtils.isEmpty(mPassWord) || TextUtils.isEmpty(mLocation)) {
            Toast.makeText(this, "用户名、验证码、邀请码、密码、地址不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (RegexUtils.verifyPassword(mPassWord) != RegexUtils.VERIFY_SUCCESS) {
            Toast.makeText(this, "密码以数字或字母开头，长度在6~18之间，只能包含字符、数字和下划线", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(mPhoneNumber) || !(RegexUtils.verifyPhoneNumber(mPhoneNumber) == RegexUtils.VERIFY_SUCCESS)) {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        } else if (!(RegexUtils.verifyPhoneNumber(mInvitationCode) == RegexUtils.VERIFY_SUCCESS)) {
            Toast.makeText(this, "验证码为已注册用户注册手机号", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkbox.isChecked()) {
            Toast.makeText(this, "请先阅读并确认都易帮条款及协议", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO: 2017/6/19
    /*    Call call = ShenLanPaoPaoClient.getInstance().register(mName, mPhoneNumber, mRegisterCode,mInvitationCode, mLocation, mPassWord, mRemark);
        call.enqueue(new UICallback() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                Toast.makeText(RegisterActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponseUI(Call call, String body) {
                UserResult userResult = new Gson().fromJson(body, UserResult.class);
                if (userResult.getCode()==1){
                    CachePreferences.setUser(userResult.getData());
                    CachePreferences.setIsLogin(true);
                    startActivity(RegisterActivity.this, MainActivity.class);
                    Toast.makeText(RegisterActivity.this, "注册已完成，实名认证通过后可接单", Toast.LENGTH_SHORT).show();
        if (CachePreferences.isLogin()) {
            requestCode = 0;
        } else {
            requestCode = 1;
        }
        Intent intent = new Intent();
        setResult(requestCode, intent);
                    finish();
                }else if (userResult.getCode()==2){
                    Toast.makeText(RegisterActivity.this,userResult.getMessage(),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(RegisterActivity.this,"未知错误",Toast.LENGTH_SHORT).show();
                }
            }
        });*/
        CachePreferences.setIsLogin(true);
        startActivity(this, MainActivity.class);

        Toast.makeText(RegisterActivity.this, "注册已完成，实名认证通过后可接单", Toast.LENGTH_SHORT).show();
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

