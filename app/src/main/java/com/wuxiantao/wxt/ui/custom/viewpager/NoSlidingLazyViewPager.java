package com.wuxiantao.wxt.ui.custom.viewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoSlidingLazyViewPager extends LazyViewPager {
    private boolean slide = false;// false 禁止ViewPager左右滑动。
    public NoSlidingLazyViewPager(@NonNull Context context) {
        super(context);
    }

    public NoSlidingLazyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public void setScrollable(boolean slide) {
        this.slide = slide;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return slide;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return slide;
    }
}
