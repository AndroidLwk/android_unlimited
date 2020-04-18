package com.wuxiantao.wxt.ui.custom.decoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *  RecyclerView 设置间距的代码
 * Created by Administrator on 2018/7/20 0020.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int leftRight;

    private int topBottom;
    private int position = -1;
    //如果需要用画笔手绘
    private Paint mPaint;
    //颜色
    private final int color = Color.GRAY;
    //分割线宽度或高度
    private int mPaintDividerLength = 10;

    public SpaceItemDecoration(int leftRight, int topBottom){
         this.leftRight = leftRight;
         this.topBottom = topBottom;
        //创建特定画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public SpaceItemDecoration(int leftRight, int topBottom,int position){
        this(leftRight,topBottom);
        this.position = position;
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
       if (position != -1){
           //左边：到父容器的left内间距位置值
           final int left = parent.getPaddingLeft();
           //右边：到父容器的right内间距位置值
           final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
           final View child = parent.getChildAt(position);
           RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
           //上边：具体的某条分割线的左边以child的(bottom+bottomMargin)位置值
           final int top = child.getBottom() + layoutParams.bottomMargin;
           //下边：top加上指定的高度
           int bottom = top + mPaintDividerLength;
           c.drawRect(left, top, right, bottom, mPaint);
       }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        //竖直方向的
        if (layoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
            //最后一项需要 bottom
            if (parent.getChildAdapterPosition(view) == layoutManager.getItemCount() - 1) {
                outRect.bottom = topBottom;
            }
            outRect.top = topBottom;
            outRect.left = leftRight;
            outRect.right = leftRight;
            //指定位置
            if (position != -1 && parent.getChildAdapterPosition(view) == position){
                outRect.set(0, 0, mPaintDividerLength, 0);
            }
        } else {
            //最后一项需要right
            if (parent.getChildAdapterPosition(view) == layoutManager.getItemCount() - 1) {
                outRect.right = leftRight;
            }
            outRect.top = topBottom;
            outRect.left = leftRight;
            outRect.bottom = topBottom;
        }
    }

}
