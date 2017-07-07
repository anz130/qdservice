package com.paopao.roblistapp.main.mine.set;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.paopao.roblistapp.R;
import com.paopao.roblistapp.base.BaseActivity;

/**
 * Created by Administrator on 2017/6/18.
 */

public class SuggestionsActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);
        //绑定toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView tvToolbar = (TextView) findViewById(R.id.tv_toolbar);
        tvToolbar.setText("意见反馈");
        TextView tvRight = (TextView) findViewById(R.id.tv_toolbar_right);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("联系客服");

        EditText etSuggestions = (EditText) findViewById(R.id.et_suggestions);
        Button button = (Button) findViewById(R.id.button);
        tvRight.setOnClickListener(this);
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
            case R.id.tv_toolbar_right:
                if (ContextCompat.checkSelfPermission(SuggestionsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SuggestionsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    //用intent启动拨打电话
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + "10086");
                    intent.setData(data);
                    startActivity(intent);}


                break;
            case R.id.button:
                // TODO: 2017/6/18  获得etSuggestions内容通过点击button发送网络请求
                break;
        }
    }
}
