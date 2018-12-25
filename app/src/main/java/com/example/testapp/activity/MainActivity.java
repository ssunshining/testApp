package com.example.testapp.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import com.example.testapp.R;
import com.example.testapp.databinding.ActivityMainBinding;
import com.example.testapp.dialog.DialogBuilder;

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
    }

}