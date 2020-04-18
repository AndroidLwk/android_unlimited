package com.wuxiantao.wxt.ui.custom.decoration;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:StaggeredDividerItemDecoration
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-19 下午12:01
 * Description:${DESCRIPTION}
 */
public class StaggeredDividerItemDecoration extends RecyclerView.ItemDecoration {

    private int interval;

    public StaggeredDividerItemDecoration(int interval) {
        this.interval = interval;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.left = interval;
        outRect.right = interval;
        outRect.bottom = interval;
        //注释这两行是为了上下间距相同
//        if(parent.getChildAdapterPosition(view)==0){
        outRect.top = interval;
//        }
    }
}
