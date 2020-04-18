package com.wuxiantao.wxt.ui.custom.textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.wuxiantao.wxt.R;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BorderTextView
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-30 下午4:54
 * Description:${DESCRIPTION} 实现带下划线的文本框
 */
public class BorderTextView extends AppCompatTextView {

    /**
     * 四周是否带有边框【true:四周带有边框】【false:四周不带边框】
     */
    private boolean borders = false;

    /**
     * 左边是否带有边框【true:左侧带有边框】【false:左侧不带边框】
     */
    private boolean borderLeft = false;

    /**
     * 顶部是否带有边框【true:顶部带有边框】【false:底部不带边框】
     */
    private boolean borderTop = false;

    /**
     * 右侧是否带有边框【true:右侧带有边框】【false:右侧不带边框】
     */
    private boolean borderRight = false;

    /**
     * 底部是否带有边框【true:底部带有边框】【false:底部不带边框】
     */
    private boolean borderBottom = false;

    /**
     * 边框颜色
     */
    private String textColor = "#ff000000";

    public BorderTextView(Context context) {
        this(context, null);
    }

    public BorderTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BorderTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context,attrs);
    }


    private void init(Context context,AttributeSet attrs){
        // 获取自定义属性集
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.BorderTextView);
        // 是否设置全部边框，默认为false
        borders = typedArray.getBoolean(
                R.styleable.BorderTextView_layout_borders, false);
        // 是否设置左侧边框，默认为false
        borderLeft = typedArray.getBoolean(
                R.styleable.BorderTextView_layout_borderLeft, false);
        // 是否设置顶部边框，默认为false
        borderTop = typedArray.getBoolean(
                R.styleable.BorderTextView_layout_borderTop, false);
        // 是否设置右侧边框，默认为false
        borderRight = typedArray.getBoolean(
                R.styleable.BorderTextView_layout_borderRight, false);
        // 是否设置底部边框，默认为false
        borderBottom = typedArray.getBoolean(
                R.styleable.BorderTextView_layout_borderBottom, false);
        // 获取文本颜色值，用来画边框的，便于和文本颜色匹配
        textColor = attrs.getAttributeValue(
                "http://schemas.android.com/apk/res/android", "textColor");
        typedArray.recycle();
    }



    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        // 创建画笔
        Paint paint = new Paint();
        // 获取该画笔颜色
        int color = paint.getColor();
        // 设置画笔颜色
        paint.setColor(Color.parseColor(textColor));
        // 如果borders为true，表示左上右下都有边框
        if (borders) {
            canvas.drawLine(0, 0, 0, this.getHeight() - 1, paint);
            canvas.drawLine(0, 0, this.getWidth() - 1, 0, paint);
            canvas.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1,
                    this.getHeight() - 1, paint);
            canvas.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,
                    this.getHeight() - 1, paint);
        } else {
            if (borderLeft) {
                // 画左边框线
                canvas.drawLine(0, 0, 0, this.getHeight() - 1, paint);
            }
            if (borderTop) {
                // 画顶部边框线
                canvas.drawLine(0, 0, this.getWidth() - 1, 0, paint);
            }
            if (borderRight) {
                // 画右侧边框线
                canvas.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1,
                        this.getHeight() - 1, paint);
            }
            if (borderBottom) {
                // 画底部边框线
                canvas.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,
                        this.getHeight() - 1, paint);
            }
        }
        // 设置画笔颜色归位
        paint.setColor(color);
    }


}
