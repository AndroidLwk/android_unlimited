package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.adapter.bean.HeroScrolllBean;

import java.util.List;

public class HeroScrollTwoAdapter extends RcvBaseAdapter<HeroScrolllBean> {
    public HeroScrollTwoAdapter(Context context, List<HeroScrolllBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, HeroScrolllBean heroScrolllBean, int position) {
        holder.setGlide(R.id.iv_hero, heroScrolllBean.getImg());
        holder.setText(R.id.tv_heroscroll_two_num, heroScrolllBean.getTotal());
        holder.setVisibility(R.id.tv_heroscroll_two_num, Integer.parseInt(heroScrolllBean.getTotal()) > 0 ? View.VISIBLE : View.INVISIBLE);
        holder.setViewOnClickListener(R.id.rt_item, v -> {
            listener.onItemClick(heroScrolllBean, position);
        });
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_heroscroll_two;
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(HeroScrolllBean heroScrolllBean, int potion);
    }
}
