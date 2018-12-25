package com.example.testapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import com.example.testapp.R;

/**
 * Author: zqz
 * Data: 2018/12/24 10:06
 * Description:
 */
public class BaseDialog<T extends ViewDataBinding> extends Dialog {

    /**
     * binding ,用于子类继承
     */
    protected T mBinding;

    protected Context mContext;


    /**
     * 创建dialog的方法
     *
     * @param context            上下文
     * @param gravity            对齐方式
     * @param animationDirection 动画效果 {@link }
     * @param backCancelable     返回键是否可以取消
     * @param outsideCancelable  外部点击是否可以取消
     */
    protected BaseDialog(Context context, int contentLayoutId, int gravity, int x, int y,
                         DialogAnimation animationDirection, boolean backCancelable,
                         boolean outsideCancelable, boolean outsideClick, int height, int width,int style) {
        super(context,style);
        this.mContext = context;
        init(gravity, x, y, animationDirection, backCancelable, outsideCancelable, outsideClick, height, width);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), contentLayoutId, null, false);
        setContentView(mBinding.getRoot());
    }


    private void init(int gravity, int x, int y, DialogAnimation animationDirection, boolean backCancelable,
                      boolean outsideCancelable, boolean outsideClick, int height, int width) {

        if (outsideClick) {//外部可响应点击事件
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
            window.setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                    WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);
        }

        this.setCancelable(backCancelable);
        this.setCanceledOnTouchOutside(outsideCancelable);

        Window dialogWindow = this.getWindow();
        if (animationDirection == DialogAnimation.VERTICLE) {
            dialogWindow.setWindowAnimations(R.style.DialogVerticleWindowAnim);
        } else if (animationDirection == DialogAnimation.HORIZONTAL) {
            dialogWindow.setWindowAnimations(R.style.DialogRightHorizontalWindowAnim);
        } else if (animationDirection == DialogAnimation.GROW) {
            dialogWindow.setWindowAnimations(R.style.DialogGrowWindowAnim);
        }
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.height = height;
        layoutParams.width = width;
        layoutParams.gravity = gravity;
        layoutParams.y = y;
        layoutParams.x = x;
        initLayout(layoutParams);
        dialogWindow.setAttributes(layoutParams);
    }


    /**
     * 设置宽 ,高 ,位置, 距离底部的方法, 可以由子类进行重写
     *
     * @param layoutParams
     */
    protected void initLayout(WindowManager.LayoutParams layoutParams) {

    }


}
