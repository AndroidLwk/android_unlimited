package com.wuxiantao.wxt.ui.custom.radiobutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.wuxiantao.wxt.R;

/**
 * Copyright (C), 2018-2019, 都可信有限公司
 * Date: 2019/4/11 0011 18:21 8-19
 * Description: ${DESCRIPTION} 自定义SiteImgRadioButton 设置图片 大小
 * Author: Administrator Shiming-Shi
 */

public class SiteImgRadioButton  extends android.support.v7.widget.AppCompatRadioButton {

    private int mDrawableSize;// xml文件中设置的大小

    public SiteImgRadioButton(Context context) {
        this(context,null,0);
    }

    public SiteImgRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SiteImgRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context,attrs);
    }


    private void initViews(Context context, AttributeSet attrs){
        Drawable mDrawableLeft = null;
        Drawable mDrawableTop = null;
        Drawable mDrawableRight = null;
        Drawable mDrawableBottom = null;
        this.setClickable(true);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.SiteImgRadioButton);
        int n = mTypedArray.getIndexCount();
        for (int i = 0;i < n;i++){
            int attr = mTypedArray.getIndex(i);
            switch (attr){
                case R.styleable.SiteImgRadioButton_drawableSize:
                    mDrawableSize = mTypedArray.getDimensionPixelSize(R.styleable.SiteImgRadioButton_drawableSize, 50);
                    break;
                case R.styleable.SiteImgRadioButton_drawableTop:
                    mDrawableTop = mTypedArray.getDrawable(attr);
                    break;
                case R.styleable.SiteImgRadioButton_drawableBottom:
                    mDrawableRight = mTypedArray.getDrawable(attr);
                    break;
                case R.styleable.SiteImgRadioButton_drawableRight:
                    mDrawableBottom = mTypedArray.getDrawable(attr);
                    break;
                case R.styleable.SiteImgRadioButton_drawableLeft:
                    mDrawableLeft = mTypedArray.getDrawable(attr);
                    break;
            }
        }
        mTypedArray.recycle();
        setCompoundDrawablesWithIntrinsicBounds(mDrawableLeft,mDrawableTop,mDrawableRight,mDrawableBottom);
    }


    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(@Nullable Drawable left, @Nullable Drawable top, @Nullable Drawable right, @Nullable Drawable bottom) {
        if (left != null) {
            left.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        if (right != null) {
            right.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        if (top != null) {
            top.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        if (bottom != null) {
            bottom.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        setCompoundDrawables(left, top, right, bottom);
    }


}
