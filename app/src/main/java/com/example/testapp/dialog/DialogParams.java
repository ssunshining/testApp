package com.example.testapp.dialog;

import android.content.Context;
import android.view.Gravity;

/**
 * Author: zqz
 * Data: 2018/12/24 15:08
 * Description:
 */
public class DialogParams {
    private int contetViewId;
    private Context mContext;
    private int gravity = Gravity.CENTER;
    private int x = 0;
    private int y = 0;
    private DialogAnimation dialogAnimation = DialogAnimation.GROW;
    private boolean backCancelable = true;
    private boolean outSideClick = false;
    private boolean outsideCancelable = true;

    private String title;
    private String content;

    private String leftButtonText;
    private String rightButtonText;

    private boolean leftVisble=true;
    private boolean rightVisble=true;

    private int height;
    private int width;

    private int style;

    public int getContetViewId() {
        return contetViewId;
    }

    public void setContetViewId(int contetViewId) {
        this.contetViewId = contetViewId;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public DialogAnimation getDialogAnimation() {
        return dialogAnimation;
    }

    public void setDialogAnimation(DialogAnimation dialogAnimation) {
        this.dialogAnimation = dialogAnimation;
    }

    public boolean isBackCancelable() {
        return backCancelable;
    }

    public void setBackCancelable(boolean backCancelable) {
        this.backCancelable = backCancelable;
    }

    public boolean isOutSideClick() {
        return outSideClick;
    }

    public void setOutSideClick(boolean outSideClick) {
        this.outSideClick = outSideClick;
    }

    public boolean isOutsideCancelable() {
        return outsideCancelable;
    }

    public void setOutsideCancelable(boolean outsideCancelable) {
        this.outsideCancelable = outsideCancelable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLeftButtonText() {
        return leftButtonText;
    }

    public void setLeftButtonText(String leftButtonText) {
        this.leftButtonText = leftButtonText;
    }

    public String getRightButtonText() {
        return rightButtonText;
    }

    public void setRightButtonText(String rightButtonText) {
        this.rightButtonText = rightButtonText;
    }

    public boolean isLeftVisble() {
        return leftVisble;
    }

    public void setLeftVisble(boolean leftVisble) {
        this.leftVisble = leftVisble;
    }

    public boolean isRightVisble() {
        return rightVisble;
    }

    public void setRightVisble(boolean rightVisble) {
        this.rightVisble = rightVisble;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }
}
