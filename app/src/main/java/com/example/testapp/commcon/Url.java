package com.example.testapp.commcon;


import com.example.testapp.BuildConfig;

public class Url {
    public static String app_key = "app3.0_android";
    public static String app_secret = "2af9694bc9289276ff430da4c08ff727";

    public static String SITE = null;//测试
    public static String BASEURL = null;


    static {
        if (BuildConfig.DEBUG) {
            SITE = "test-acs.czb365.com/services/v3";//测试
            BASEURL = "https://" + SITE;
        } else {
            SITE = "acs.czb365.com/services/v3";//线上
            BASEURL = "https://" + SITE;
        }
    }


}
