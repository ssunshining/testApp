package com.example.testapp.repository.datasource;

import com.example.testapp.repository.bean.OilEntity;
import com.example.testapp.repository.bean.VersionCode;

import io.reactivex.Observable;


/**
 * Author: zqz
 * Data: 2019/3/5 11:14
 * Description:
 */
public class TestLocalDataSource implements TestDataSource {

    private static TestLocalDataSource INSTANCE;

    public static TestLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestLocalDataSource();
        }
        return INSTANCE;
    }


    @Override
    public Observable<OilEntity> getGasPreference(String token) {
        return null;
    }

    @Override
    public Observable<VersionCode> getCode(String phone) {
        return null;
    }
}
