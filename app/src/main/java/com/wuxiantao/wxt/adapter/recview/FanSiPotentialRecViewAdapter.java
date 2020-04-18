package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.FanSiPotentialBean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FanSiPotentialRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-10 下午5:36
 * Description:${DESCRIPTION}
 */
public class FanSiPotentialRecViewAdapter extends RcvBaseAdapter<FanSiPotentialBean.ListBean> {


    public FanSiPotentialRecViewAdapter(Context context, List<FanSiPotentialBean.ListBean> list) {
        super(context, list);
    }


    @Override
    public void convert(BaseViewHolder holder, FanSiPotentialBean.ListBean bean, int position) {
        holder.setCircleImageResource(R.id.item_fansi_potential_headimg,bean.getHeadimg());
        holder.setText(R.id.item_fansi_potential_name,bean.getNickname());
        long create = bean.getCreate_at();
        holder.setText(R.id.item_fansi_potential_time,mContext.getString(R.string.fansi_validity_period,timediff(create)));
        holder.setViewOnClickListener(R.id.fansi_potential_activation, v -> {
            if (listener != null){
                listener.onActivation(bean.getId(),bean.getHeadimg(),bean.getNickname());
            }
        });
    }

    @Override
    protected void bindEmptyData(BaseViewHolder holder, int position) {
        holder.setViewOnClickListener(R.id.item_fansi_empty_invite, v -> {
            if (listener != null){
                listener.onInviteFriend();
            }
        });
    }

    //计算时间差
    private String timediff(long timeStamp){
        long current = System.currentTimeMillis() / 1000;
        if (sb.length() > 0){
            sb.setLength(0);
        }
        long timediff =  (timeStamp + 7 * 24 * 3600) - current;
        long day = timediff / (24 * 3600);
        long hour = timediff % (24 * 3600) / 3600;
        sb.append(day).append(mContext.getString(R.string.day))
                .append(hour).append(mContext.getString(R.string.hour));
        return sb.toString();
    }

    private StringBuilder sb = new StringBuilder();

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_fansi_potential;
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
        void onActivation(int uid,String img,String name);
        void onInviteFriend();
    }
}
