package com.phone1000.groupproject.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ${USER_NAME} on 2016/9/8.
 */
public class HttpServer {
    //创建一个获取数据的retrofit框架
    private static HttpUtil httpUtil;
    private static final  String BASE_URL ="http://www.dgtle.com";
    public  static HttpUtil create(){
        if(httpUtil == null){
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).
                    addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
                 httpUtil = retrofit.create(HttpUtil.class);
        }
        return  httpUtil;
    }
}
