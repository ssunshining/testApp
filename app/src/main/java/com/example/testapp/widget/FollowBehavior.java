package com.example.testapp.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Author: zqz
 * Data: 2019/3/13 17:11
 * Description:
 */
public class FollowBehavior extends CoordinatorLayout.Behavior<LinearLayout> {//这里的泛型是child的类型，也就是观察者View

    //这个构造方法比写
    public FollowBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override//关心那个View,也就是被观察者，也称依赖者
    public boolean layoutDependsOn(CoordinatorLayout parent, LinearLayout child, View dependency) {
        return dependency instanceof RelativeLayout;
    }

    @Override//当 dependency(Button)变化的时候，可以对child(TextView)进行操作
    public boolean onDependentViewChanged(CoordinatorLayout parent, LinearLayout child, View dependency) {
        child.setX(0);
        child.setY(dependency.getY() - 100);
        return true;
    }
}