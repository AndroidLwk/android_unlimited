package com.wuxiantao.wxt.adapter.base;

import android.support.v7.widget.RecyclerView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:RcvViewParameter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-14 下午4:17
 * Description:${DESCRIPTION}
 */
public class RcvViewParameter<T> {

    private RecyclerView.LayoutManager manager;
    private RecyclerView.ItemDecoration decoration;
    private RcvBaseAdapter<T> adapter;

    public RecyclerView.LayoutManager getManager() {
        return manager;
    }

    public void setManager(RecyclerView.LayoutManager manager) {
        this.manager = manager;
    }

    public RecyclerView.ItemDecoration getDecoration() {
        return decoration;
    }

    public void setDecoration(RecyclerView.ItemDecoration decoration) {
        this.decoration = decoration;
    }

    public RcvBaseAdapter<T> getAdapter() {
        return adapter;
    }

    public void setAdapter(RcvBaseAdapter<T> adapter) {
        this.adapter = adapter;
    }

    public RcvViewParameter(RecyclerView.LayoutManager manager, RecyclerView.ItemDecoration decoration,
                            RcvBaseAdapter<T> adapter) {
        this.manager = manager;
        this.decoration = decoration;
        this.adapter = adapter;
    }

    public RcvViewParameter(){}
}
