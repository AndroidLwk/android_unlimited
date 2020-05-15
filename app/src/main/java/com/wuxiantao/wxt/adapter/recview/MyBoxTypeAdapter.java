package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.BoxTypeBean;

import java.util.List;

public class MyBoxTypeAdapter extends RcvBaseAdapter<BoxTypeBean> {

    public MyBoxTypeAdapter(Context context, List<BoxTypeBean> list) {
        super(context, list);
    }


    @Override
    protected void convert(BaseViewHolder holder, BoxTypeBean bean, int position) {
        holder.setVisibility(R.id.iv_seleted, bean.isSeleted() ? View.VISIBLE : View.INVISIBLE);
        holder.setText(R.id.tv_type_name, bean.getName());
        holder.setTextColor(R.id.tv_type_name, bean.isSeleted() ? "#2F3135" : "#565961");
        holder.setViewBackGroundColor(R.id.rt_item_boxType, bean.isSeleted() ? "#ffffff" : "#F5F6F9");
        holder.setViewOnClickListener(R.id.rt_item_boxType, v -> listener.onItemClick(bean, position));
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_mybackpack_boxtype;
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(BoxTypeBean bean, int potion);
    }
}
