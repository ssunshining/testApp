package com.example.testapp.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.testapp.R;

/**
 * Author: zqz
 * Data: 2019/3/13 14:53
 * Description:
 */
public class ScrollTopViewBehavior extends CoordinatorLayout.Behavior<RecyclerView> {
    private RelativeLayout rlLayout;
//    private ObjectAnimator animator;
//    private static IsRefrshListener  isRefrshListener;

    public ScrollTopViewBehavior(Context context, AttributeSet attrs) {//此方法必写
        super(context, attrs);

    }

    @Override//每次被观察者dependency位置发生改变以后，child再发生改变
    public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
        if (dependency != null && dependency.getId() == R.id.rl_layout) {
            rlLayout = (RelativeLayout) dependency;
            return true;
        }
        return false;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull RecyclerView child, @NonNull View dependency) {
        rlLayout.setX(0);
        rlLayout.setY(child.getY()-child.getHeight()-100);
        Log.e("==",child.getY()+"");
//        child.setTranslationY(dependency.getHeight() + dependency.getTranslationY());
//        float progress = 1.0f - Math.abs(dependency.getTranslationY()) / dependency.getHeight();
//        dependency.setAlpha(progress);
        return true;
    }
}