package com.wuxiantao.wxt.ui.custom.progress;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wuxiantao.wxt.R;


/**
 * Created by Relin
 * on 2018-08-10.
 * 圈圈进度控件
 * 圆圈进度控件支持进度显示，修改进度背景色
 * 和进度颜色、圆圈边框宽度、圆圈半径大小
 * The circle progress control supports <br>
 * progress display,modifying progress <br>
 * background color and progress color, <br>
 * circle border width, and circle radius size<br>
 */

public class CircleProgressBar extends View {

    /**
     * 画笔
     */
    private Paint paint;
    /**
     * 宽度
     */
    private float width;
    /**
     * 高度
     */
    private float height;
    /**
     * 中心坐标
     */
    private float circleX, circleY;
    /**
     * 半径
     */
    private float progressRadius = 0;
    /**
     * 进度背景颜色
     */
    private int progressBackgroundColor = Color.parseColor("#F2F2F2");
    /**
     * 进度颜色
     */
    private int progressColor = Color.parseColor("#73B2FF");

    /**
     * 进度
     */
    private int progress = 65;

    /**
     * 进度最大值
     */
    private int max = 100;

    /**
     * 进度宽度
     */
    private float progressStrokeWidth = dpToPx(20);

    /**
     * 进度开始角度
     */
    private float progressStartAngle = -90;

    /**
     * 中间进度值可见
     */
    private int progressTextVisibility = View.VISIBLE;

    /**
     * 中间进度值文字大小
     */
    private int progressTextSize = (int) (Resources.getSystem().getDisplayMetrics().density * 14);

    /**
     * 中间进度值颜色
     */
    private int progressTextColor = Color.parseColor("#222222");


    public CircleProgressBar(Context context) {
        super(context);
        init(context, null);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);
        progress = array.getInt(R.styleable.CircleProgressBar_progress, progress);
        max = array.getInt(R.styleable.CircleProgressBar_maxx, max);
        progressTextVisibility = array.getInt(R.styleable.CircleProgressBar_progressTextVisibility, progressTextVisibility);
        progressTextSize = array.getDimensionPixelSize(R.styleable.CircleProgressBar_progressTextSize, progressTextSize);
        progressTextColor = array.getColor(R.styleable.CircleProgressBar_progressTextColor, progressTextColor);
        progressColor = array.getColor(R.styleable.CircleProgressBar_progressColor, progressColor);
        progressBackgroundColor = array.getColor(R.styleable.CircleProgressBar_progressBackgroundColor, progressBackgroundColor);
        progressStartAngle = array.getFloat(R.styleable.CircleProgressBar_progressStartAngle, progressStartAngle);
        progressStrokeWidth = array.getDimension(R.styleable.CircleProgressBar_progressStrokeWidth, progressStrokeWidth);
        progressRadius = array.getDimension(R.styleable.CircleProgressBar_progressRadius, progressRadius);

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);//只描边，不填充
        paint.setAntiAlias(true);
        paint.setStrokeWidth(progressStrokeWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        circleX = width / 2;
        circleY = height / 2;
        if (progressRadius == 0) {
            progressRadius = circleX > circleY ? circleY : circleX;
        } else {
            float limit = circleX > circleY ? circleY : circleX;
            progressRadius = progressRadius > limit ? limit : progressRadius;
        }
        float paddingVertical = getPaddingLeft() + getPaddingRight();
        float paddingHorizontal = getPaddingTop() + getPaddingBottom();
        float paddingSum = paddingHorizontal > paddingVertical ? paddingVertical : paddingHorizontal;
        progressRadius -= paddingSum + (progressStrokeWidth / 4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(progressBackgroundColor);
        canvas.drawCircle(circleX, circleY, progressRadius, paint);
        paint.setColor(progressColor);
        RectF rectF = new RectF(circleX - progressRadius, circleY - progressRadius, circleX + progressRadius, circleY + progressRadius);
        canvas.drawArc(rectF, progressStartAngle, 360 * progress / max, false, paint);
        if (progressTextVisibility == View.VISIBLE) {
            String progressText = String.valueOf(100 * progress / max) + "%";
            paint.setColor(progressTextColor);
            Paint paint = new Paint();
            paint.setColor(progressTextColor);
            paint.setTextSize(progressTextSize);
            Rect rect = new Rect();
            paint.getTextBounds(progressText, 0, progressText.length(), rect);
            int w = rect.width();
            int h = rect.height();
            canvas.drawText(progressText, circleX - (w / 2), circleY + (h / 2), paint);
        }
    }


    /**
     * 获取屏幕密度
     *
     * @return
     */
    private static float getScreenDensity() {
        return Resources.getSystem().getDisplayMetrics().density;
    }


    /**
     * dp to px
     *
     * @param dp
     * @return
     */
    private static float dpToPx(float dp) {
        return dp * getScreenDensity();
    }


    /**
     * 设置宽度
     * @param width
     */
    public void setWidth(float width) {
        this.width = width;
        invalidate();
    }

    /**
     * 设置高
     * @param height
     */
    public void setHeight(float height) {
        this.height = height;
        invalidate();
    }

    /**
     * 获取圆心 -x
     * @return
     */
    public float getCircleX() {
        return circleX;
    }

    /**
     * 设置圆心 -x
     * @param circleX
     */
    public void setCircleX(float circleX) {
        this.circleX = circleX;
        invalidate();
    }

    /**
     * 获取圆心 -y
     * @return
     */
    public float getCircleY() {
        return circleY;
    }

    /**
     * 设置圆心 - y
     * @param circleY
     */
    public void setCircleY(float circleY) {
        this.circleY = circleY;
        invalidate();
    }

    /**
     * 获取进度半径
     * @return
     */
    public float getProgressRadius() {
        return progressRadius;
    }

    /**
     * 设置进度半径
     * @param progressRadius
     */
    public void setProgressRadius(float progressRadius) {
        this.progressRadius = progressRadius;
        invalidate();
    }

    /**
     * 获取进度半径
     * @return
     */
    public float getProgressStartAngle() {
        return progressStartAngle;
    }

    /**
     * 设置开始角度
     * @param progressStartAngle
     */
    public void setProgressStartAngle(float progressStartAngle) {
        this.progressStartAngle = progressStartAngle;
        invalidate();
    }

    /**
     * 获取进度背景颜色
     * @return
     */
    public int getProgressBackgroundColor() {
        return progressBackgroundColor;
    }

    /**
     * 设置进度背景颜色
     * @param progressBackgroundColor
     */
    public void setProgressBackgroundColor(int progressBackgroundColor) {
        this.progressBackgroundColor = progressBackgroundColor;
        invalidate();
    }

    /**
     * 获取进度颜色
     * @return
     */
    public int getProgressColor() {
        return progressColor;
    }

    /**
     * 设置进度背景颜色
     * @param progressColor
     */
    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    /**
     * 获取进度
     * @return
     */
    public float getProgress() {
        return progress;
    }

    /**
     * 设置进度
     * @param progress
     */
    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    /**
     * 获取进度宽度
     * @return
     */
    public float getProgressStrokeWidth() {
        return progressStrokeWidth;
    }

    /**
     * 设置进度宽度
     * @param progressStrokeWidth
     */
    public void setProgressStrokeWidth(float progressStrokeWidth) {
        this.progressStrokeWidth = progressStrokeWidth;
        invalidate();
    }

    /**
     * 获取最大进度
     * @return
     */
    public float getMax() {
        return max;
    }

    /**
     * 设置最大进度
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
        invalidate();
    }

    /**
     * 获取进度文字显示属性
     * @return
     */
    public int getProgressTextVisibility() {
        return progressTextVisibility;
    }

    /**
     * 设置文字显示属性
     * @param progressTextVisibility
     */
    public void setProgressTextVisibility(int progressTextVisibility) {
        this.progressTextVisibility = progressTextVisibility;
        invalidate();
    }

    /**
     * 获取文字大小
     * @return
     */
    public int getProgressTextSize() {
        return progressTextSize;
    }

    /**
     * 设置进度文字大小
     * @param progressTextSize
     */
    public void setProgressTextSize(int progressTextSize) {
        this.progressTextSize = progressTextSize;
        invalidate();
    }

    /**
     * 获取进度文字颜色
     * @return
     */
    public int getProgressTextColor() {
        return progressTextColor;
    }

    /**
     * 设置进度文字颜色
     * @param progressTextColor
     */
    public void setProgressTextColor(int progressTextColor) {
        this.progressTextColor = progressTextColor;
        invalidate();
    }


}

