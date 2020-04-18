package com.wuxiantao.wxt.ui.title;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiantao.wxt.R;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CNToolbar
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-6 下午5:24
 * Description:${DESCRIPTION}
 */
public class CNToolbar extends Toolbar {


    private TextView leftTextView;
    private ImageView leftImageView;

    private TextView titleView;

    private TextView rightTextView;
    private ImageView rightImageView;

    private RelativeLayout toolbar_layout;

    private View mChildView;

    private String leftText;
    private String titleText;
    private String rightText;
    private Drawable leftImg;
    private Drawable rightImg;
    private int backgroundColor;

    private int textSize;
    private int titleTextSize;
    private int textColor;

    public CNToolbar(Context context) {
        this(context, null, 0);
    }


    public CNToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("RestrictedApi")
    public CNToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //通过代码得到布局文件当中一些属性的值
        @SuppressLint("RestrictedApi")
        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                R.styleable.CNToolbar, defStyleAttr, 0);
        leftText = a.getString(R.styleable.CNToolbar_cn_leftText);
        titleText = a.getString(R.styleable.CNToolbar_cn_titleText);
        rightText = a.getString(R.styleable.CNToolbar_cn_rightText);
        leftImg = a.getDrawable(R.styleable.CNToolbar_cn_leftImage);
        rightImg = a.getDrawable(R.styleable.CNToolbar_cn_rightImage);
        backgroundColor = a.getInt(R.styleable.CNToolbar_cn_background,0);
        textSize = a.getDimensionPixelSize(R.styleable.CNToolbar_cn_textSize,0);
        titleTextSize = a.getDimensionPixelSize(R.styleable.CNToolbar_cn_titleTextSize,0);
        textColor = a.getColor(R.styleable.CNToolbar_cn_textColor,0);
        a.recycle();

        //初始界面
        initView();

        //初始监听器
        initListener();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        if (mChildView == null) {
            mChildView = View.inflate(getContext(), R.layout.toolbar_cn, null);
            leftImageView = mChildView.findViewById(R.id.toolbar_left_img);
            leftTextView = mChildView.findViewById(R.id.toolbar_left_tv);
            titleView = mChildView.findViewById(R.id.toolbar_title);
            rightTextView = mChildView.findViewById(R.id.toolbar_right_text);
            rightImageView = mChildView.findViewById(R.id.toolbar_right_img);
            toolbar_layout = mChildView.findViewById(R.id.toolbar_layout);
        }

        //添加自定义的布局到Toolbar
        addView(mChildView);
        //设置左边文字
        if (!isEmpty(leftText)){
            leftTextView.setVisibility(VISIBLE);
            leftTextView.setText(leftText);
            leftImageView.setVisibility(GONE);
        }

        //设置左边图片
        if (leftImg != null){
            leftTextView.setVisibility(GONE);
            leftImageView.setVisibility(VISIBLE);
            leftImageView.setImageDrawable(leftImg);
        }

        //设置标题
        if (!isEmpty(titleText)){
            titleView.setVisibility(VISIBLE);
            titleView.setText(titleText);
            if (titleTextSize > 0){
                titleView.setTextSize(titleTextSize);
            }
        }

        //设置右边文字
        if (!isEmpty(rightText)){
            rightTextView.setVisibility(VISIBLE);
            rightTextView.setText(rightText);
            rightImageView.setVisibility(GONE);
        }

        //设置右边图片
        if (rightImg != null){
            rightTextView.setVisibility(GONE);
            rightImageView.setImageDrawable(rightImg);
            rightImageView.setVisibility(VISIBLE);
        }

        //设置文字大小
        if (textSize > 0){
            leftTextView.setTextSize(textSize);
            rightTextView.setTextSize(textSize);
        }

        //设置文字颜色
        if (textColor > 0){
            leftTextView.setTextColor(textColor);
            rightTextView.setTextColor(textColor);
            titleView.setTextColor(textColor);
        }

        //设置标题栏背景色
        if (backgroundColor > 0){
            toolbar_layout.setBackgroundResource(backgroundColor);
        }
    }


    private boolean isEmpty(String s){
        return s == null || TextUtils.isEmpty(s);
    }

    /**
     * 重写设置标题的方法
     * @param title
     */
    @Override
    public void setTitle(CharSequence title) {
        titleView.setText(title);
    }

    @Override
    public void setTitle(@StringRes int resId) {
        titleView.setText(resId);
    }


    public void setTitleText(String titleText) {
        if (!isEmpty(titleText)){
            titleView.setText(titleText);
        }
    }

    /**
     * 左右按钮的监听
     */
    private void initListener() {
        if (leftTextView.getVisibility() == VISIBLE){
            setOnClickListener(leftTextView,1);
        }
        else if (leftImageView.getVisibility() == VISIBLE){
            setOnClickListener(leftImageView,1);
        }

        if (rightTextView.getVisibility() == VISIBLE){
            setOnClickListener(rightTextView);
        }
        else if (rightImageView.getVisibility() == VISIBLE){
            setOnClickListener(rightImageView);
        }
    }

    private void setOnClickListener(View view,int type){
        if (type == 1){
            view.setOnClickListener(v -> {
                if (onLeftButtonClickListener != null) {
                    onLeftButtonClickListener.onClick();
                }
            });
        }else {
            setOnClickListener(view);
        }
    }

    private void setOnClickListener(View view){
        view.setOnClickListener(v -> {
            if (onRightButtonClickListener != null) {
                onRightButtonClickListener.onClick();
            }
        });
    }

    public interface OnLeftButtonClickListener {
        void onClick();
    }

    public interface OnRightButtonClickListener {
        void onClick();
    }

    private OnLeftButtonClickListener onLeftButtonClickListener;
    private OnRightButtonClickListener onRightButtonClickListener;

    public void setOnLeftButtonClickListener(OnLeftButtonClickListener listener) {
        onLeftButtonClickListener = listener;
    }

    public void setOnRightButtonClickListener(OnRightButtonClickListener listener) {
        onRightButtonClickListener = listener;
    }
}

