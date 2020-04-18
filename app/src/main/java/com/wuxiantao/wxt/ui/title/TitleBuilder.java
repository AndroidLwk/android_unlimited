package com.wuxiantao.wxt.ui.title;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.utils.StatusBarUtil;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/11 0011 11:05 8-19
 * Description: ${DESCRIPTION} 封装标题栏 统一风格
 * Author: Administrator Shiming-Shi
 */

public class TitleBuilder {

    /**
     * title栏根布局
     */
    private View  titleView;
    private ImageView base_title_left_img;
    private TextView  base_title_left_tv;
    private RelativeLayout base_title_left_layout;
    private TextView  base_title_center_tv;
    private ImageView base_title_center_img;
    private ImageView base_title_right_img;
    private TextView  base_title_right_tv;
    private RelativeLayout base_title_right_layout;
    private RelativeLayout base_title_layout;

    /**
     * 初始化控件
     * @param act
     */
    public TitleBuilder(Activity act){
        StatusBarUtil.setStatusBarColor(act,act.getResources().getColor(R.color.white));
        StatusBarUtil.setStatusBarDarkTheme(act,true);
        titleView = act.findViewById(R.id.base_title);
        base_title_left_img = act.findViewById(R.id.base_title_left_img);
        base_title_left_tv = act.findViewById(R.id.base_title_left_tv);
        base_title_left_layout = act.findViewById(R.id.base_title_left_layout);
        base_title_center_tv = act.findViewById(R.id.base_title_center_tv);
        base_title_center_img = act.findViewById(R.id.base_title_center_img);
        base_title_right_img = act.findViewById(R.id.base_title_right_img);
        base_title_right_tv = act.findViewById(R.id.base_title_right_tv);
        base_title_right_layout = act.findViewById(R.id.base_title_right_layout);
        base_title_layout = act.findViewById(R.id.base_title_layout);
    }

    /**
     * 设置背景
     * @param color
     * @return
     */
    @SuppressLint("ResourceAsColor")
    public TitleBuilder setBackGroundColor(@ColorInt int color){
        if (color != 0){
            base_title_layout.setBackgroundColor(color);
        }
        return this;
    }

    /**
     * 设置背景
     * @param color
     * @return
     */
    @SuppressLint("ResourceAsColor")
    public TitleBuilder setBackGroundColor(String color){
        if (color != null && !TextUtils.isEmpty(color)){
            base_title_layout.setBackgroundColor(Color.parseColor(color));
        }
        return this;
    }



    /**
     * 设置背景
     * @param drawableRes
     * @return
     */
    @SuppressLint({"ResourceAsColor", "ResourceType"})
    public TitleBuilder setBackgroundResource(@DrawableRes int drawableRes){
        if (drawableRes != 0){
            base_title_layout.setBackgroundResource(drawableRes);
        }
        return this;
    }

    /**
     * 设置左边图片
     * @param resid
     * @return
     */
    public TitleBuilder setLeftImageRes(int resid){
        base_title_left_img.setVisibility(resid > 0 ? View.VISIBLE : View.GONE);
        base_title_left_img.setImageResource(resid);
        return this;
    }

    /**
     * 设置左边文字按钮
     * @param text
     * @return
     */
    public TitleBuilder setLeftText(String text){
        base_title_left_tv.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        base_title_left_tv.setText(text);
        return this;
    }

    /**
     * 设置左边图片或文字的点击事件
     * @param listener
     * @return
     */
    public TitleBuilder  setLeftTextOrImageListener(View.OnClickListener listener){
        if (base_title_left_img.getVisibility() == View.VISIBLE || base_title_left_tv.getVisibility() == View.VISIBLE){
            base_title_left_layout.setOnClickListener(listener);
        }
        return this;
    }


    /**
     * 设置标题文本
     * @param title
     * @return
     */
    public TitleBuilder setMiddleTitleText(@StringRes int title){
        setMiddleTitleText(title,Color.BLACK);
        return this;
    }

    /**
     * 设置标题文本
     * @param title
     * @return
     */
    public TitleBuilder setMiddleTitleText(String title){
        setMiddleTitleText(title,Color.BLACK);
        return this;
    }


    /**
     * 设置标题文本
     * @param title
     * @return
     */
    public TitleBuilder setMiddleTitleText(String title,@ColorInt int color){
        if (!isEmpty(title) && color != 0){
            base_title_center_tv.setVisibility(View.VISIBLE);
            base_title_center_tv.setText(title);
            base_title_center_tv.setTextColor(color);
        }
        return this;
    }


    /**
     * 设置标题文本
     * @param title
     * @return
     */
    public TitleBuilder setMiddleTitleText(@StringRes int title,@ColorInt int color){
        if (title != 0 && color != 0){
            base_title_center_tv.setVisibility(View.VISIBLE);
            base_title_center_tv.setText(BaseApplication.getAppContext().getResources().getString(title));
            base_title_center_tv.setTextColor(color);
        }
        return this;
    }



    /**
     * 设置标题img
     * @param resid
     * @return
     */
    public TitleBuilder setMiddleTitleImg(int resid){
        base_title_center_img.setVisibility(resid > 0 ? View.VISIBLE : View.GONE);
        base_title_center_img.setImageResource(resid);
        return this;
    }




    /**
     * 设置右边图片
     * @param resid
     * @return
     */
    public TitleBuilder setRightImageRes(int resid){
        base_title_right_img.setVisibility(resid > 0 ? View.VISIBLE : View.GONE);
        base_title_right_img.setImageResource(resid);
        return this;
    }

    /**
     * 设置右边文字按钮
     * @param text
     * @return
     */
    public TitleBuilder setRightText(String text){
        setRightText(text,Color.BLACK);
        return this;
    }

    /**
     * 设置右边文字按钮
     * @param text
     * @return
     */
    public TitleBuilder setRightText(@StringRes int text){
        setRightText(text,Color.BLACK);
        return this;
    }


    /**
     * 设置右边文字按钮
     * @param text
     * @return
     */
    public TitleBuilder setRightText(String text,@ColorInt int color){
        if (!isEmpty(text) && color != 0){
            base_title_right_tv.setVisibility(View.VISIBLE);
            base_title_right_tv.setText(text);
            base_title_right_tv.setTextColor(color);
        }
        return this;
    }

    /**
     * 设置右边文字按钮
     * @param text
     * @return
     */
    public TitleBuilder setRightText(@StringRes int text,@ColorInt int color){
        if (text != 0 && color != 0){
            base_title_right_tv.setVisibility(View.VISIBLE);
            base_title_right_tv.setText(text);
            base_title_right_tv.setTextColor(color);
        }
        return this;
    }

    /**
     * 设置右边文字按钮
     * @param text
     * @return
     */
    public TitleBuilder setRightText(String text,String color){
        if (!isEmpty(text) && !isEmpty(color)){
            base_title_right_tv.setVisibility(View.VISIBLE);
            base_title_right_tv.setText(text);
            base_title_right_tv.setTextColor(Color.parseColor(color));
        }
        return this;
    }

    /**
     * 设置右边文字按钮
     * @param text
     * @return
     */
    public TitleBuilder setRightText(@StringRes int text,String color){
        if (text != 0 && !isEmpty(color)){
            base_title_right_tv.setVisibility(View.VISIBLE);
            base_title_right_tv.setText(text);
            base_title_right_tv.setTextColor(Color.parseColor(color));
        }
        return this;
    }

    /**
     * 设置右边图片或文字的点击事件
     * @param listener
     * @return
     */
    public TitleBuilder  setRightTextOrImageListener(View.OnClickListener listener){
        if (base_title_right_img.getVisibility() == View.VISIBLE || base_title_right_tv.getVisibility() == View.VISIBLE){
            base_title_right_layout.setOnClickListener(listener);
        }
        return this;
    }

    private boolean isEmpty(String s){
        return s == null || TextUtils.isEmpty(s);
    }

    public View build(){
        return titleView;
    }
}
