package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.LeaderboardBean;
import com.wuxiantao.wxt.utils.MathUtils;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.DECIMAL_BIT;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:LeaderboarRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-6 下午2:39
 * Description:${DESCRIPTION}
 */
public class LeaderboarRecViewAdapter extends RcvBaseAdapter<LeaderboardBean.ListBean> {

    public LeaderboarRecViewAdapter(Context context, List<LeaderboardBean.ListBean> list) {
        super(context, list);
    }


    @Override
    public void convert(BaseViewHolder holder, LeaderboardBean.ListBean bean, int position) {
        holder.setText(R.id.leader_board_deposit_num,String.valueOf(position + 1));
        holder.setText(R.id.leader_board_deposit_name,bean.getNickname());
        holder.setRoundImageResource(R.id.leader_board_deposit_icon,bean.getHeadimg(),5.0f);
        holder.setText(R.id.leader_board_deposit_count,MathUtils.formatCurrency(String.valueOf(bean.getTotal()),DECIMAL_BIT));
        showItemHatView(holder,position);
        holder.setViewOnClickListener(R.id.item_leader_board_deposit_layout, v -> {
            if (listener != null){
                listener.OnItemClick(bean.getUser_id());
            }
        });
    }

    private void showItemHatView(BaseViewHolder holder,int position){
        switch (position){
            case 0:
                holder.setVisibility(R.id.leader_board_deposit_hat, View.VISIBLE);
                holder.setImageResource(R.id.leader_board_deposit_hat,R.mipmap.vip1_hat);
                break;
            case 1:
                holder.setVisibility(R.id.leader_board_deposit_hat, View.VISIBLE);
                holder.setImageResource(R.id.leader_board_deposit_hat,R.mipmap.vip2_hat);
                break;
            case 2:
                holder.setVisibility(R.id.leader_board_deposit_hat, View.VISIBLE);
                holder.setImageResource(R.id.leader_board_deposit_hat,R.mipmap.vip3_hat);
                break;
            default:
                holder.setVisibility(R.id.leader_board_deposit_hat, View.GONE);
                break;
        }
    }


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_leader_board_deposit;
    }

}
