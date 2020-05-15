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
        holder.setImageResource(R.id.iv_hero, heroScrolllBean.getHero_img_background());
        holder.setText(R.id.tv_heroscroll_two_num, heroScrolllBean.getHero_num() + "");
        holder.setVisibility(R.id.tv_heroscroll_two_num, heroScrolllBean.getIsHave() == 0 ? View.GONE : View.VISIBLE);
        holder.setViewOnClickListener(R.id.rt_item, v -> {
            listener.onItemClick(heroScrolllBean,position);
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
        void onItemClick(HeroScrolllBean heroScrolllBean,int potion);
    }
}
