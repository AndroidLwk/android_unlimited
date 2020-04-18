package com.wuxiantao.wxt.ui.custom.scrollview;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CustomScrollView
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-26 下午4:19
 * Description:${DESCRIPTION}
 */
public class CustomScrollView extends NestedScrollView {

    private OnScrollListener listener;

    public CustomScrollView(Context context) {
        this(context,null);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
    }

    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        return 0;
    }

    //监听是否滑动到底部
    public interface  OnScrollListener{
        void onScrollBottom(boolean isBottom);
    }

    public void setOnScrollListener(OnScrollListener l){
        this.listener = l;
    }

    //重写原生onScrollChanged方法，将参数传递给接口，由接口传递出去
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View view = getChildAt(getChildCount() -1);
        int d = view.getBottom();
        d -= (getHeight() + getScrollY());
        boolean isBottom;
        //是否滑动到底部
        if (d == 0){
            isBottom = true;
        }else {
            isBottom = false;
            super.onScrollChanged(l, t, oldl, oldt);
        }
        //回调滑动到底部
        if (listener != null){
            listener.onScrollBottom(isBottom);
        }
    }
}
