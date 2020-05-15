package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.MyBoxInfo;
import com.wuxiantao.wxt.utils.DensityUtils;
import com.wuxiantao.wxt.utils.ScrapCardHeroUtils;

import java.util.List;

public class MyBackpackRecRecViewAdapter extends RcvBaseAdapter<MyBoxInfo.ListBean> {
    public MyBackpackRecRecViewAdapter(Context context, List<MyBoxInfo.ListBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, MyBoxInfo.ListBean myBackpackBean, int position) {
        holder.setText(R.id.tv_mybackpack_heroName, myBackpackBean.getName());
        holder.setText(R.id.tv_mybakcpack_num, myBackpackBean.getNumber() + "");
        holder.setMargin(R.id.iv_mybackpack_hero, myBackpackBean.getPid() == 4 ? DensityUtils.dip2px(6) : DensityUtils.dip2px(0));
        switch (myBackpackBean.getPid()) {
            case 4://英雄碎片
            case 1://英雄
                holder.setImageResource(R.id.iv_mybackpack_hero, ScrapCardHeroUtils.queryPictureId_knapsack_hero_ft(myBackpackBean.getCard_id()));
                break;
            case 2://皮肤卡
                holder.setImageResource(R.id.iv_mybackpack_hero, ScrapCardHeroUtils.queryPictureId_knapsack_skinCard(myBackpackBean.getCard_id()));
                break;
            case 3://现金卡
                holder.setImageResource(R.id.iv_mybackpack_hero, ScrapCardHeroUtils.queryPictureId_knapsack_crashcard(myBackpackBean.getCard_id()));
                break;

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
