package com.wuxiantao.wxt.ui.custom.progress;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wuxiantao.wxt.R;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CircleProgressView
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-6 下午12:19
 * Description:${DESCRIPTION}
 */
public class CircleProgressView extends View {

    private int mCurrent;//当前进度
    private Paint mPaintOut;
    private Paint mPaintCurrent;
    private float mPaintWidth;//画笔宽度
    private int mPaintColor = Color.RED;//画笔颜色
    private int location;//从哪个位置开始
    private float startAngle;//开始角度

    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
        location = array.getInt(R.styleable.CircleProgressView_location, 1);
        mPaintWidth = array.getDimension(R.styleable.CircleProgressView_progress_paint_width, dip2px(context, 4));//默认4dp
        mPaintColor = array.getColor(R.styleable.CircleProgressView_progress_paint_color, mPaintColor);
        array.recycle();

        //画笔->背景圆弧
        mPaintOut = new Paint();
        mPaintOut.setAntiAlias(true);
        mPaintOut.setStrokeWidth(mPaintWidth);
        mPaintOut.setStyle(Paint.Style.STROKE);
        mPaintOut.setColor(Color.WHITE);
        mPaintOut.setStrokeCap(Paint.Cap.ROUND);

        //画笔->进度圆弧
        mPaintCurrent = new Paint();
        mPaintCurrent.setAntiAlias(true);
        mPaintCurrent.setStrokeWidth(mPaintWidth);
        mPaintCurrent.setStyle(Paint.Style.STROKE);
        mPaintCurrent.setColor(mPaintColor);
        mPaintCurrent.setStrokeCap(Paint.Cap.ROUND);

        if (location == 1) {//默认从左侧开始
            startAngle = -180;
        } else if (location == 2) {
            startAngle = -90;
        } else if (location == 3) {
            startAngle = 0;
        } else if (location == 4) {
            startAngle = 90;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = width > height ? height : width;
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制背景圆弧,因为画笔有一定的宽度，所有画圆弧的范围要比View本身的大小稍微小一些，不然画笔画出来的东西会显示不完整
        @SuppressLint("DrawAllocation")
        RectF rectF = new RectF(mPaintWidth / 2, mPaintWidth / 2, getWidth() - mPaintWidth / 2, getHeight() - mPaintWidth / 2);
        canvas.drawArc(rectF, 0, 360, false, mPaintOut);

        //绘制当前进度
        float sweepAngle = (float) 360 * mCurrent / 100;
        canvas.drawArc(rectF, startAngle, sweepAngle, false, mPaintCurrent);

        if (mLoadingCompleteListener != null && mCurrent == 100) {
            mLoadingCompleteListener.complete();
        }
    }


    /**
     * 获取当前进度值
     *
     * @return
     */
    public int getmCurrent() {
        return mCurrent;
    }

    /**
     * 设置当前进度并重新绘制界面
     *
     * @param mCurrent
     */
    public void setmCurrent(int mCurrent) {
        this.mCurrent = mCurrent;
        invalidate();
    }


    private OnLoadingCompleteListener mLoadingCompleteListener;

    public void setOnLoadingCompleteListener(OnLoadingCompleteListener loadingCompleteListener) {
        this.mLoadingCompleteListener = loadingCompleteListener;
    }

    public interface OnLoadingCompleteListener {
        void complete();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
