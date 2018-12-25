package com.example.testapp.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.WindowManager;

import com.example.testapp.R;

/**
 * Author: zqz
 * Data: 2018/12/24 16:06
 * Description: dialogBuilder 对话框builder
 */
public class DialogBuilder {
    private DialogParams params=new DialogParams();

    public DialogBuilder(Context context) {
        params.setmContext(context);
    }

    public DialogBuilder setGravity(int gravity) {
        params.setGravity(gravity);
        return this;
    }

    public DialogBuilder setContentViewId(int contentViewId) {
        params.setContetViewId(contentViewId);
        return this;
    }

    public DialogBuilder setX(int x) {
        params.setX(x);
        return this;
    }

    public DialogBuilder setY(int y) {
        params.setY(y);
        return this;
    }

    public DialogBuilder setAnimationDirection(DialogAnimation animationDirection) {
        params.setDialogAnimation(animationDirection);
        return this;
    }

    public DialogBuilder setBackCancelable(boolean backCancelable) {
        params.setBackCancelable(backCancelable);
        return this;
    }


    public DialogBuilder setOutsideCancelable(boolean outsideCancelable) {
        params.setOutsideCancelable(outsideCancelable);
        return this;
    }

    public DialogBuilder setOutsideClick(boolean outsideClick) {
        params.setOutSideClick(outsideClick);
        return this;
    }



    public DialogBuilder setTitle(String title){
        params.setTitle(title);
        return this;
    }

    public DialogBuilder setContent(String content){
        params.setContent(content);
        return this;
    }

    public DialogBuilder setLeftButtonText(String leftButtonText){
        params.setLeftButtonText(leftButtonText);
        return this;
    }

    public DialogBuilder setRightButtonText(String rightButtonText){
        params.setRightButtonText(rightButtonText);
        return this;
    }

    public DialogBuilder setHeight(int height){
        params.setHeight(height);
        return this;
    }

    public DialogBuilder setWidth(int width) {
        params.setWidth(width);
        return this;
    }

    public DialogBuilder setLeftVisible(boolean visible){
        params.setLeftVisble(visible);
        return this;
    }
    public DialogBuilder setRightVisible(boolean visible){
        params.setRightVisble(visible);
        return this;
    }

    public DialogBuilder setStyle(int style){
        params.setStyle(style);
        return this;
    }


    /**
     * 此处dialog 是标题内容的dialog，底部有两个按钮的dialog，可以灵活控制显示隐藏
     * @return
     */
    public TipDialog createTip() {

        if (params.getHeight()==0){
            params.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        }

        if (params.getWidth()==0){
            params.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        }

        if (TextUtils.isEmpty(params.getLeftButtonText())){
            params.setLeftButtonText("取消");
        }
        if (TextUtils.isEmpty(params.getRightButtonText())){
            params.setRightButtonText("确定");
        }

        if (params.getStyle()==0){
            params.setStyle(R.style.dialogCommon);
        }


        TipDialog tipDialog= new TipDialog(params.getmContext(), params.getContetViewId(), params.getGravity(),
                params.getX(), params.getY(), params.getDialogAnimation(),
                params.isBackCancelable(), params.isOutsideCancelable(), params.isOutSideClick(),
                params.getHeight(),params.getWidth(),params.getStyle());
        tipDialog.setTitle(params.getTitle());
        tipDialog.setContent(params.getContent());
        tipDialog.setLeftButton(params.getLeftButtonText());
        tipDialog.setRightButton(params.getRightButtonText());
        tipDialog.setVisibaleLeftButton(params.isLeftVisble());
        tipDialog.setVisibaleRightButton(params.isRightVisble());
        tipDialog.show();
        return tipDialog;
    }



}
