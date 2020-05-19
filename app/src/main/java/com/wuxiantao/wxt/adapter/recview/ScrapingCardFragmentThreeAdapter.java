package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.MyCardInfo;

import java.util.List;

public class ScrapingCardFragmentThreeAdapter extends RcvBaseAdapter<MyCardInfo.ListBean> {
    public ScrapingCardFragmentThreeAdapter(Context context, List<MyCardInfo.ListBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, MyCardInfo.ListBean bean, int position) {
        holder.setVisibility(R.id.lt_one, View.GONE);
        holder.setVisibility(R.id.lt_two, View.GONE);
        holder.setVisibility(R.id.lt_three, View.GONE);
        holder.setVisibility(R.id.sbt_startHall_scrapCard, bean.getCard() == 0 ? View.VISIBLE : View.GONE);
        holder.setVisibility(R.id.lt_three, bean.getStatus() == 1 ? View.VISIBLE : View.GONE);
        if (bean.getStatus() == 0) {
            if (bean.getCard_cha() <= bean.getCard_all()) {
                holder.setVisibility(R.id.lt_one, View.VISIBLE);
            } else {
                holder.setVisibility(R.id.lt_two, View.GONE);
            }
        }
        holder.setText(R.id.tv_name_one, bean.getName());
        holder.setText(R.id.tv_name_two, bean.getName());
        holder.setText(R.id.tv_name_three, bean.getName());
        holder.setText(R.id.tv_money, bean.getMoney() + "元");
        holder.setText(R.id.tv_day_num, bean.getDay() + "天");
        holder.setText(R.id.tv_bronze_num, "距离平台分红还差" + bean.getCard_cha() + "张");
        holder.setText(R.id.tv_gold_num, "目前" + bean.getStatus_total() + "人正参与分红");
        int progress_one = 0;
        if (bean.getCard_all() + bean.getCard_cha() > 0) {
            progress_one = bean.getCard_all() / (bean.getCard_all() + bean.getCard_cha());
        }
        holder.setProgress(R.id.progress_scrapingcard_b, progress_one);
        holder.setOnCheckedListener(R.id.sbt_startHall_scrapCard, (view, isChecked) -> listener.Onclick(bean));
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_scrapingcardfragment_a;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void Onclick(MyCardInfo.ListBean bean);
    }
}
