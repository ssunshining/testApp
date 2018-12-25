package com.example.testapp.dialog;

import android.app.Dialog;

/**
 * Author: zqz
 * Data: 2018/12/24 15:39
 * Description:
 */
public interface DialogInterface {

    public interface onCLickCancleDialog{
        public void onCancle(Dialog dialog);
    }

   public interface onClickConfirmDialog{
        public void onClickCofirm(Dialog dialog);
    }

}
