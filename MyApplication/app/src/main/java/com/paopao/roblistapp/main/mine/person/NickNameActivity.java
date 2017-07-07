package com.paopao.roblistapp.main.mine.person;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.paopao.roblistapp.R;
import com.paopao.roblistapp.base.BaseActivity;
import com.paopao.roblistapp.network.ShenLanPaoPaoClient;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/29.
 */

public class NickNameActivity extends BaseActivity {

    private EditText etNickName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);
        etNickName = (EditText) findViewById(R.id.et_nickname);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView tvToolbar = (TextView) findViewById(R.id.tv_toolbar);
        tvToolbar.setText("修改昵称");
        TextView tvToolbarRight = (TextView) findViewById(R.id.tv_toolbar_right);
        tvToolbarRight.setText("保存");
        tvToolbarRight.setVisibility(View.VISIBLE);
        tvToolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname = etNickName.getText().toString();
                Call call = ShenLanPaoPaoClient.getInstance().changeNickName(nickname);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        showMsg(e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        // TODO: 2017/6/29 成功的回调 ，nickname添加到数据库
                    }
                });
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    public void showMsg(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
