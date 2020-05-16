package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.adapter.bean.HeroScrolllBean;

import java.util.List;

public class HeroScrolllOneAdapter extends RcvBaseAdapter<HeroScrolllBean.ChildBean> {
    public HeroScrolllOneAdapter(Context context, List<HeroScrolllBean.ChildBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, HeroScrolllBean.ChildBean heroScrolllBean, int position) {
        holder.setGlide(R.id.iv_hero_background, heroScrolllBean.getImg());
        holder.setText(R.id.tv_heroscroll_num, heroScrolllBean.getTotal());
        holder.setVisibility(R.id.tv_heroscroll_num, Integer.parseInt(heroScrolllBean.getTotal()) > 0 ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_heroscroll_a;
    }
}
