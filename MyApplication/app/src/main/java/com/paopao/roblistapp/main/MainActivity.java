package com.paopao.roblistapp.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.paopao.roblistapp.R;
import com.paopao.roblistapp.base.BaseActivity;
import com.paopao.roblistapp.model.CachePreferences;
import com.paopao.roblistapp.model.DutyType;
import com.paopao.roblistapp.network.ShenLanPaoPaoClient;
import com.paopao.roblistapp.network.UICallback;

import java.io.IOException;

import okhttp3.Call;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    private boolean isExit = false;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    private TextView[] textViews;
    private TextView tvToolBar;

    @Override
    public void onBackPressed() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            handler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
        }
    }
    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView tvToolBarRight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvToolBar = (TextView) findViewById(R.id.tv_toolbar);
        tvToolBarRight = (TextView) findViewById(R.id.tv_toolbar_right);
        tvToolBarRight.setText("下班");
        tvToolBarRight.setVisibility(View.VISIBLE);
        textView = (TextView) findViewById(R.id.grab_single);
        textView1 = (TextView) findViewById(R.id.my_indent);
        textView2 = (TextView) findViewById(R.id.share);
        textView3 = (TextView) findViewById(R.id.mine);
        textViews = new TextView[]{textView,textView1,textView2,textView3};
        textView.setOnClickListener(this);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        tvToolBarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvToolBarRight.getText().toString()=="上班"){
                    tvToolBarRight.setText("下班");
                }else if (tvToolBarRight.getText().toString()=="下班"){
                    tvToolBarRight.setText("上班");
                }
                // TODO: 2017/7/5
               /* Call call = ShenLanPaoPaoClient.getInstance().changeDuty(CachePreferences.getUser().getName(), !CachePreferences.isOnType());
                call.enqueue(new UICallback() {
                    @Override
                    public void onFailureUI(Call call, IOException e) {
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponseUI(Call call, String body) {
                        DutyType dutyType = new Gson().fromJson(body, DutyType.class);
                        if (dutyType.getCode() == 1){
                            CachePreferences.setIsOnType(dutyType.isOnType());
                            if (CachePreferences.isOnType()){
                                tvToolBarRight.setText("上班");
                            }else {
                                tvToolBarRight.setText("下班");
                            }
                        }
                    }
                });*/
            }
        });
        tvToolBar.setText(textView.getText());
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        GrabSingleFragment fragment1 = new GrabSingleFragment();
        transaction.add(R.id.layout, fragment1);
        transaction.commit();
        textViews[0].setBackgroundColor(getResources().getColor(R.color.orange));
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        for (int i = 0; i < textViews.length; i++) {
            textViews[i].setBackgroundColor(getResources().getColor(R.color.purple));
        }
        switch (v.getId()){
            case R.id.grab_single:
                tvToolBar.setText(textView.getText());
                GrabSingleFragment fragment1 = new GrabSingleFragment();
                transaction.replace(R.id.layout, fragment1);
                transaction.commitAllowingStateLoss();
                textViews[0].setBackgroundColor(getResources().getColor(R.color.orange));
                break;
            case R.id.my_indent:
                tvToolBar.setText(textView1.getText());
                MyIndentFragment fragment2 = new MyIndentFragment();
                transaction.replace(R.id.layout, fragment2);
                transaction.commitAllowingStateLoss();
                textViews[1].setBackgroundColor(getResources().getColor(R.color.orange));
                break;
            case R.id.share:
                tvToolBar.setText(textView2.getText());
                ShareFragment fragment3 = new ShareFragment();
                transaction.replace(R.id.layout, fragment3);
                transaction.commitAllowingStateLoss();
                textViews[2].setBackgroundColor(getResources().getColor(R.color.orange));
                break;
            case R.id.mine:
                tvToolBar.setText(textView3.getText());
                MineFragment fragment4 = new MineFragment();
                transaction.replace(R.id.layout, fragment4);
                transaction.commitAllowingStateLoss();
                textViews[3].setBackgroundColor(getResources().getColor(R.color.orange));
                break;
        }
    }
}