package com.example.testapp.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.testapp.R;
import com.example.testapp.databinding.ActivityMainBinding;
import com.example.testapp.dialog.DialogBuilder;
import com.example.testapp.repository.RepositoryProvider;
import com.example.testapp.repository.bean.OilEntity;
import com.example.testapp.repository.bean.VersionCode;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        /**
         * 调起dialog
         */
        mBinding.idDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogBuilder(MainActivity.this)
                        .setTitle("测试")
                        .setContent("内容")
                        .setLeftVisible(false)
                        .setContentViewId(R.layout.dialog_tip)
                        .createTip()
                        .setDialogInterface(new com.example.testapp.dialog.DialogInterface.onClickConfirmDialog() {
                            @Override
                            public void onClickCofirm(Dialog dialog) {
                                dialog.dismiss();
                            }
                        });
            }
        });



        mBinding.requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable<OilEntity> observable = RepositoryProvider.providerTestRepository().getGasPreference("");

                observable.subscribe(new Observer<OilEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("onSubscribe:", "");
                    }

                    @Override
                    public void onNext(OilEntity oilEntity) {
                        Log.e("onNext:", oilEntity.getCode() + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError:", "");

                    }

                    @Override
                    public void onComplete() {
                        Log.e("onComplete:", "");

                    }
                });
            }
        });

        mBinding.getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable<VersionCode> observable = RepositoryProvider.providerTestRepository().getCode("15810019431");

                observable.subscribe(new Observer<VersionCode>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("onSubscribe:", "");
                    }

                    @Override
                    public void onNext(VersionCode oilEntity) {
                        Log.e("onNext:", oilEntity.getCode() + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError:", "");

                    }

                    @Override
                    public void onComplete() {
                        Log.e("onComplete:", "");

                    }
                });
            }
        });

        mBinding.refreshBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                intentJump(RefreshActivity.class);
            }
        });

    }

}