package com.paopao.roblistapp.main.mine.wallet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.paopao.roblistapp.R;
import com.paopao.roblistapp.base.BaseActivity;
import com.paopao.roblistapp.components.DateWindow;
import com.paopao.roblistapp.model.User;
import com.paopao.roblistapp.network.ShenLanPaoPaoClient;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/29.
 */

public class WalletActivity extends BaseActivity implements View.OnClickListener {
    private User user;
    private TextView startTime;
    private TextView endTime;
    private DateWindow dateWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        //绑定toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView tvToolbar = (TextView) findViewById(R.id.tv_toolbar);
        tvToolbar.setText("钱包");
        final TextView surplus = (TextView) findViewById(R.id.surplus);
        final TextView profit = (TextView) findViewById(R.id.profit);
        startTime = (TextView) findViewById(R.id.start_time);
        endTime = (TextView) findViewById(R.id.end_time);
        ListView listview = (ListView) findViewById(R.id.listview);
        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);
       /* Call call = ShenLanPaoPaoClient.getInstance().getWallet(user.getName(), startTime.getText().toString(), endTime.getText().toString());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                surplus.setText("");
                profit.setText("总盈利："+""+"元");
                // TODO: 2017/6/29 明细数据适配
            }
        });*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_time:
                if (dateWindow == null||!dateWindow .isShowing()) {
                    dateWindow = null;
                    dateWindow = new DateWindow(this, startTime);
                    WindowManager.LayoutParams params = this.getWindow().getAttributes();
                    params.alpha = 0.7f;
                    this.getWindow().setAttributes(params);
                    dateWindow.show();
                } else if (dateWindow.isShowing()) {
                    dateWindow.dismiss();
                    return;
                }
                break;
            case R.id.end_time:
                if (dateWindow ==null||!dateWindow .isShowing()) {
                    dateWindow = null;
                    dateWindow = new DateWindow(this, endTime);
                    WindowManager.LayoutParams params = this.getWindow().getAttributes();
                    params.alpha = 0.7f;
                    this.getWindow().setAttributes(params);
                    dateWindow.show();
                } else if (dateWindow.isShowing()) {
                    dateWindow.dismiss();
                    return;
                }
                break;
        }
    }
}
