package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.adapter.bean.OrderPayModeBean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OrderPayModeRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-22 下午5:55
 * Description:${DESCRIPTION}
 */
public class OrderPayModeRecViewAdapter extends RcvBaseAdapter<OrderPayModeBean> {

    public OrderPayModeRecViewAdapter(Context context, List<OrderPayModeBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, OrderPayModeBean bean, int position) {
        holder.setImageResource(R.id.item_order_pay_mode_icon,bean.getPayIcon());
        holder.setText(R.id.item_order_pay_mode_type,bean.getPayName());
        holder.setVisibility(R.id.item_order_pay_mode_checked,bean.isPaySelected() ? View.VISIBLE : View.GONE);
        holder.setViewOnClickListener(R.id.item_order_pay_mde_layout, v -> {
            if (listener != null){
                listener.OnItemClick(position);
            }
        });
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_order_pay_mode;
    }
}
