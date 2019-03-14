package com.example.testapp.repository.datasource;

import com.example.testapp.repository.bean.OilEntity;
import com.example.testapp.repository.bean.VersionCode;

import io.reactivex.Observable;


/**
 * Author: zqz
 * Data: 2019/3/5 11:14
 * Description:
 */
public interface TestDataSource {

    /**
     * 获取加油偏好
     */
   Observable<OilEntity> getGasPreference(String token);

    /**
     * 获取验证码
     */
    Observable<VersionCode> getCode(String phone);


}
