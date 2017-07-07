package com.paopao.roblistapp.network;

/**
 * Created by Administrator on 2017/4/17.
 */

public class ShenLanApi {
    //服务器地址：
    static final String BASE_URL = "http://10.0.4.15:8080/";

    //图片的根路径
    public static final String IMAGE_URL = "";

    //注册接口
    static final String REGISTER = "";

    //登录接口
    static final String LOGIN = "login";

    //更新接口（更新昵称，更新头像）
    static final String UPDATA = "UserWeb?method=update";

    //获取商品
    static final String GETGOODS = "GoodsServlet?method=getAll";

    //获取商品详情
    static final String DETAIL = "GoodsServlet?method=view";

    //删除商品
    static final String DELETE = "GoodsServlet?method=delete";

    //上传商品
    static final String UPLOADGOODS = "GoodsServlet?method=add";

    //获取好友列表
    static final String GET_NAMES = "UserWeb?method=getNames";

    //查找好友
    static final String GET_USER = "UserWeb?method=getUsers";
}
