package com.paopao.roblistapp.components;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.paopao.roblistapp.R;

/**
 * Created by Administrator on 2017/6/30.
 */

public class ShareWindow extends PopupWindow {

    public interface Listener {

        String setTextShareOnce();


        String setTextShareSecond();
    }

    private final Activity activity;

    private final Listener listener;

    @SuppressWarnings("all")
    public ShareWindow(final Activity activity, Listener listener1) {


        super(activity.getLayoutInflater().inflate(R.layout.popu_share, null),
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        this.activity = activity;
        this.listener = listener1;
        TextView shareOnce = (TextView) getContentView().findViewById(R.id.share_once);
        shareOnce.setText(listener.setTextShareOnce());
        TextView shareSecond = (TextView) getContentView().findViewById(R.id.share_second);
        shareSecond.setText(listener.setTextShareSecond());
        getContentView().findViewById(R.id.btn_popu_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setFocusable(true);
        setBackgroundDrawable(new BitmapDrawable());
    }

    public void show() {
        showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        WindowManager.LayoutParams params=activity.getWindow().getAttributes();
        params.alpha=1f;
        activity.getWindow().setAttributes(params);
    }
}
