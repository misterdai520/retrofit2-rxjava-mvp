package com.petecc.pro.peteccenforcesystem.retorfit;

import com.petecc.pro.peteccenforcesystem.BuildConfig;
import com.petecc.pro.peteccenforcesystem.utils.LogUtil;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：daiyf on 2017/3/16 16:54
 * 邮箱：misterdai@126.com
 * 初始化Retorfit
 */

public class AppCline {
    public static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if(retrofit==null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if(BuildConfig.DEBUG) {
                //Log拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        LogUtil.i(message);
                    }
                });
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置debug模式
                builder.addInterceptor(loggingInterceptor);
            }
            OkHttpClient okHttpClient = builder.build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiStores.baseUrl)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
