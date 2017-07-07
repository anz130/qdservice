package com.paopao.roblistapp.main.indent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.paopao.roblistapp.R;
import com.paopao.roblistapp.base.BaseActivity;
import com.paopao.roblistapp.map.MyMap;
import com.paopao.roblistapp.model.CachePreferences;

/**
 * Created by Administrator on 2017/7/3.
 */

public class IndentItemActivity extends BaseActivity implements View.OnClickListener {
    private String FROMCODE;//标识从哪边跳转过来的

    public static void startIndentItem(Context context, String FROMCODE) {
        Intent intent = new Intent(context, IndentItemActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("FROMCODE",FROMCODE);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indentitem);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView tvToolbar = (TextView) findViewById(R.id.tv_toolbar);
        tvToolbar.setText("订单详情");

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        TextView mapSend = (TextView) findViewById(R.id.map_send);
        TextView mapReceive = (TextView) findViewById(R.id.map_receive);
        mapSend.setOnClickListener(this);
        mapReceive.setOnClickListener(this);
        if (getIntent() != null) {
            FROMCODE = getIntent().getStringExtra("FROMCODE");
            if (FROMCODE .equals("notake") ) {
                button1.setText("退单");
                button2.setText("加价");
                button3.setText("已取货");
            } else if (FROMCODE .equals("undone")) {
                button1.setText("退单");
                button2.setText("加价");
                button3.setText("已完成");
            } else if (FROMCODE .equals("finish") ) {
                button1.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);
            } else {
                button1.setText("抢单");
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);
            }
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.map_send:
                startActivity(this, MyMap.class);
                break;
            case R.id.map_receive:
                startActivity(this,MyMap.class);
                break;
        }
    }
}
