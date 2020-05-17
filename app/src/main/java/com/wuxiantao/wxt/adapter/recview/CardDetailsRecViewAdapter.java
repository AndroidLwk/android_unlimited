package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.BalanceDetailBean;
import com.wuxiantao.wxt.bean.ScratchCardDetailsBean;

import java.util.List;

/**
 * Copyright (C), 成都都爱玩科技有限公司
 * Date: 2020/5/17--18:13
 * Description: 刮刮卡明细的适配器
 * Author: lht
 */
public class CardDetailsRecViewAdapter extends RcvBaseAdapter<ScratchCardDetailsBean.ListBean> {

    public CardDetailsRecViewAdapter(Context context, List<ScratchCardDetailsBean.ListBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, ScratchCardDetailsBean.ListBean bean, int position) {
        holder.setText(R.id.itv_balance_details_time,bean.getTime());

        String money = bean.getNum();
        if (money.contains("-")){
            holder.setTextColor(R.id.itv_balance_details_dec,mContext.getResources().getColor(R.color.red));
            holder.setText(R.id.itv_balance_details_dec,bean.getMsg()+" "+bean.getNum());
        }else {
            holder.setTextColor(R.id.itv_balance_details_dec,mContext.getResources().getColor(R.color.limegreen));
            holder.setText(R.id.itv_balance_details_dec,bean.getMsg()+" +"+bean.getNum());
        }
        holder.setViewOnClickListener(R.id.ill_balance_details, v -> {
            if (listener != null){
                listener.OnItemClick(position);
            }
        });
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_balance_details;
    }
}
