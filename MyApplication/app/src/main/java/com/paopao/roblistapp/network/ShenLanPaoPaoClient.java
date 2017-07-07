package com.paopao.roblistapp.network;

import com.google.gson.Gson;
import com.paopao.roblistapp.model.CachePreferences;

import java.io.File;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator on 2017/4/17.
 */

public class ShenLanPaoPaoClient {
    private static ShenLanPaoPaoClient mEasyShopClient;
    private OkHttpClient mOkHttpClient;
    private Gson mGson;

    private ShenLanPaoPaoClient() {
        HttpLoggingInterceptor mHttpLoggingInterceptor = new HttpLoggingInterceptor();
        mHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(mHttpLoggingInterceptor)
                .build();
        if (mGson==null){
            mGson = new Gson();
        }
    }

    public static ShenLanPaoPaoClient getInstance() {
        if (mEasyShopClient == null) {
            mEasyShopClient = new ShenLanPaoPaoClient();
        }
        return mEasyShopClient;
    }

    //获取验证码
    public  Call getCode(String phoneNumber){
        RequestBody requestBody = new FormBody.Builder()
                .add("phonenumber", phoneNumber)
                .build();
        Request request = new Request.Builder()
                .url("")
                .post(requestBody)
                .build();
        return mOkHttpClient.newCall(request);
    }

    //注册
    public Call register(String username,String phoneNumber,String invcode,String invitationCode,String location, String password,String remark) {
        RequestBody requestBody = new FormBody.Builder()
                .add("username", username)
                .add("phonenumber", phoneNumber)
                .add("invcode", invcode)
                .add("invitationCode",invitationCode)
                .add("location", location)
                .add("password", password)
                .add("remark",remark)
                .build();
        Request mRequest = new Request.Builder()
                .url("")
                .post(requestBody)
                .build();
        return mOkHttpClient.newCall(mRequest);
    }


    //密码登录
    public Call passwordlogin(String phoneNumber, String password) {
        RequestBody requestBody = new FormBody.Builder()
                .add("phonenumber", phoneNumber)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url(ShenLanApi.BASE_URL+ShenLanApi.LOGIN)
                .post(requestBody)
                .build();
        return mOkHttpClient.newCall(request);
    }



    //修改密码
    public Call changePassword(String phoneNumber, String invcode,String registerPassWord,String notarizePassWord) {
        RequestBody requestBody = new FormBody.Builder()
                .add("phonenumber", phoneNumber)
                .add("invcode", invcode)
                .add("registerPassWord" ,registerPassWord)
                .add("notarizePassWord",notarizePassWord)
                .build();
        Request request = new Request.Builder()
                .url(ShenLanApi.BASE_URL+ShenLanApi.LOGIN)
                .post(requestBody)
                .build();
        return mOkHttpClient.newCall(request);
    }


    //修改昵称
    public Call changeNickName(String nickname){
        RequestBody requestBody = new FormBody.Builder()
                .add("nickname", nickname)
                .build();
        Request request = new Request.Builder()
                .url("")
                .post(requestBody)
                .build();
        return mOkHttpClient.newCall(request);
    }

    //上传头像
    public Call uploadAvatar(File file){
        MultipartBody multipartBody = new MultipartBody.Builder()
                .addFormDataPart("image", file.getName(), RequestBody.create(MediaType.parse("image/png"), file))
                .build();
        Request request = new Request.Builder()
                .url("")
                .post(multipartBody)
                .build();
        return mOkHttpClient.newCall(request);
    }

    /**
     * 改变上下班状态
     * @param master
     * @param isOnType 是否上班状态，true为上班状态，false为下班状态
     * @return
     */
    public Call changeDuty(String master,boolean isOnType){
        RequestBody mRequestBody = new FormBody.Builder()
                .add("master", master)
                .add("dutytType", String.valueOf(isOnType))
                .build();
        Request mRequest = new Request.Builder()
                .url("")
                .post(mRequestBody)
                .build();
        return mOkHttpClient.newCall(mRequest);
    }

    //获取抢单页所有订单
    public Call getIndent(int pageNo,String type){
        RequestBody mRequestBody = new FormBody.Builder()
                .add("pageNo", String.valueOf(pageNo))
                .add("type", type)
                .build();
        Request mRequest = new Request.Builder()
                .url("")
                .post(mRequestBody)
                .build();
        return mOkHttpClient.newCall(mRequest);
    }

    /**
     * 抢单
     * @param master
     * @param uuid
     * @param time 下单时间
     * @param phoneNumber
     * @return
     */
    public Call robIndent(String master,String uuid,String time,String phoneNumber){
        RequestBody requestBody = new FormBody.Builder()
                .add("master",master)
                .add("uuid", uuid)
                .add("time",time)
                .add("phonenumber",phoneNumber)
                .build();
        Request mRequest = new Request.Builder()
                .url("")
                .post(requestBody)
                .build();
        return mOkHttpClient.newCall(mRequest);
    }

    /**
     * 退单
     * @param master 发布者
     * @param uuid 单号（订单的唯一标识）
     * @param state 订单状态（1为正常单，0为问题单）
     * @param remark 跑腿师傅备注（正常单传空字符串）
     * @return
     */
    public Call recedeIndent(String master,String uuid,String state,String remark){
        RequestBody requestBody = new FormBody.Builder()
                .add("master",master)
                .add("uuid", uuid)
                .add("state",state)
                .add("remark",remark)
                .build();
        Request mRequest = new Request.Builder()
                .url("")
                .post(requestBody)
                .build();
        return mOkHttpClient.newCall(mRequest);
    }


    /**
     * 获取个人订单
     * @param pageNo 页码
     * @param master 发布者
     * @param type 订单类型（类型有send、buy、do三种，全部类型传空字符串）
     * @param completeness 订单完成情况（类型有undone（未完成）、finish（已完成）两种，全部类型传空字符串）
     * @return
     */
    public Call getPersonIndent(int pageNo, String master, String type,String completeness){
        RequestBody requestBody = new FormBody.Builder()
                .add("pageNo", String.valueOf(pageNo))
                .add("master", master)
                .add("type", type)
                .add("completeness",completeness)
                .build();
        Request request = new Request.Builder()
                .url("")
                .post(requestBody)
                .build();
        return mOkHttpClient.newCall(request);
    }

    /**
     * 订单详情
     * @param uuid 单号（订单的唯一标识）
     * @return
     */
    public Call indentData(String uuid){
        RequestBody requestBody = new FormBody.Builder()
                .add("uuid", uuid)
                .build();
        Request request = new Request.Builder()
                .url("")
                .post(requestBody)
                .build();
        return mOkHttpClient.newCall(request);
    }


    /**
     * 取件确认
     * @param condition  确认情形（pickup取件、overtime加时、finish完成）
     * @param uuid 单号
     * @param file 图片文件
     * @return
     */
    public Call pickUpNotarize(String condition,String uuid,File file){
        MultipartBody multipartBody = new MultipartBody.Builder()
                .addFormDataPart("condition",condition)
                .addFormDataPart("uuid",uuid)
                .addFormDataPart("image", file.getName(), RequestBody.create(MediaType.parse("image/png"), file))
                .build();
        Request request = new Request.Builder()
                .url("")
                .post(multipartBody)
                .build();
        return mOkHttpClient.newCall(request);
    }

    public Call getWallet(String master,String startTime,String endTime){
        RequestBody requestBody = new FormBody.Builder()
                .add("master", master)
                .add("startTime", startTime)
                .add("endTime", endTime)
                .build();
        Request request = new Request.Builder()
                .url("")
                .post(requestBody)
                .build();
        return mOkHttpClient.newCall(request);
    }

}
