package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.adapter.bean.HeroScrolllBean;

import java.util.List;

public class HeroScrolllOneAdapter extends RcvBaseAdapter<HeroScrolllBean> {
    public HeroScrolllOneAdapter(Context context, List<HeroScrolllBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, HeroScrolllBean heroScrolllBean, int position) {
//        holder.setImageResource(R.id.iv_hero_background, heroScrolllBean.getHero_img_background());
//        holder.setText(R.id.tv_heroscroll_num, heroScrolllBean.getHero_num() + "");
        holder.setVisibility(R.id.tv_heroscroll_num, heroScrolllBean.getIsHave() == 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_heroscroll_a;
    }
}
