package com.example.testapp.repository;

import com.example.testapp.repository.datasource.TestLocalDataSource;
import com.example.testapp.repository.datasource.TestRemoteDateSource;

/**
 * Author: zqz
 * Data: 2019/3/5 16:03
 * Description:
 */
public class RepositoryProvider {

    public static TestRepository providerTestRepository() {
        return TestRepository.getInstance(TestRemoteDateSource.getInstance(),
                TestLocalDataSource.getInstance());
    }



}
