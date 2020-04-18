package com.wuxiantao.wxt.ui.custom.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Copyright (C), 2018-2019, 都可信有限公司
 * Date: 2019/1/9 0009 16:20 8-19
 * Description: ${DESCRIPTION} ScrollView 嵌套RecyclerView
 * Author: Administrator Shiming-Shi
 */

public class NestRecyclerView extends RecyclerView{


    public NestRecyclerView(Context context) {
        super(context);
    }

    public NestRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayoutManager(new LinearLayoutManager(context));
        setNestedScrollingEnabled(false);
        setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int newHeightSpec = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
        super.onMeasure(widthSpec, newHeightSpec);
    }


}
