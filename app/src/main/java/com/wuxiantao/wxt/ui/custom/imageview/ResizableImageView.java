package com.wuxiantao.wxt.ui.custom.imageview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ResizableImageView
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-7 下午11:45
 * Description:${DESCRIPTION} 图片宽度充满屏幕 自定义
 */
public class ResizableImageView extends AppCompatImageView {
    public ResizableImageView(Context context) {
        this(context, null);
    }

    public ResizableImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ResizableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable d = getDrawable();
        if (d != null) {
            // ceil not round - avoid thin vertical gaps along the left/right edges
            int width = MeasureSpec.getSize(widthMeasureSpec);
            //高度根据使得图片的宽度充满屏幕计算而得
            int height = (int) Math.ceil((float) width * (float) d.getIntrinsicHeight() / (float) d.getIntrinsicWidth());
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

}
