package com.example.testapp.dialog;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.example.testapp.databinding.DialogTipBinding;

/**
 * Author: zqz
 * Data: 2018/12/24 09:43
 * Description:通用信息dialog
 */
public class TipDialog extends BaseDialog<DialogTipBinding> {


    private DialogInterface.onCLickCancleDialog cancleDialog;
    private DialogInterface.onClickConfirmDialog confirmDialog; 

    protected TipDialog(Context context, int contentId, int gravity, int x, int y, DialogAnimation animationDirection,
                        boolean backCancelable, boolean outsideCancelable, boolean outsideClick, int height, int width,int style) {
        super(context, contentId, gravity, x, y, animationDirection, backCancelable,
                outsideCancelable, outsideClick, height, width,style);
        initView();
    }

    public void initView() {
        mBinding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancleDialog != null) {
                    cancleDialog.onCancle(TipDialog.this);
                }
            }
        });

        mBinding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (confirmDialog != null) {
                    confirmDialog.onClickCofirm(TipDialog.this);
                }
            }
        });

    }


    public void setTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            mBinding.title.setVisibility(View.GONE);
        } else {
            mBinding.title.setVisibility(View.VISIBLE);
            mBinding.title.setText(title);
        }
    }

    public void setContent(String content) {
        if (TextUtils.isEmpty(content)) {
            mBinding.content.setVisibility(View.GONE);
        } else {
            mBinding.content.setVisibility(View.VISIBLE);
            mBinding.content.setText(content);
        }

    }

    public void setLeftButton(String leftButtonText) {
        mBinding.cancel.setText(leftButtonText);

    }

    public void setRightButton(String rightButtonText) {
        mBinding.confirm.setText(rightButtonText);
    }

    public void setVisibaleLeftButton(boolean visbale) {
        if (visbale) {
            mBinding.cancel.setVisibility(View.VISIBLE);
        } else {
            mBinding.cancel.setVisibility(View.GONE);
        }
    }

    public void setVisibaleRightButton(boolean visbale) {
        if (visbale) {
            mBinding.confirm.setVisibility(View.VISIBLE);
        } else {
            mBinding.confirm.setVisibility(View.GONE);
        }
    }

    public void setDialogInterface(DialogInterface.onCLickCancleDialog cancelDialog) {
        this.cancleDialog = cancleDialog;
    }

    public void setDialogInterface(DialogInterface.onClickConfirmDialog confirmDialog) {
        this.confirmDialog = confirmDialog;
    }

}
