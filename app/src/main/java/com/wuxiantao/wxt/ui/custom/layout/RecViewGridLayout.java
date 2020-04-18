package com.wuxiantao.wxt.ui.custom.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:RecViewGridLayout
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-30 下午4:50
 * Description:${DESCRIPTION} RecyclerView 网格显示正方形元素
 */
public class RecViewGridLayout extends RelativeLayout {

    public RecViewGridLayout(Context context) {
        super(context);
    }

    public RecViewGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecViewGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0,widthMeasureSpec),getDefaultSize(0,heightMeasureSpec));
        int childWidthSize = getMeasuredWidth();
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize,MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
