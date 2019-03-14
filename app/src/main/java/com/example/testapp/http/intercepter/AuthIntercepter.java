package com.example.testapp.http.intercepter;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Author: hfyd
 * Datae: 2018/11/5
 * Description:
 */
public class AuthIntercepter implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder();
        RequestBody body = original.body();
        if (body instanceof FormBody) {
            FormBody formBody = ((FormBody) body);
            String sign = getSignFromBody(formBody);
            FormBody.Builder newFormBody = new FormBody.Builder();
            if (!TextUtils.isEmpty(sign)) {
                newFormBody.add("sign", sign);
            }

            for (int i = 0; i < formBody.size(); i++) {
                newFormBody.add(formBody.name(i), formBody.value(i));
            }

            requestBuilder.method(original.method(), newFormBody.build());
        }

//        if (body instanceof MultipartBody) {
//            MultipartBody multipartBody = ((MultipartBody) body);
//            String sign = getSignFromBody(multipartBody);
//            MultipartBody.Builder newMultipartBody = new MultipartBody.Builder();
//            if (!TextUtils.isEmpty(sign)) {
////<<<<<<< HEAD
//////                newMultipartBody.add(RequestCode.SIGN, sign);
////                Log.e("打印sign值",sign);
////=======
////                //newMultipartBody.add(RequestCode.SIGN, sign);
////>>>>>>> 4523de591631dd19ecd9f47d030dee2525361f3a
//                newMultipartBody.addFormDataPart(RequestCode.SIGN, sign);
//            }
//
//            for (int i = 0; i < multipartBody.size(); i++) {
//                Log.e("打印日志name：",multipartBody.part(i).toString());
//                newMultipartBody.addPart(multipartBody.part(i));
//            }
//
//            requestBuilder.method(original.method(), newMultipartBody.build());
//        }

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

    private String getSignFromBody(MultipartBody formBody) {
        try {
            int size = formBody.size();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < size; i++) {
                MultipartBody.Part part = formBody.part(i);

                if (part.body().contentType().type().equals("application")) {
                    Headers headers = part.headers();
                    for (int j = 0; j < headers.names().size(); j++) {
//                        LogUtils.e("getSignFromBody headers======value=" + headers.value(j));
                        String value = headers.value(j);//valueform-data; name="article_type"
                        String replaceValue = "form-data; name=";
                        if (value.contains(replaceValue)) {
                            String key = value.replace(replaceValue, "").replaceAll("\"", "");
//                            LogUtils.e("getSignFromBody MultipartBody======key=" + key);
                            Buffer bodybuffer = new Buffer();

                            part.body().writeTo(bodybuffer);

                            String body = bodybuffer.toString();
                            String valueStr = body.replace("[text=", "");
                            valueStr = valueStr.replace("[size=0", "");
                            valueStr = valueStr.replace("]", "");
                            //valueStr = valueStr.replace("\"","");
                            if (valueStr == null) {
                                valueStr = "";
                            }

                           /* if (!(RequestCode.APP_KEY.equals(key) || RequestCode.TIMESTAMP.equals(key)
                                    || "app_version".equals(key))) {
                                valueStr = "\"" + valueStr + "\"";
                            }*/

                            buffer.append(key + valueStr + "@&");
                            break;
                        }
                    }
                }
            }

            String str[] = buffer.toString().split("@&");//生成数组
            Comparator cmp = Collator.getInstance(java.util.Locale.CHINA);
            Arrays.sort(str, cmp);
            buffer.delete(0, buffer.length());//清空stringBuffer
            for (int i = 0; i < str.length; i++) {
                buffer.append(str[i]);
            }
            return getSign(buffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String getSignFromBody(FormBody formBody) {
        int size = formBody.size();
        StringBuffer budiler = new StringBuffer();
        for (int i = 0; i < size; i++) {
            String name = formBody.name(i);
            String value = formBody.value(i);

            Log.e("forBody Name ：", name);
            Log.e("forBody value ：", value);

            if (value == null) {
                value = "";
            }
            budiler.append(name + value + "@&");
        }
        String str[] = budiler.toString().split("@&");//生成数组
        Comparator cmp = Collator.getInstance(java.util.Locale.CHINA);
        Arrays.sort(str, cmp);
        budiler.delete(0, budiler.length());//清空stringBuffer
        for (int i = 0; i < str.length; i++) {
            budiler.append(str[i]);
        }
        return getSign(budiler.toString());
    }

    private synchronized static String getSign(String str) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("2af9694bc9289276ff430da4c08ff727");
        buffer.append(str);
        buffer.append("2af9694bc9289276ff430da4c08ff727");
        return getMD5(buffer.toString());
    }

    //字符串md5加密
    private synchronized static String getMD5(String str) {
        Log.e("md5加密前的sign", str);
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
}
