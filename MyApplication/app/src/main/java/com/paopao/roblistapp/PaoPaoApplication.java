package com.paopao.roblistapp;

import android.app.Application;

/*import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;*/
import com.baidu.mapapi.SDKInitializer;
import com.paopao.roblistapp.model.CachePreferences;

/**
 * Created by Administrator on 2017/4/20.
 */

public class PaoPaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //百度地图初始化
        SDKInitializer.initialize(getApplicationContext());

        CachePreferences.init(this);
        //########### ImageLoader相关初始化  #############
        /*DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)//开启硬盘缓存
                .cacheInMemory(true)//开启内存缓存
                .resetViewBeforeLoading(true)//加载前重置ImageView
                .build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(1024 * 1024 * 4)//设置内存缓存的大小，4M
                .defaultDisplayImageOptions(displayImageOptions)//默认加载选项
                .build();

        ImageLoader.getInstance().init(configuration);*/

    }
}
