package com.example.testapp.http;

import com.example.testapp.commcon.Url;
import com.example.testapp.http.intercepter.CommonParamInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: zqz
 * Data: 2018/12/25 15:22
 * Description:
 */
public class RetrofitClient {

    private static Retrofit defaultRxClient;

    public static Retrofit getDefaultRxClient() {
        synchronized (RetrofitClient.class){
            if (null == defaultRxClient) {
                defaultRxClient = new Retrofit.Builder()
                        .baseUrl(Url.BASEURL+"/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(getOkHttpClient())
                        .build();
            }
        }
        return defaultRxClient;
    }





    private static OkHttpClient getOkHttpClient() {
       /* HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(new InputStream[]{MyApplication.application.getResources().openRawResource(R.raw.server)}
                , null, null);*/
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new CommonParamInterceptor());
//        //builder.addInterceptor(new UrlDecodeInterceptor());
//        builder.addInterceptor(new AuthIntercepter());
//        builder.addInterceptor(new HttpLoggingInterceptor());
        builder.sslSocketFactory(SSLSocketClient.getSSLSocketFactory());
        builder.hostnameVerifier(SSLSocketClient.getHostnameVerifier());
        builder.connectTimeout(20, TimeUnit.SECONDS);//设置超时时间
        builder.readTimeout(20, TimeUnit.SECONDS);//设置读取超时时间
        builder.writeTimeout(20, TimeUnit.SECONDS);//设置写入超时时间

        return builder.build();
    }

}
