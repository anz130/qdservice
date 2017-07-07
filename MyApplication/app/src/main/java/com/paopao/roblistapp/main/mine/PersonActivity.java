package com.paopao.roblistapp.main.mine;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.paopao.roblistapp.R;
import com.paopao.roblistapp.base.BaseActivity;
import com.paopao.roblistapp.components.AvatarLoadOptions;
import com.paopao.roblistapp.components.PicWindow;
import com.paopao.roblistapp.components.ProgressDialogFragment;
import com.paopao.roblistapp.components.ShareWindow;
import com.paopao.roblistapp.components.TransportationWindow;
import com.paopao.roblistapp.main.mine.person.AuthenticationActivity;
import com.paopao.roblistapp.main.mine.person.NickNameActivity;
import com.paopao.roblistapp.main.mine.person.PartnerActivity;
import com.paopao.roblistapp.model.CachePreferences;
import com.paopao.roblistapp.network.ShenLanApi;
import com.paopao.roblistapp.network.ShenLanPaoPaoClient;
import com.paopao.roblistapp.network.UICallback;

import org.hybridsquad.android.library.CropHandler;
import org.hybridsquad.android.library.CropHelper;
import org.hybridsquad.android.library.CropParams;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;

import static com.baidu.location.d.j.v;

/**
 * Created by Administrator on 2017/6/28.
 */

public class PersonActivity extends BaseActivity implements View.OnClickListener {
    private PicWindow picWindow;
    private TransportationWindow transportationWindow;
    private ShareWindow shareWindow;
    private ProgressDialogFragment progressDialogFragment;
    private ImageView headPortrait;
    private TextView tvTransportatiuon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persion);
        //绑定toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView tvToolbar = (TextView) findViewById(R.id.tv_toolbar);
        tvToolbar.setText("个人信息");

        headPortrait = (ImageView) findViewById(R.id.iv_head_portrait);
        TextView userName = (TextView) findViewById(R.id.username);
        TextView integral = (TextView) findViewById(R.id.integral);
        TextView alternative = (TextView) findViewById(R.id.alternative);
        RelativeLayout nickname = (RelativeLayout) findViewById(R.id.nickname);
        TextView tvPhoneNumber = (TextView) findViewById(R.id.tv_phone_number);
        RelativeLayout distribution = (RelativeLayout) findViewById(R.id.distribution);
        RelativeLayout transportation = (RelativeLayout) findViewById(R.id.transportation);
        tvTransportatiuon = ((TextView) findViewById(R.id.tv_transportation));
        RelativeLayout shareNumber = (RelativeLayout) findViewById(R.id.share_number);
        RelativeLayout partner = (RelativeLayout) findViewById(R.id.partner);
        RelativeLayout authentication = (RelativeLayout) findViewById(R.id.authentication);
        userName.setText("张三" + "(ID:" + "252155" + ")");
        integral.setText("100");
        alternative.setText("1525");
        tvPhoneNumber.setText("手机号：" + "");
        // TODO: 2017/6/28 网络请求数据
        headPortrait.setOnClickListener(this);
        nickname.setOnClickListener(this);
        distribution.setOnClickListener(this);
        transportation.setOnClickListener(this);
        shareNumber.setOnClickListener(this);
        partner.setOnClickListener(this);
        authentication.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_head_portrait://头像
                if (picWindow == null) {
                    picWindow = new PicWindow(this, picListener);
                    WindowManager.LayoutParams params = this.getWindow().getAttributes();
                    params.alpha = 0.7f;
                    this.getWindow().setAttributes(params);
                    picWindow.show();
                } else if (picWindow.isShowing()) {
                    picWindow.dismiss();
                    return;
                } else {
                    WindowManager.LayoutParams params = this.getWindow().getAttributes();
                    params.alpha = 0.7f;
                    this.getWindow().setAttributes(params);
                    picWindow.show();
                }
                break;
            case R.id.nickname://昵称
                startActivity(PersonActivity.this, NickNameActivity.class);
                break;
            case R.id.distribution://配送城市
                // TODO: 2017/6/29
                break;
            case R.id.transportation: //交通工具
                if (transportationWindow == null) {
                    transportationWindow = new TransportationWindow(this, transportationListener);
                    WindowManager.LayoutParams params = this.getWindow().getAttributes();
                    params.alpha = 0.7f;
                    this.getWindow().setAttributes(params);
                    transportationWindow.show();
                } else if (transportationWindow.isShowing()) {
                    transportationWindow.dismiss();
                    return;
                } else {
                    WindowManager.LayoutParams params = this.getWindow().getAttributes();
                    params.alpha = 0.7f;
                    this.getWindow().setAttributes(params);
                    transportationWindow.show();
                }
                break;
            case R.id.share_number://分享人数
                if (shareWindow == null) {
                    shareWindow = new ShareWindow(this, shareListener);
                    WindowManager.LayoutParams params = this.getWindow().getAttributes();
                    params.alpha = 0.7f;
                    this.getWindow().setAttributes(params);
                    shareWindow.show();
                } else if (shareWindow.isShowing()) {
                    shareWindow.dismiss();
                    return;
                } else {
                    WindowManager.LayoutParams params = this.getWindow().getAttributes();
                    params.alpha = 0.7f;
                    this.getWindow().setAttributes(params);
                    shareWindow.show();
                }
                break;
            case R.id.partner://自由合伙人
                startActivity(this, PartnerActivity.class);
                break;
            case R.id.authentication://实名认证
                if (CachePreferences.isAuthentication()){
                    Toast.makeText(this,"您已通过实名认证，快去接单吧",Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(PersonActivity.this, AuthenticationActivity.class);
                }
                break;

        }
    }

    //头像上传相关
    private PicWindow.Listener picListener = new PicWindow.Listener() {
        @Override
        public void toGallery() {
            CropHelper.clearCachedCropFile(mCropHandler.getCropParams().uri);
            Intent intent = CropHelper.buildCropFromGalleryIntent(mCropHandler.getCropParams());
            startActivityForResult(intent, CropHelper.REQUEST_CROP);
        }

        @Override
        public void toCamera() {
            CropHelper.clearCachedCropFile(mCropHandler.getCropParams().uri);
            Intent intent = CropHelper.buildCaptureIntent(mCropHandler.getCropParams().uri);
            startActivityForResult(intent, CropHelper.REQUEST_CAMERA);

        }
    };
    private CropHandler mCropHandler = new CropHandler() {
        @Override
        public void onPhotoCropped(Uri uri) {
            File mFile = new File(uri.getPath());
            updataAvatar(mFile);
        }

        @Override
        public void onCropCancel() {

        }

        @Override
        public void onCropFailed(String message) {

        }

        @Override
        public CropParams getCropParams() {
            CropParams mCropParams = new CropParams();
            mCropParams.aspectX = 400;
            mCropParams.aspectY = 400;

            return mCropParams;
        }

        @Override
        public Activity getContext() {
            return PersonActivity.this;
        }
    };
    public void updataAvatar(File file) {
        showPrb();
        Call call = ShenLanPaoPaoClient.getInstance().uploadAvatar(file);
        call.enqueue(new UICallback() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                hidePrb();
                showMsg(e.getMessage());
            }

            @Override
            public void onResponseUI(Call call, String body) {
                hidePrb();
                /*UserResult mUserResult = new Gson().fromJson(body, UserResult.class);
                if (mUserResult.getCode() == 1) {
                    CachePreferences.setUser(mUserResult.getUser());
                    updataAvatar1(mUserResult.getUser().getHead_image());
                } else if (mUserResult.getCode() == 2) {
                    showMsg(mUserResult.getMessage());
                    return;
                } else {
                    showMsg("未知错误");
                }*/

            }
        });
    }
    public void updataAvatar1(String url) {
        ImageLoader.getInstance().displayImage(ShenLanApi.IMAGE_URL + url, headPortrait, AvatarLoadOptions.build());
    }


    //交通工具选择监听
    private TransportationWindow.Listener transportationListener = new TransportationWindow.Listener() {
        @Override
        public void electrombile() {
            tvTransportatiuon.setText("交通工具：电动车");
        }

        @Override
        public void motorcycle() {
            tvTransportatiuon.setText("交通工具：摩托车");
        }

        @Override
        public void automobile() {
            tvTransportatiuon.setText("交通工具：汽车");
        }
    };


    //分享人数监听
    private ShareWindow.Listener shareListener = new ShareWindow.Listener() {
        @Override
        public String setTextShareOnce() {
            // TODO: 2017/6/30 网络请求分享人数
            return "一级分享："+"140"+"人";
        }

        @Override
        public String setTextShareSecond() {
            return "二级分享："+"350"+"人";
        }
    };


    //网络请求相关
    public void showPrb() {
        if (progressDialogFragment == null)
            progressDialogFragment = new ProgressDialogFragment();
        if (progressDialogFragment.isVisible()) return;
        progressDialogFragment.show(getSupportFragmentManager(), "progress_dialog_fragment");
    }
    public void hidePrb() {
        progressDialogFragment.dismiss();

    }
    public void showMsg(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


}
