package com.example.testapp.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.example.testapp.R;

/**
 * Author: zqz
 * Data: 2019/3/13 14:53
 * Description:
 */
public class ScrollImageViewBehavior extends CoordinatorLayout.Behavior<RecyclerView> {
    private RelativeLayout rlLayout;
    private ObjectAnimator animator;
    private static IsRefrshListener  isRefrshListener;

    public ScrollImageViewBehavior(Context context, AttributeSet attrs) {//此方法必写
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
    public boolean onDependentViewChanged(CoordinatorLayout parent, RecyclerView child, View dependency) {
        child.setTranslationY(dependency.getHeight() + dependency.getTranslationY());
        float progress = 1.0f - Math.abs(dependency.getTranslationY()) / dependency.getHeight();
        dependency.setAlpha(progress);
        return true;
    }

    //用户按下手指时触发，如果返回 true 则表示“要处理这次滑动”，
    // 如果返回 false 则表示不管这次的滑动，该怎么滑就怎么滑，后面的一系列回调函数就不会被调用了。
    // 它有一个关键的参数nestedScrollAxes，就是滑动方向，表明了用户是垂直滑动还是水平滑动，本例子只需考虑垂直滑动，因此判断滑动方向为垂直时就处理这次滑动，其他不管。
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    //当接受要处理本次滑动后，这个回调被调用，我们可以做一些准备工作，比如让之前的滑动动画结束
    @Override
    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, RecyclerView child, View directTargetChild, View target, int nestedScrollAxes) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
        if (animator != null) {
            animator.cancel();
        }
    }

    //当即将被滑动时调用，在这里你可以做一些处理。值得注意的是，这个方法有一个参数 int[] consumed，
    // 你可以修改这个数组来表示你到底处理掉了多少像素。假设用户滑动了 100px，你做了 90px 的位移，
    // 那么就需要把 consumed[1] 改成 90（下标 0、1 分别对应 x、y 轴），这样 NSC 就能知道，然后继续处理剩下的 10px。
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        if (dy < 0) {//向下滑动
            return;
        }
        //向上滑动
        float newTranslateY = rlLayout.getTranslationY() - dy;
        float minHeaderTranslate = -rlLayout.getHeight();

        if (newTranslateY > minHeaderTranslate) {

            rlLayout.setTranslationY(newTranslateY);
            consumed[1] = dy;
        }


    }

    //上一个方法结束后，NSC 处理剩下的距离。比如上面还剩 10px，这里 NSC 滚动 2px 后发现已经到头了，
    // 于是 NSC 结束其滚动，调用该方法，并将 NSC 处理剩下的像素数作为参数（dxUnconsumed、dyUnconsumed）传过来，
    // 这里传过来的就是 8px。参数中还会有 NSC 处理过的像素数（dxConsumed、dyConsumed）。这个方法主要处理一些越界后的滚动。
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyUnconsumed > 0) {
            return;
        }
        float newTranslateY = rlLayout.getTranslationY() - dyUnconsumed;
        final float maxHeaderTranslate = 0;
        if (newTranslateY < maxHeaderTranslate) {
            rlLayout.setTranslationY(newTranslateY);
        }
    }

    //用户松开手指并且会发生惯性滚动之前调用。参数提供了速度信息，我们这里可以根据速度，决定最终的状态是展开还是折叠，并且启动滑动动画。
    // 通过返回值我们可以通知 NSC 是否自己还要进行滑动滚动，一般情况如果面板处于中间态，我们就不让 NSC 接着滚了，因为我们还要用动画把面板完全展开或者完全折叠。
    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, float velocityX, float velocityY) {
        //velocityY>0向上滑动
//        if (rlLayout.getTranslationY() > -rlLayout.getHeight() / 2 && rlLayout.getTranslationY() < 0) {
//            if (velocityY > 0) {
//                return true;
//            }
//            animator = ObjectAnimator.ofFloat(rlLayout, "translationY", rlLayout.getTranslationY(), 0).setDuration(200);
//            animator.start();
//        } else if ((rlLayout.getTranslationY() < -rlLayout.getHeight() / 2 || rlLayout.getTranslationY() == -rlLayout.getHeight() / 2) && rlLayout.getTranslationY() > -rlLayout.getHeight()) {
//            animator = ObjectAnimator.ofFloat(rlLayout, "translationY", rlLayout.getTranslationY(), -rlLayout.getHeight()).setDuration(200);
//            animator.start();
//        }
        return false;
    }

    //一切滚动停止后调用，如果不会发生惯性滚动，fling 相关方法不会调用，直接执行到这里。这里我们做一些清理工作，当然有时也要处理中间态问题。
    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
//        if (rlLayout.getTranslationY() > -rlLayout.getHeight() / 2 && rlLayout.getTranslationY() < 0) {
//            animator = ObjectAnimator.ofFloat(rlLayout, "translationY", rlLayout.getTranslationY(), 0).setDuration(200);
//            animator.start();
//            if (isRefrshListener!=null){
//                isRefrshListener.isResh(true);
//            }
//        } else if ((rlLayout.getTranslationY() < -rlLayout.getHeight() / 2 || rlLayout.getTranslationY() == -rlLayout.getHeight() / 2) && rlLayout.getTranslationY() > -rlLayout.getHeight()) {
//            animator = ObjectAnimator.ofFloat(rlLayout, "translationY", rlLayout.getTranslationY(), -rlLayout.getHeight()).setDuration(200);
//            animator.start();
//            if (isRefrshListener!=null){
//                isRefrshListener.isResh(false);
//            }
//        }

    }




    public interface IsRefrshListener {
        void isResh(boolean isRefresh);
    }

    public static void setIsRefrshListener(IsRefrshListener sRefrshListener) {
        isRefrshListener = sRefrshListener;
    }
}