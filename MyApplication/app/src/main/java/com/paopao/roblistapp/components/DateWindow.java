package com.paopao.roblistapp.components;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.paopao.roblistapp.R;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/7/1.
 */

public class DateWindow extends PopupWindow {

    private final DatePicker dp;
    private int year1;
    private int month1;
    private int day1;


    private final Activity activity;


    @SuppressWarnings("all")
    public DateWindow(final Activity activity, final TextView tv) {


        super(activity.getLayoutInflater().inflate(R.layout.popu_date, null),
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        this.activity = activity;
        dp = (DatePicker) getContentView().findViewById(R.id.dp);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            dp.setCalendarViewShown(false);
        }
        else {
            dp.setCalendarViewShown(true);
        }
        Calendar c = Calendar.getInstance();
        year1 = c.get(Calendar.YEAR);
        c.add(Calendar.MONTH, 1);
        month1 = c.get(Calendar.MONTH);
        day1 = c.get(Calendar.DAY_OF_MONTH);
        tv.setText(year1+"-"+month1+"-"+day1);

        dp.init(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(), new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                // TODO Auto-generated method stub
                year1 = year;
                month1 = monthOfYear+1;
                day1 = dayOfMonth;

                StringBuffer lend_time = new StringBuffer();
                lend_time.append(year1);
                lend_time.append("-");
                lend_time.append(month1);
                lend_time.append("-");
                lend_time.append(day1);

                tv.setText(lend_time);
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
        showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        WindowManager.LayoutParams params=activity.getWindow().getAttributes();
        params.alpha=1f;
        activity.getWindow().setAttributes(params);
    }
}
