package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.MyBoxInfo;
import com.wuxiantao.wxt.utils.DensityUtils;

import java.util.List;

public class MyBackpackRecRecViewAdapter extends RcvBaseAdapter<MyBoxInfo.ListBean> {
    public MyBackpackRecRecViewAdapter(Context context, List<MyBoxInfo.ListBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, MyBoxInfo.ListBean myBackpackBean, int position) {
        holder.setVisibility(R.id.tv_mybackpack_heroName, TextUtils.isEmpty(myBackpackBean.getName()) ? View.INVISIBLE : View.VISIBLE);
        holder.setText(R.id.tv_mybackpack_heroName, myBackpackBean.getName());
        holder.setText(R.id.tv_mybakcpack_num, myBackpackBean.getNumber() + "");
        holder.setVisibility(R.id.tv_mybakcpack_num, myBackpackBean.getNumber() > 0 ? View.VISIBLE : View.GONE);
        holder.setMargin(R.id.iv_mybackpack_hero, myBackpackBean.getPid() == 5 ? DensityUtils.dip2px(6) : DensityUtils.dip2px(0));
        if (!TextUtils.isEmpty(myBackpackBean.getImg())) {
            holder.setGlide(R.id.iv_mybackpack_hero, myBackpackBean.getImg());
        }
        holder.setViewOnClickListener(R.id.lt_item, v -> listener.onConfirm(myBackpackBean, position));
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_mybackpack;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onConfirm(MyBoxInfo.ListBean myBackpackBean, int position);
    }

}
