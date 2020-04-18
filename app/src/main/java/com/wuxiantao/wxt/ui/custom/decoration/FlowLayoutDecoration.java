package com.wuxiantao.wxt.ui.custom.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FlowLayoutDecoration
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-11 下午4:57
 * Description:${DESCRIPTION}
 */
public class FlowLayoutDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public FlowLayoutDecoration(int space) {
        this.space = space;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = space;
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
    }
}
