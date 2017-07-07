package com.paopao.roblistapp.components;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.paopao.roblistapp.R;

import static com.paopao.roblistapp.R.layout.popu_pic;


/**
 * 自定义PopupWindow,用于图片的选择
 */
public class PicWindow extends PopupWindow {

    public interface Listener {
        /**
         * 来自相册
         */
        void toGallery();

        /**
         * 来自相机
         */
        void toCamera();
    }

    private final Activity activity;

    private final Listener listener;

    @SuppressWarnings("all")
    public PicWindow(final Activity activity, Listener listener1) {


        super(activity.getLayoutInflater().inflate(R.layout.popu_pic, null),
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        this.activity = activity;
        this.listener = listener1;
        getContentView().findViewById(R.id.btn_popu_album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.toGallery();
                dismiss();
            }
        });
        getContentView().findViewById(R.id.btn_popu_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.toCamera();
                dismiss();
            }
        });
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

