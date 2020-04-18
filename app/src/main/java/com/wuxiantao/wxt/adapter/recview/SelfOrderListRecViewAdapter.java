package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.SelfOrderListBean;
import com.wuxiantao.wxt.utils.MathUtils;
import com.wuxiantao.wxt.utils.RegexUtils;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.DECIMAL_BIT;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SelfOrderListRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-24 下午12:53
 * Description:${DESCRIPTION}
 */
public class SelfOrderListRecViewAdapter extends RcvBaseAdapter<SelfOrderListBean.ListBean> {

    private int status;

    public SelfOrderListRecViewAdapter(Context context, List<SelfOrderListBean.ListBean> list,int status) {
        super(context, list);
        this.status = status;
    }

    @Override
    protected void convert(BaseViewHolder holder,SelfOrderListBean.ListBean bean, int position) {
        holder.setViewOnClickListener(R.id.item_self_order_list_layout, v -> {
            if (listener != null){
                listener.onQueryOrderDetail(bean.getId());
            }
        });
        String imgs = bean.getGoods_image();
        holder.setRoundImageResource(R.id.item_self_order_list_img, RegexUtils.imgUrlSeparate(imgs),R.drawable.no_banner,5.0f);
        holder.setTaoBaoTagText(R.id.item_self_order_list_title,bean.getGoods_title());
        String price = MathUtils.formatCurrency(bean.getPrice(),DECIMAL_BIT);
        holder.setText(R.id.item_self_order_list_money, price);
        holder.setText(R.id.item_self_order_list_deposit,
                MathUtils.formatCurrency(String.valueOf(bean.getRate_money()),DECIMAL_BIT));
        holder.setVisibility(R.id.item_self_order_list_pay_layout,bean.getIs_pay() == 0 ? View.VISIBLE : View.GONE);
        //1.vip礼包 0.不是vip礼包
        int vip_level = bean.getVip_level();
        if (vip_level > 0){
            holder.setVisibility(R.id.item_self_order_list_money_dao,View.GONE);
            holder.setVisibility(R.id.item_self_order_list_money_day,View.VISIBLE);
            holder.setText(R.id.item_self_order_list_money_type,mContext.getString(R.string.expected_member));
        }else {
            holder.setVisibility(R.id.item_self_order_list_money_dao,View.VISIBLE);
            holder.setVisibility(R.id.item_self_order_list_money_day,View.GONE);
            switch (status){
                case 0:
                    //全部订单
                    int orderStatus = bean.getStatus();
                    setOrderStatus(holder,orderStatus);
                    break;
                case 1:
                    //验证中
                    holder.setText(R.id.item_self_order_list_money_type,mContext.getString(R.string.expected_commission));
                    break;
                case 2:
                    //验证成功
                    holder.setText(R.id.item_self_order_list_money_type,mContext.getString(R.string.seek_commission));
                    break;
                case 3:
                    //已失效
                    holder.setText(R.id.item_self_order_list_money_type,mContext.getString(R.string.lost_commission));
                    break;
            }
        }
        holder.setViewOnClickListener(R.id.item_self_order_list_pay, v -> {
            if (listener != null){
                listener.onOrderPay(bean.getOrder_no(),price);
            }
        });
    }


    private void setOrderStatus(BaseViewHolder holder,int orderStatus){
        //订单状态： 1、验证中;2 3 4 验证完成;5 6 失效
        switch (orderStatus){
            case 1:
                holder.setText(R.id.item_self_order_list_money_type,mContext.getString(R.string.expected_commission));
                break;
            case 2:
            case 3:
            case 4:
                holder.setText(R.id.item_self_order_list_money_type,mContext.getString(R.string.seek_commission));
                break;
            case 5:
            case 6:
            case 7:
            default:
                holder.setText(R.id.item_self_order_list_money_type,mContext.getString(R.string.lost_commission));
                break;
        }
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_self_order_list;
    }

    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    public interface OnItemClickListener{
        void onQueryOrderDetail(int id);
        void onOrderPay(String order_no,String money);
    }
}
