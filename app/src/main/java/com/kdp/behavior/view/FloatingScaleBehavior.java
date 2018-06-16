package com.kdp.behavior.view;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kangdongpu on 2017/9/21.
 */

public class FloatingScaleBehavior extends FloatingActionButton.Behavior {

    private boolean isAnimating = false;
    private OnStateChangedListener listener;

    private static final String TAG = FloatingScaleBehavior.class.getSimpleName();

    public FloatingScaleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {

        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }


    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, final FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.e(TAG, "onNestedScroll: dxConsumed: " + dxConsumed + "  dyConsumed: " + dyConsumed);
        Log.e(TAG, "onNestedScroll: dxUnconsumed: " + dxUnconsumed + "  dyUnconsumed: " + dyUnconsumed);

//        if (dyConsumed > 0 && dyUnconsumed == 0) {
//            System.out.print("上滑中...");
//        } else if (dyConsumed < 0 && dyUnconsumed == 0) {
//            System.out.print("下滑中");
//        } else if (dyConsumed == 0 && dyUnconsumed > 0) {
//            System.out.print("上滑到边界还继续上滑...");
//        } else if (dyConsumed == 0 && dyUnconsumed < 0) {
//            System.out.print("下滑到边界还继续下滑...");
//        }


        if (((dyConsumed > 0) || (dyUnconsumed > 0)) && child.getVisibility() != View.INVISIBLE && !isAnimating) {

            hide(child);
            if (listener != null) {
                listener.onChanged(false);
            }

        } else if (((dyConsumed < 0) || (dyUnconsumed < 0)) && child.getVisibility() != View.VISIBLE) {
            show(child);
            if (listener != null) {
                listener.onChanged(true);
            }

        }


    }

    public void setOnStateChangedListener(OnStateChangedListener listener) {
        this.listener = listener;

    }

    /**
     * 隐藏View
     *
     * @param view
     */
    private void hide(final FloatingActionButton view) {
        view.animate().scaleX(0.0f).scaleY(0.0f).alpha(0.0f).setDuration(500).setInterpolator(new LinearOutSlowInInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e(TAG, "onAnimationEnd: end");
                isAnimating = false;
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

                isAnimating = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 显示View
     *
     * @param view
     */
    private void show(FloatingActionButton view) {
        view.setVisibility(View.VISIBLE);
        view.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setDuration(500).setInterpolator(new LinearOutSlowInInterpolator()).setListener(null);
    }


    public interface OnStateChangedListener {

        void onChanged(boolean isShow);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }

    //获取指定View的Behavior
    public static <V extends View> FloatingScaleBehavior from(V view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (!(params instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("这个View不是CoordinatorLayout的子View");
        }
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) params)
                .getBehavior();
        if (!(behavior instanceof FloatingScaleBehavior)) {
            throw new IllegalArgumentException(
                    "这个View的Behaviro不是FloatingScaleBehavior2");
        }
        return (FloatingScaleBehavior) behavior;
    }
}
