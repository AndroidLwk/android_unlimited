package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.FansiDirectlyBean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FanSiPotentialRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-10 下午5:36
 * Description:${DESCRIPTION}
 */
public class FanSiDirectlyRecViewAdapter extends RcvBaseAdapter<FansiDirectlyBean.ListBean> {


    public FanSiDirectlyRecViewAdapter(Context context, List<FansiDirectlyBean.ListBean> list) {
        super(context, list);
    }


    @Override
    public void convert(BaseViewHolder holder, FansiDirectlyBean.ListBean bean, int position) {
        holder.setCircleImageResource(R.id.item_fansi_directly_headimg,bean.getHeadimg());
        holder.setText(R.id.item_fansi_directly_name,bean.getNickname());
        holder.setText(R.id.item_fansi_directly_fnum,mContext.getString(R.string.fansi_count,bean.getFnum()));
        holder.setText(R.id.item_fansi_directly_rebate,mContext.getString(R.string.tribute_count,bean.getRebate1()));
        holder.setViewOnClickListener(R.id.item_fansi_directly_layout, v -> {
            if (listener != null){
                listener.onItemClick(bean.getId());
            }
        });
        int vipStatus = bean.getVip();
        switch (vipStatus){
            case -1:
            case 0:
                holder.setBtnText(R.id.item_fansi_directly_vip_type,mContext.getString(R.string.ordinary_member));
                break;
            case 1:
                holder.setBtnText(R.id.item_fansi_directly_vip_type,mContext.getString(R.string.year_member));
                break;
            case 2:
                holder.setBtnText(R.id.item_fansi_directly_vip_type,mContext.getString(R.string.month_member));
                break;
        }
    }

    @Override
    protected void bindEmptyData(BaseViewHolder holder, int position) {
        holder.setViewOnClickListener(R.id.item_fansi_empty_invite, v -> {
            if (listener != null){
                listener.onInviteFriend();
            }
        });
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_fansi_directly;
    }

    @Override
    protected int getEmptyLayoutId() {
        return R.layout.item_empty_fansi;
    }

    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener l){
        this.listener = l;
    }
    public interface OnItemClickListener{
        void onItemClick(int uid);
        void onInviteFriend();
    }

}
