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

public class TransportationWindow extends PopupWindow {

    public interface Listener {
        //电动车
        void electrombile();
        //摩托车
        void motorcycle();
        //汽车
        void automobile();
    }

    private final Activity activity;

    private final Listener listener;

    @SuppressWarnings("all")
    public TransportationWindow(Activity activity, Listener listener1) {


        super(activity.getLayoutInflater().inflate(R.layout.popu_transportation, null),
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        this.activity = activity;
        this.listener = listener1;

        getContentView().findViewById(R.id.electrombile).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.electrombile();
                        dismiss();
                    }
                });

        getContentView().findViewById(R.id.motorcycle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.motorcycle();
                dismiss();
            }
        });
        getContentView().findViewById(R.id.automobile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.automobile();
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
