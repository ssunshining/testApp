package com.example.testapp.repository;

import android.support.annotation.NonNull;

import com.example.testapp.repository.bean.OilEntity;
import com.example.testapp.repository.bean.VersionCode;
import com.example.testapp.repository.datasource.TestDataSource;

import io.reactivex.Observable;


/**
 * Author: zqz
 * Data: 2019/3/6 18:37
 * Description:
 */
public class TestRepository implements TestDataSource {

    private static TestRepository INSTANCE;
    private TestDataSource mTestLocalDataSource;
    private TestDataSource mTestRemoteDateSource;


    private TestRepository(@NonNull TestDataSource remoteDataSource,
                           @NonNull TestDataSource localDataSource) {
        this.mTestRemoteDateSource = remoteDataSource;
        this.mTestLocalDataSource = localDataSource;
    }


    public static TestRepository getInstance(@NonNull TestDataSource remoteDataSource,
                                             @NonNull TestDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TestRepository(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }


    @Override
    public Observable<OilEntity> getGasPreference(String token) {
        return mTestRemoteDateSource.getGasPreference(token);
    }

    @Override
    public Observable<VersionCode> getCode(String phone) {
        return mTestRemoteDateSource.getCode(phone);
    }
}
