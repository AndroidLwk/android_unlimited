package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.adapter.bean.AboutSuperManBean;
import com.wuxiantao.wxt.bean.BalanceDetailBean;

import java.util.List;

/**
 * Copyright (C), 成都都爱玩科技有限公司
 * Date: 2020/5/17--15:44
 * Description: 余额明细的适配器
 * Author: lht
 */
public class BalanceDetailsRecViewAdapter extends RcvBaseAdapter<BalanceDetailBean.ListBean> {

    public BalanceDetailsRecViewAdapter(Context context, List<BalanceDetailBean.ListBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, BalanceDetailBean.ListBean bean, int position) {
        holder.setText(R.id.itv_balance_details_time,bean.getTime());
        holder.setText(R.id.itv_balance_details_dec,bean.getMsg()+"元");
        String money = bean.getMoney();
        if (money.contains("-")){
            holder.setTextColor(R.id.itv_balance_details_dec,mContext.getResources().getColor(R.color.red));
        }else {
            holder.setTextColor(R.id.itv_balance_details_dec,mContext.getResources().getColor(R.color.limegreen));
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
