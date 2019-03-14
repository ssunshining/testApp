package com.example.testapp.repository;

import com.example.testapp.commcon.UrlConstant;
import com.example.testapp.repository.bean.OilEntity;
import com.example.testapp.repository.bean.VersionCode;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Author: zqz
 * Data: 2019/3/5 11:15
 * Description:
 */
public interface TestService {

    /**
     * 获取未登录加油用户偏好
     */
    @POST(UrlConstant.GET_GAS_PREFER)
    @FormUrlEncoded
    Observable<OilEntity> getUnLoginOilPreference(@Field("token") String token);


    /**
     * 获取未登录加油用户偏好
     */
    @POST(UrlConstant.GET_CODE_BY_TOKEN)
    @FormUrlEncoded
    Observable<VersionCode> getCode(@Field("phone") String phone);
}
