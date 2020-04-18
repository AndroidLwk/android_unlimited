package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.adapter.bean.WithdrawBean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:RedEnvelopeRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-3 下午7:10
 * Description:${DESCRIPTION}
 */
public class RedEnvelopeRecViewAdapter extends RcvBaseAdapter<WithdrawBean> {

    public RedEnvelopeRecViewAdapter(Context context, List<WithdrawBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, WithdrawBean bean, int position) {
        holder.setViewBackGroundResources(R.id.item_red_envelope_withdraw_layout,bean.isSelected()
                ? R.mipmap.withdraw_money_selected : R.mipmap.withdraw_money_selecte);
        holder.setTextColor(R.id.item_red_envelope_withdraw_money,bean.isSelected()
                ? Color.parseColor("#FF360a") : Color.parseColor("#333333"));
        holder.setText(R.id.item_red_envelope_withdraw_money,mContext.getString(R.string.yuan_regex_s,bean.getMoney()));
        holder.setViewOnClickListener(R.id.item_red_envelope_withdraw_layout, v -> {
            if (listener != null){
                listener.OnItemClick(position);
            }
        });
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_red_envelope_withdraw;
    }
}
