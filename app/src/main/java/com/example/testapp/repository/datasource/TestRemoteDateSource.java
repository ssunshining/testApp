package com.example.testapp.repository.datasource;

import com.example.testapp.http.RetrofitClient;
import com.example.testapp.repository.TestService;
import com.example.testapp.repository.bean.OilEntity;
import com.example.testapp.repository.bean.VersionCode;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: zqz
 * Data: 2019/3/5 15:10
 * Description:
 */
public class TestRemoteDateSource implements TestDataSource {

    private static TestRemoteDateSource INSTANCE;

    public static TestRemoteDateSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestRemoteDateSource();
        }
        return INSTANCE;
    }


    @Override
    public Observable<OilEntity> getGasPreference(String token) {
        return RetrofitClient.getDefaultRxClient()
                .create(TestService.class)
                .getUnLoginOilPreference(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<VersionCode> getCode(String phone) {
        return RetrofitClient.getDefaultRxClient()
                .create(TestService.class)
                .getCode(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
