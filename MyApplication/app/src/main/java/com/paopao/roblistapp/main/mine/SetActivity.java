package com.paopao.roblistapp.main.mine;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.paopao.roblistapp.R;
import com.paopao.roblistapp.base.BaseActivity;
import com.paopao.roblistapp.main.mine.set.AboutUsActivity;
import com.paopao.roblistapp.main.mine.set.SuggestionsActivity;
import com.paopao.roblistapp.me.login.LoginActivity;
import com.paopao.roblistapp.me.register.RecoverActivity;
import com.paopao.roblistapp.model.CachePreferences;

/**
 * Created by Administrator on 2017/6/18.
 */

public class SetActivity extends BaseActivity implements View.OnClickListener {


    private Switch soundSwitch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        //绑定toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView tvToolbar = (TextView) findViewById(R.id.tv_toolbar);
        tvToolbar.setText("设置");

        RelativeLayout changePassword = (RelativeLayout) findViewById(R.id.change_password);
        soundSwitch = (Switch) findViewById(R.id.iv_sound_switch);
        RelativeLayout versionsInformation = (RelativeLayout) findViewById(R.id.versions_information);
        TextView versions = (TextView) findViewById(R.id.versions);
        RelativeLayout aboutUs = (RelativeLayout) findViewById(R.id.about_us);
        RelativeLayout suggestions = (RelativeLayout) findViewById(R.id.suggestions);
        Button button = (Button) findViewById(R.id.button);
        versions.setText(getVersion());
        changePassword.setOnClickListener(this);
        soundSwitch.setOnClickListener(this);
        versionsInformation.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        suggestions.setOnClickListener(this);
        button.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_password://修改密码
                startActivity(this, RecoverActivity.class);
                break;
            case R.id.iv_sound_switch://音乐开关
                if (soundSwitch.isChecked()){
                    soundSwitch.setChecked(false);
                    // TODO: 2017/6/30 关闭音乐服务
                }else {
                    soundSwitch.setChecked(true);
                }
                break;
            case R.id.versions_information://更新版本
                // TODO: 2017/6/18 点击检查版本更新
                break;
            case R.id.about_us://关于我们
                startActivity(this, AboutUsActivity.class);
                break;
            case R.id.suggestions://意见反馈
                startActivity(this, SuggestionsActivity.class);
                break;
            case R.id.button://退出登录
                CachePreferences.clearAllData();
                CachePreferences.setIsLogin(false);
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
        }
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
