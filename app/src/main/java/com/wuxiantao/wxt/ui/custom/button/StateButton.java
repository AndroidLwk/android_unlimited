package com.wuxiantao.wxt.ui.custom.button;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.wuxiantao.wxt.R;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:StateButton
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-30 下午5:54
 * Description:${DESCRIPTION}
 */
public class StateButton extends AppCompatButton {
    //text color
    private int mNormalTextColor = 0;
    private int mPressedTextColor = 0;
    private int mUnableTextColor = 0;
    private ColorStateList mTextColorStateList;

    //animation duration
    private int mDuration = 0;

    //radius
    private float mRadius = 0;
    private boolean mRound;

    //stroke
    private float mStrokeDashWidth = 0;
    private float mStrokeDashGap = 0;
    private int mNormalStrokeWidth = 0;
    private int mPressedStrokeWidth = 0;
    private int mUnableStrokeWidth = 0;
    private int mNormalStrokeColor = 0;
    private int mPressedStrokeColor = 0;
    private int mUnableStrokeColor = 0;

    //background color
    private int mNormalBackgroundColor = 0;
    private int mPressedBackgroundColor = 0;
    private int mUnableBackgroundColor = 0;

    //Orientation
    private int normalOrientation = 0;
    private int pressedOrientation = 0;
    private int unableOrientation = 0;

    //shape type
    private int normalShapeType = 0;
    private int pressedShapeType = 0;
    private int unableShapeType = 0;

    private GradientDrawable mNormalBackground;
    private GradientDrawable mPressedBackground;
    private GradientDrawable mUnableBackground;

    private int[][] states;

    private StateListDrawable mStateBackground;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public StateButton(Context context) {
        this(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public StateButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.buttonStyle);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public StateButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(attrs);
    }

    @SuppressLint("WrongConstant")
    private void setup(AttributeSet attrs) {

        states = new int[4][];

        Drawable drawable = getBackground();
        if(drawable != null && drawable instanceof StateListDrawable){
            mStateBackground = (StateListDrawable) drawable;
        }else{
            mStateBackground = new StateListDrawable();
        }

        mNormalBackground = new GradientDrawable();
        mPressedBackground = new GradientDrawable();
        mUnableBackground = new GradientDrawable();

        //pressed, focused, normal, unable
        states[0] = new int[] { android.R.attr.state_enabled, android.R.attr.state_pressed };
        states[1] = new int[] { android.R.attr.state_enabled, android.R.attr.state_focused };
        states[3] = new int[] { -android.R.attr.state_enabled};
        states[2] = new int[] { android.R.attr.state_enabled };

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.StateButton);

        //get original text color as default
        //set text color
        mTextColorStateList = getTextColors();
        int mDefaultNormalTextColor = mTextColorStateList.getColorForState(states[2], getCurrentTextColor());
        int mDefaultPressedTextColor = mTextColorStateList.getColorForState(states[0], getCurrentTextColor());
        int mDefaultUnableTextColor = mTextColorStateList.getColorForState(states[3], getCurrentTextColor());
        mNormalTextColor = a.getColor(R.styleable.StateButton_normalTextColor, mDefaultNormalTextColor);
        mPressedTextColor = a.getColor(R.styleable.StateButton_pressedTextColor, mDefaultPressedTextColor);
        mUnableTextColor = a.getColor(R.styleable.StateButton_unableTextColor, mDefaultUnableTextColor);
        setTextColor();

        //set animation duration
        mDuration = a.getInteger(R.styleable.StateButton_animationDuration, mDuration);
        mStateBackground.setEnterFadeDuration(mDuration);
        mStateBackground.setExitFadeDuration(mDuration);

        normalOrientation = a.getInt(R.styleable.StateButton_normalOrientation,-1);
        pressedOrientation = a.getInt(R.styleable.StateButton_pressedOrientation,-1);
        unableOrientation = a.getInt(R.styleable.StateButton_unableOrientation,-1);

        normalShapeType = a.getInt(R.styleable.StateButton_normalShapeType,-1);
        pressedShapeType = a.getInt(R.styleable.StateButton_pressedShapeType,-1);
        unableShapeType = a.getInt(R.styleable.StateButton_unableShapeType,-1);

        if (normalOrientation != -1){
            setGradient(a);
        }else {
            setBackGroundColor(a);
        }

        setShapeType();

        //set radius
        mRadius = a.getDimensionPixelSize(R.styleable.StateButton_radius, 0);
        mRound = a.getBoolean(R.styleable.StateButton_round, false);
        mNormalBackground.setCornerRadius(mRadius);
        mPressedBackground.setCornerRadius(mRadius);
        mUnableBackground.setCornerRadius(mRadius);

        //set stroke
        mStrokeDashWidth = a.getDimensionPixelSize(R.styleable.StateButton_strokeDashWidth, 0);
        mStrokeDashGap = a.getDimensionPixelSize(R.styleable.StateButton_strokeDashGap, 0);
        mNormalStrokeWidth = a.getDimensionPixelSize(R.styleable.StateButton_normalStrokeWidth, 0);
        mPressedStrokeWidth = a.getDimensionPixelSize(R.styleable.StateButton_pressedStrokeWidth, 0);
        mUnableStrokeWidth = a.getDimensionPixelSize(R.styleable.StateButton_unableStrokeWidth, 0);
        mNormalStrokeColor = a.getColor(R.styleable.StateButton_normalStrokeColor, 0);
        mPressedStrokeColor = a.getColor(R.styleable.StateButton_pressedStrokeColor, 0);
        mUnableStrokeColor = a.getColor(R.styleable.StateButton_unableStrokeColor, 0);
        setStroke();

        //set background
        mStateBackground.addState(states[0], mPressedBackground);
        mStateBackground.addState(states[1], mPressedBackground);
        mStateBackground.addState(states[3], mUnableBackground);
        mStateBackground.addState(states[2], mNormalBackground);
        setBackgroundDrawable(mStateBackground);
        a.recycle();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.setStateListAnimator(null);
        }
    }

    //设置渐变参数
    private void setGradient(TypedArray a){
        //设置渐变方向
        mNormalBackground.setOrientation(getOrientation(normalOrientation));
        mPressedBackground.setOrientation(getOrientation(pressedOrientation));
        mUnableBackground.setOrientation(getOrientation(unableOrientation));

        //设置渐变类型
        mNormalBackground.setGradientType(GradientDrawable.RECTANGLE);
        mPressedBackground.setGradientType(GradientDrawable.RECTANGLE);
        mUnableBackground.setGradientType(GradientDrawable.RECTANGLE);

        //开始颜色
        //background start color
        int mNormalBackgroundStartColor = a.getColor(R.styleable.StateButton_normalBackgroundStartColor, 0);
        int mPressedBackgroundStartColor = a.getColor(R.styleable.StateButton_pressedBackgroundStartColor, 0);
        int mUnableBackgroundStartColor = a.getColor(R.styleable.StateButton_unableBackgroundStartColor, 0);
        //中间颜色
        //background center color
        int mNormalBackgroundCenterColor = a.getColor(R.styleable.StateButton_normalBackgroundCenterColor, 0);
        int mPressedBackgroundCenterColor = a.getColor(R.styleable.StateButton_pressedBackgroundCenterColor, 0);
        int mUnableBackgroundCenterColor = a.getColor(R.styleable.StateButton_unableBackgroundCenterColor, 0);
        //结束颜色
        //background end color
        int mNormalBackgroundEndColor = a.getColor(R.styleable.StateButton_normalBackgroundEndColor, 0);
        int mPressedBackgroundEndColor = a.getColor(R.styleable.StateButton_pressedBackgroundEndColor, 0);
        int mUnableBackgroundEndColor = a.getColor(R.styleable.StateButton_unableBackgroundEndColor, 0);
        //渐变颜色数组
        @ColorInt
        int[] normalColors = {mNormalBackgroundStartColor, mNormalBackgroundCenterColor, mNormalBackgroundEndColor};
        @ColorInt
        int[] pressedColors = {mPressedBackgroundStartColor, mPressedBackgroundCenterColor, mPressedBackgroundEndColor};
        @ColorInt
        int[] unableColors = {mUnableBackgroundStartColor, mUnableBackgroundCenterColor, mUnableBackgroundEndColor};
        mNormalBackground.setColors(normalColors);
        mPressedBackground.setColors(pressedColors);
        mUnableBackground.setColors(unableColors);
    }

    //setBackGroundColor
    private void setBackGroundColor(TypedArray a){
        mNormalBackgroundColor = a.getColor(R.styleable.StateButton_normalBackgroundColor, 0);
        mPressedBackgroundColor = a.getColor(R.styleable.StateButton_pressedBackgroundColor, 0);
        mUnableBackgroundColor = a.getColor(R.styleable.StateButton_unableBackgroundColor, 0);
        mNormalBackground.setColor(mNormalBackgroundColor);
        mPressedBackground.setColor(mPressedBackgroundColor);
        mUnableBackground.setColor(mUnableBackgroundColor);
    }


    //set shapeType
    private void setShapeType(){
        if (normalShapeType != -1){
            mNormalBackground.setShape(normalShapeType);
        }
        if (pressedShapeType != -1){
            mPressedBackground.setShape(pressedShapeType);
        }
        if (unableShapeType != -1){
            mUnableBackground.setShape(unableShapeType);
        }
    }

    private GradientDrawable.Orientation getOrientation(int orientation){
        GradientDrawable.Orientation o = null;
        switch (orientation){
            case 0:
                o = GradientDrawable.Orientation.BL_TR;
                break;
            case 1:
                o = GradientDrawable.Orientation.BOTTOM_TOP;
                break;
            case 2:
                o = GradientDrawable.Orientation.BR_TL;
                break;
            case 3:
                o = GradientDrawable.Orientation.LEFT_RIGHT;
                break;
            case 4:
                o = GradientDrawable.Orientation.RIGHT_LEFT;
                break;
            case 5:
                o = GradientDrawable.Orientation.TL_BR;
                break;
            case 6:
                o = GradientDrawable.Orientation.TOP_BOTTOM;
                break;
            case 7:
                o = GradientDrawable.Orientation.TR_BL;
                break;
            default:
                o = GradientDrawable.Orientation.BL_TR;
                break;
        }

        return o;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setRound(mRound);
    }

    /****************** stroke color *********************/
    public void setNormalStrokeColor(@ColorInt int normalStrokeColor) {
        this.mNormalStrokeColor = normalStrokeColor;
        setStroke(mNormalBackground, mNormalStrokeColor, mNormalStrokeWidth);
    }

    public void setPressedStrokeColor(@ColorInt int pressedStrokeColor) {
        this.mPressedStrokeColor = pressedStrokeColor;
        setStroke(mPressedBackground, mPressedStrokeColor, mPressedStrokeWidth);
    }

    public void setUnableStrokeColor(@ColorInt int unableStrokeColor) {
        this.mUnableStrokeColor = unableStrokeColor;
        setStroke(mUnableBackground, mUnableStrokeColor, mUnableStrokeWidth);
    }

    public void setStateStrokeColor(@ColorInt int normal, @ColorInt int pressed, @ColorInt int unable){
        mNormalStrokeColor = normal;
        mPressedStrokeColor = pressed;
        mUnableStrokeColor = unable;
        setStroke();
    }

    /****************** stroke width *********************/
    public void setNormalStrokeWidth(int normalStrokeWidth) {
        this.mNormalStrokeWidth = normalStrokeWidth;
        setStroke(mNormalBackground, mNormalStrokeColor, mNormalStrokeWidth);
    }

    public void setPressedStrokeWidth(int pressedStrokeWidth) {
        this.mPressedStrokeWidth = pressedStrokeWidth;
        setStroke(mPressedBackground, mPressedStrokeColor, mPressedStrokeWidth);
    }

    public void setUnableStrokeWidth(int unableStrokeWidth) {
        this.mUnableStrokeWidth = unableStrokeWidth;
        setStroke(mUnableBackground, mUnableStrokeColor, mUnableStrokeWidth);
    }

    public void setStateStrokeWidth(int normal, int pressed, int unable){
        mNormalStrokeWidth = normal;
        mPressedStrokeWidth = pressed;
        mUnableStrokeWidth= unable;
        setStroke();
    }

    public void setStrokeDash(float strokeDashWidth, float strokeDashGap) {
        this.mStrokeDashWidth = strokeDashWidth;
        this.mStrokeDashGap = strokeDashWidth;
        setStroke();
    }

    private void setStroke(){
        setStroke(mNormalBackground, mNormalStrokeColor, mNormalStrokeWidth);
        setStroke(mPressedBackground, mPressedStrokeColor, mPressedStrokeWidth);
        setStroke(mUnableBackground, mUnableStrokeColor, mUnableStrokeWidth);
    }

    private void setStroke(GradientDrawable mBackground, int mStrokeColor, int mStrokeWidth) {
        mBackground.setStroke(mStrokeWidth, mStrokeColor, mStrokeDashWidth, mStrokeDashGap);
    }

    /********************   radius  *******************************/
    public void setRadius(@FloatRange(from = 0) float radius) {
        this.mRadius = radius;
        mNormalBackground.setCornerRadius(mRadius);
        mPressedBackground.setCornerRadius(mRadius);
        mUnableBackground.setCornerRadius(mRadius);
    }

    public void setRound(boolean round){
        this.mRound = round;
        int height = getMeasuredHeight();
        if(mRound){
            setRadius(height / 2f);
        }
    }

    public void setRadius(float[] radii){
        mNormalBackground.setCornerRadii(radii);
        mPressedBackground.setCornerRadii(radii);
        mUnableBackground.setCornerRadii(radii);
    }

    /********************  background color  **********************/
    public void setStateBackgroundColor(@ColorInt int normal, @ColorInt int pressed, @ColorInt int unable){
        mNormalBackgroundColor = normal;
        mPressedBackgroundColor = pressed;
        mUnableBackgroundColor = unable;
        mNormalBackground.setColor(mNormalBackgroundColor);
        mPressedBackground.setColor(mPressedBackgroundColor);
        mUnableBackground.setColor(mUnableBackgroundColor);
    }

    public void setNormalBackgroundColor(@ColorInt int normalBackgroundColor) {
        this.mNormalBackgroundColor = normalBackgroundColor;
        mNormalBackground.setColor(mNormalBackgroundColor);
    }

    public void setPressedBackgroundColor(@ColorInt int pressedBackgroundColor) {
        this.mPressedBackgroundColor = pressedBackgroundColor;
        mPressedBackground.setColor(mPressedBackgroundColor);
    }

    public void setUnableBackgroundColor(@ColorInt int unableBackgroundColor) {
        this.mUnableBackgroundColor = unableBackgroundColor;
        mUnableBackground.setColor(mUnableBackgroundColor);
    }

    /*******************alpha animation duration********************/
    public void setAnimationDuration(@IntRange(from = 0)int duration){
        this.mDuration = duration;
        mStateBackground.setEnterFadeDuration(mDuration);
    }

    /***************  text color   ***********************/
    private void setTextColor() {
        int[] colors = new int[] {mPressedTextColor, mPressedTextColor, mNormalTextColor, mUnableTextColor};
        mTextColorStateList = new ColorStateList(states, colors);
        setTextColor(mTextColorStateList);
    }

    public void setStateTextColor(@ColorInt int normal, @ColorInt int pressed, @ColorInt int unable){
        this.mNormalTextColor = normal;
        this.mPressedTextColor = pressed;
        this.mUnableTextColor = unable;
        setTextColor();
    }

    public void setNormalTextColor(@ColorInt int normalTextColor) {
        this.mNormalTextColor = normalTextColor;
        setTextColor();

    }

    public void setPressedTextColor(@ColorInt int pressedTextColor) {
        this.mPressedTextColor = pressedTextColor;
        setTextColor();
    }

    public void setUnableTextColor(@ColorInt int unableTextColor) {
        this.mUnableTextColor = unableTextColor;
        setTextColor();
    }
}
