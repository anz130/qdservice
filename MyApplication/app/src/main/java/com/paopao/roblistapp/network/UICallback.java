package com.paopao.roblistapp.network;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/4/18.
 */

public abstract class UICallback implements Callback {
    Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onFailure(final Call call, final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onFailureUI(call, e);
            }
        });

    }

    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
        if (!response.isSuccessful()) {
            throw new IOException("error code"+response.code());
        }
        final String json = response.body().string();
        Log.e("eeeeeee",json);
        mHandler.post(new Runnable() {
            @Override
            public void run() {

                onResponseUI(call, json);
            }
        });


    }

    public abstract void onFailureUI(Call call, IOException e);

    public abstract void onResponseUI(Call call, String body);
}
