package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.TaoBaoOrderListBean;
import com.wuxiantao.wxt.utils.MathUtils;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.DECIMAL_BIT;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoOrderListRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-24 下午12:47
 * Description:${DESCRIPTION}
 */
public class TaoBaoOrderListRecViewAdapter extends RcvBaseAdapter<TaoBaoOrderListBean.ListBean> {

    private int status;

    public TaoBaoOrderListRecViewAdapter(Context context, List<TaoBaoOrderListBean.ListBean> list,int status) {
        super(context, list);
        this.status = status;
    }

    @Override
    protected void convert(BaseViewHolder holder, TaoBaoOrderListBean.ListBean bean, int position) {
        String url = bean.getPict_url();
        if (url.startsWith("//")){
            url = "http:" + url;
        }
        holder.setRoundImageResource(R.id.item_taobao_order_list_img,url,R.drawable.no_banner,5.0f);
        String orderType = bean.getOrder_type();
        holder.setTaoBaoTagText(R.id.item_taobao_order_list_title,bean.getItem_title(),orderType);
        holder.setText(R.id.item_taobao_order_list_money, MathUtils.formatCurrency(bean.getPrice(),DECIMAL_BIT));
        holder.setText(R.id.item_taobao_order_list_deposit,MathUtils.formatCurrency(String.valueOf(bean.getRebate()),DECIMAL_BIT));
        holder.setViewOnClickListener(R.id.item_taobao_order_list_layout, v -> {
             if (listener != null){
                 listener.OnItemClick(bean.getId());
             }
         });
        int orderStaus = bean.getTk_status();
        //订单状态：0全部，1验证中，2验证完成，3失效
        switch (status){
            case 0:
                setOrderStatus(holder,orderStaus);
                break;
            case 1:
                holder.setText(R.id.item_taobao_order_list_deposit_type,mContext.getString(R.string.expected_commission));
                break;
            case 2:
                holder.setText(R.id.item_taobao_order_list_deposit_type,mContext.getString(R.string.seek_commission));
                break;
            case 3:
                holder.setText(R.id.item_taobao_order_list_deposit_type,mContext.getString(R.string.lost_commission));
                break;
        }
    }

    private void setOrderStatus(BaseViewHolder holder,int orderStatus){
        //订单状态:0 12验证中,3 14验证成功 ,13已失效
        switch (orderStatus){
            case 0:
            case 12:
                holder.setText(R.id.item_taobao_order_list_deposit_type,mContext.getString(R.string.expected_commission));
                break;
            case 3:
            case 14:
                holder.setText(R.id.item_taobao_order_list_deposit_type,mContext.getString(R.string.seek_commission));
                break;
            case 13:
                holder.setText(R.id.item_taobao_order_list_deposit_type,mContext.getString(R.string.lost_commission));
                break;
        }
    }


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_taobao_order_list;
    }
}
