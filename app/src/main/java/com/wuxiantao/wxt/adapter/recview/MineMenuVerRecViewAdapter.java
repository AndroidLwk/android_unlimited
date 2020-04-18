package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ssm.sp.SPSecuredUtils;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.bean.BaseExampleBean;
import com.wuxiantao.wxt.adapter.bean.MineMenuBean;
import com.wuxiantao.wxt.adapter.bean.RecentOrderBean;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.imgloader.GlideImgManager;
import com.wuxiantao.wxt.ui.custom.textview.TaoBaoTagTextView;
import com.wuxiantao.wxt.utils.MathUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import static com.wuxiantao.wxt.adapter.bean.RecentOrderBean.RECENT_ORDER_TAOBAO;
import static com.wuxiantao.wxt.adapter.bean.RecentOrderBean.RECENT_ORDER_TMALL;
import static com.wuxiantao.wxt.adapter.bean.RecentOrderBean.RECENT_ORDER_YOUXUAN;
import static com.wuxiantao.wxt.config.Constant.DECIMAL_BIT;
import static com.wuxiantao.wxt.config.Constant.IS_REVIEW;
import static com.wuxiantao.wxt.config.Constant.TYPE_NORMAL;
import static com.wuxiantao.wxt.config.Constant.TYPE_ORDER_INFO;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoFeaturedMenuRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-30 下午3:08
 * Description:${DESCRIPTION} 我的存款 - >列表适配器
 */
public class MineMenuVerRecViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BaseExampleBean> list;
    private Context mContext;

    public MineMenuVerRecViewAdapter(Context context, List<BaseExampleBean> list){
        this.mContext = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void updateList(List<BaseExampleBean> list){
        this.list = list;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType){
            case TYPE_NORMAL:
                View normalView = LayoutInflater.from(mContext).inflate(R.layout.item_mine_menu,viewGroup,false);
                NormalViewHolder normalHolder = new NormalViewHolder(normalView);
                x.view().inject(normalHolder,normalView);
                return normalHolder;
            case TYPE_ORDER_INFO:
                View orderlView = LayoutInflater.from(mContext).inflate(R.layout.item_mine_menu_order,viewGroup,false);
                OrderViewHolder orderHolder = new OrderViewHolder(orderlView);
                x.view().inject(orderHolder,orderlView);
                return orderHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder h, int position) {
        int viewType = getItemViewType(position);
        switch (viewType){
            case TYPE_NORMAL:
                NormalViewHolder normalHolder = (NormalViewHolder) h;
                MineMenuBean menuBean = (MineMenuBean) list.get(position);
                normalHolder.item_mine_menu_icon.setImageResource(menuBean.getIcon());
                normalHolder.item_mine_menu_title.setText(menuBean.getTitle());
                normalHolder.item_mine_menu_all_order_tv.setVisibility(position == 1 ? View.VISIBLE : View.GONE);
                normalHolder.item_mine_menu_layout.setOnClickListener(v -> {
                    if (listener != null){
                        listener.onItemClick(position);
                    }
                });
                normalHolder.item_mine_menu_all_order_prize_icon.setVisibility(position == 0
                        && menuBean.isShowSmallIcon() ? View.VISIBLE : View.GONE);
                boolean isReview = (boolean) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(IS_REVIEW,false);
                if (list.size() - 1 == position){
                    normalHolder.item_mine_menu_layout.setVisibility(isReview ? View.GONE : View.VISIBLE);
                }else {
                    normalHolder.item_mine_menu_layout.setVisibility(View.VISIBLE);
                }
                break;
            case TYPE_ORDER_INFO:
                OrderViewHolder orderHolder = (OrderViewHolder) h;
                RecentOrderBean orderBean = (RecentOrderBean) list.get(position);
                GlideImgManager.loadRoundImg(mContext,orderBean.getUrl(),
                        orderHolder.item_mine_menu_order_img,5.0f);
                String rate_money = orderBean.getRate_money();
                switch (orderBean.getOrderType()){
                    case RECENT_ORDER_TAOBAO:
                        setTaobaoOrderStatus(orderHolder,orderBean.getOrderStatus());
                        orderHolder.item_mine_menu_order_title.setContentAndTag(orderBean.getTitle(),TaoBaoTagTextView.TYPE_TAOBAO);
                        break;
                    case RECENT_ORDER_TMALL:
                        setTaobaoOrderStatus(orderHolder,orderBean.getOrderStatus());
                        orderHolder.item_mine_menu_order_title.setContentAndTag(orderBean.getTitle(), TaoBaoTagTextView.TYPE_TIANMAO);
                        break;
                    case RECENT_ORDER_YOUXUAN:
                        int vip_level = orderBean.getVip_level();
                        //vip礼包 显示预计会员天数
                        if (vip_level > 0){
                            orderHolder.item_mine_menu_order_deposoit_dao.setVisibility(View.GONE);
                            orderHolder.item_mine_menu_order_deposoit_day.setVisibility(View.VISIBLE);
                            orderHolder.item_mine_menu_order_commission_type.setText(mContext.getString(R.string.expected_member));
                        }else {
                            //不是vip礼包
                            orderHolder.item_mine_menu_order_deposoit_dao.setVisibility(View.VISIBLE);
                            orderHolder.item_mine_menu_order_deposoit_day.setVisibility(View.GONE);
                            setSelfOrderStatus(orderHolder,orderBean.getOrderStatus());
                        }
                        orderHolder.item_mine_menu_order_title.setContentAndTag(orderBean.getTitle(), TaoBaoTagTextView.TYPE_YOUXUAN);
                        break;
                }
                orderHolder.item_mine_menu_order_deposit.setText(rate_money);
                orderHolder.item_mine_menu_order_money.setText(MathUtils.formatCurrency(String.valueOf(orderBean.getMoney()),DECIMAL_BIT));
                orderHolder.item_mine_menu_order_layout.setOnClickListener(v -> {
                    if (listener != null){
                        listener.onViewOrderInfo(orderBean.getId());
                    }
                });
                break;
        }
    }

    private void setSelfOrderStatus(OrderViewHolder holder, int orderStatus){
        //订单状态： 1、验证中;2 3 4 验证完成;5 6 失效
        switch (orderStatus){
            case 1:
                holder.item_mine_menu_order_commission_type.setText(mContext.getString(R.string.expected_commission));
                break;
            case 2:
            case 3:
            case 4:
                holder.item_mine_menu_order_commission_type.setText(mContext.getString(R.string.seek_commission));
                break;
            case 5:
            case 6:
            case 7:
            default:
                holder.item_mine_menu_order_commission_type.setText(mContext.getString(R.string.lost_commission));
                break;
        }
    }

    private void setTaobaoOrderStatus(OrderViewHolder holder, int orderStatus){
        holder.item_mine_menu_order_deposoit_dao.setVisibility(View.VISIBLE);
        holder.item_mine_menu_order_deposoit_day.setVisibility(View.GONE);
        //订单状态:0 12验证中,3 14验证成功 ,13已失效
        switch (orderStatus){
            case 0:
            case 12:
                holder.item_mine_menu_order_commission_type.setText(mContext.getString(R.string.expected_commission));
                break;
            case 3:
            case 14:
                holder.item_mine_menu_order_commission_type.setText(mContext.getString(R.string.seek_commission));
                break;
            case 13:
            default:
                holder.item_mine_menu_order_commission_type.setText(mContext.getString(R.string.lost_commission));
                break;
        }
    }

    private static class NormalViewHolder extends RecyclerView.ViewHolder{
        @ViewInject(R.id.item_mine_menu_layout)
        RelativeLayout item_mine_menu_layout;
        @ViewInject(R.id.item_mine_menu_icon)
        ImageView item_mine_menu_icon;
        @ViewInject(R.id.item_mine_menu_title)
        TextView item_mine_menu_title;
        @ViewInject(R.id.item_mine_menu_all_order_tv)
        TextView item_mine_menu_all_order_tv;
        @ViewInject(R.id.item_mine_menu_all_order_prize_icon)
        ImageView item_mine_menu_all_order_prize_icon;
        private NormalViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private static class OrderViewHolder extends RecyclerView.ViewHolder{
        @ViewInject(R.id.item_mine_menu_order_layout)
        LinearLayout item_mine_menu_order_layout;
        @ViewInject(R.id.item_mine_menu_order_img)
        ImageView item_mine_menu_order_img;
        @ViewInject(R.id.item_mine_menu_order_title)
        TaoBaoTagTextView item_mine_menu_order_title;
        @ViewInject(R.id.item_mine_menu_order_commission_type)
        TextView item_mine_menu_order_commission_type;
        @ViewInject(R.id.item_mine_menu_order_money)
        TextView item_mine_menu_order_money;
        @ViewInject(R.id.item_mine_menu_order_deposit)
        TextView item_mine_menu_order_deposit;
        @ViewInject(R.id.item_mine_menu_order_deposoit_dao)
        TextView item_mine_menu_order_deposoit_dao;
        @ViewInject(R.id.item_mine_menu_order_deposoit_day)
        TextView item_mine_menu_order_deposoit_day;
        private OrderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener l){
        this.listener = l;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onViewOrderInfo(int id);
    }
}
