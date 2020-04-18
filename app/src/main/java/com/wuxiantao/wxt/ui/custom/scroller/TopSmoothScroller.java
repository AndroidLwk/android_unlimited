package com.wuxiantao.wxt.ui.custom.scroller;

import android.content.Context;
import android.support.v7.widget.LinearSmoothScroller;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TopSmoothScroller
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-23 下午1:25
 * Description:${DESCRIPTION}
 */
public class TopSmoothScroller extends LinearSmoothScroller {

    public TopSmoothScroller(Context context) {
        super(context);
    }

    @Override
    protected int getHorizontalSnapPreference() {
        return SNAP_TO_START;
    }

    @Override
    protected int getVerticalSnapPreference() {
        return SNAP_TO_START;
    }
}
