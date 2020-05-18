package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.ScrapingCardBean;

import java.util.List;

public class ScrapingCardFragmentTwoAdapter extends RcvBaseAdapter<ScrapingCardBean> {

    public ScrapingCardFragmentTwoAdapter(Context context, List<ScrapingCardBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, ScrapingCardBean bean, int position) {
        holder.setText(R.id.tv_num_title, bean.getNumTitle());
        holder.setText(R.id.sbt_finish, bean.getIsFinish() == 0 ? "去完成" : "已完成");
        holder.setViewBackGroundColor(R.id.sbt_finish, "#FA5858");
        holder.setTextColor(R.id.sbt_finish, "#ffffff");
        holder.setViewEnabled(R.id.sbt_finish, bean.getIsFinish() == 0 ? true : false);
        holder.setViewOnClickListener(R.id.sbt_finish, v -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });
    }


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_scrapingcard_b;
    }

    private OnItemClickListener listener;

    public void setOnItemBtnClickListener(OnItemClickListener l) {
        this.listener = l;
    }

    public interface OnItemClickListener {
        void onItemClick(int potion);
    }
}
