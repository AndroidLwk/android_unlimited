package com.wuxiantao.wxt.ui.custom.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Copyright (C), 2018-2019, 都可信有限公司
 * Date: 2019/4/28 0028 12:43 8-19
 * Description: ${DESCRIPTION} 自动换行布局组件
 * Author: Administrator Shiming-Shi
 */

public class FixGridLayout extends ViewGroup{


    public FixGridLayout(Context context) {
        this(context,null);
    }

    public FixGridLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FixGridLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    /**
     * 控制子控件的换行
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layoutHorizontal();
    }


    private void layoutHorizontal() {
        final int count = getChildCount();
        final int lineWidth = getMeasuredWidth() - getPaddingLeft()
                - getPaddingRight();
        int paddingTop = getPaddingTop();
        int childTop = 0;
        int childLeft = getPaddingLeft();

        int availableLineWidth = lineWidth;
        int maxLineHight = 0;

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child == null) {
                continue;
            } else if (child.getVisibility() != GONE) {
                final int childWidth = child.getMeasuredWidth();
                final int childHeight = child.getMeasuredHeight();

                if (availableLineWidth < childWidth) {
                    availableLineWidth = lineWidth;
                    paddingTop = paddingTop + maxLineHight;
                    childLeft = getPaddingLeft();
                    maxLineHight = 0;
                }
                childTop = paddingTop;
                setChildFrame(child, childLeft, childTop, childWidth,
                        childHeight);
                childLeft += childWidth;
                availableLineWidth = availableLineWidth - childWidth;
                maxLineHight = Math.max(maxLineHight, childHeight);
            }
        }
    }


    /**
     * 计算控件及子控件所占区域
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
        if (heightMode == MeasureSpec.AT_MOST||heightMode == MeasureSpec.UNSPECIFIED) {
            final int width = MeasureSpec.getSize(widthMeasureSpec);
            super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(
                    getDesiredHeight(width), MeasureSpec.EXACTLY));
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }


    private int getDesiredHeight(int width) {
        final int lineWidth = width - getPaddingLeft() - getPaddingRight();
        int availableLineWidth = lineWidth;
        int totalHeight = getPaddingTop() + getPaddingBottom();
        int lineHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            final int childWidth = child.getMeasuredWidth();
            final int childHeight = child.getMeasuredHeight();
            if (availableLineWidth < childWidth) {
                availableLineWidth = lineWidth;
                totalHeight = totalHeight + lineHeight;
                lineHeight = 0;
            }
            availableLineWidth = availableLineWidth - childWidth;
            lineHeight = Math.max(childHeight, lineHeight);
        }
        totalHeight = totalHeight + lineHeight;
        return totalHeight;
    }

    private void setChildFrame(View child, int left, int top, int width,
                               int height) {
        child.layout(left, top, left + width, top + height);
    }




}
