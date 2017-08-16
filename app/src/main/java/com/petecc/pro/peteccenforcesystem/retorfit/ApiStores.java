package com.petecc.pro.peteccenforcesystem.retorfit;

import com.petecc.pro.peteccenforcesystem.model.LoginResult;
import com.petecc.pro.peteccenforcesystem.model.RegisterOrChangePassOneModel;
import com.petecc.pro.peteccenforcesystem.model.UserInfoResult;

import java.util.HashMap;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 作者：daiyf on 2017/3/16 16:50
 * 邮箱：misterdai@126.com
 */

public interface ApiStores {

    //baseUrl
    String baseUrl = "https://m.yun.ilulala.cn/";

    /**
     * 登录接口
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("login.php")
    Observable<LoginResult> login(@FieldMap HashMap<String, String> map);

    /**
     * 注册的第一步
     * @param phoneNum
     * @return
     */
    @FormUrlEncoded
    @POST("register.php")
    Observable<RegisterOrChangePassOneModel> registerOne(@FieldMap HashMap<String, String> map);


    /**
     *  注册第二步|修改密码第二步, 设置密码
     * @param userid
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("set_password.php")
    Observable<LoginResult> setPassWord(@Field("userid") String userid,@Field("password") String password);


    /**
     * 修改密码第一步, 验证用户信息
     * @param phoneNum
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST("validate_user.php")
    Observable<RegisterOrChangePassOneModel> validateUser(@Field("mobile") String phoneNum, @Field("note") String code);

}
