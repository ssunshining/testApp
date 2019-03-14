package com.example.testapp.http.intercepter;

import android.util.Log;

import com.example.testapp.commcon.Url;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Author: zqz
 * Data: 2019/3/8 10:59
 * Description:
 */
public class CommonParamInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Log.e("请求", "开始请求");
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        RequestBody body = request.body();


        if (body instanceof FormBody) {
            FormBody formBody = (FormBody) body;
            //基参
            FormBody.Builder newFormBody = new FormBody.Builder();
            newFormBody.add("app3.0_android", "2af9694bc9289276ff430da4c08ff727");
            newFormBody.add("app_version", "4.0.1");
            newFormBody.add("timestamp", time());

            //添加旧参数
            for (int i = 0; i < formBody.size(); i++) {
                newFormBody.add(formBody.name(i), formBody.value(i));
            }
//            newFormBody.add("sign", getSignFromBody(newFormBody.build().);
            requestBuilder.method(request.method(), newFormBody.build());
        }
        Request request1 = requestBuilder.build();
        return chain.proceed(request1);
    }


//    private void addCommonParamsFromGloble(FormBody.Builder newFormBody) {
//        Map<String, String> commonParams = CommonParamsManager.getCommonParams();
//        if (commonParams == null) return;
//        for (Map.Entry<String, String> entry : commonParams.entrySet()) {
//            newFormBody.add(entry.getKey(), entry.getValue());
//        }
//    }

    private String getMD5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String time() {
        return String.valueOf(System.currentTimeMillis());
    }


//    private String getSignFromBody(FormBody formBody) {
//        int size = formBody.size();
//        StringBuffer budiler = new StringBuffer();
//        for (int i = 0; i < size; i++) {
//            String name = formBody.name(i);
//            String value = formBody.value(i);
//
//            Log.e("forBody Name ：", name);
//            Log.e("forBody value ：", value);
//            if (value == null) {
//                value = "";
//            }
//            budiler.append(name + value + "@&");
//        }
//        String str[] = budiler.toString().split("@&");//生成数组
//        Comparator cmp = Collator.getInstance(java.util.Locale.CHINA);
//        Arrays.sort(str, cmp);
//        budiler.delete(0, budiler.length());//清空stringBuffer
//        for (int i = 0; i < str.length; i++) {
//            budiler.append(str[i]);
//        }
//        return getSign(budiler.toString());
//    }



    private synchronized  String getSign(String str) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(Url.app_secret);
        buffer.append(str);
        buffer.append(Url.app_secret);
        return getMD5(buffer.toString());
    }

}
