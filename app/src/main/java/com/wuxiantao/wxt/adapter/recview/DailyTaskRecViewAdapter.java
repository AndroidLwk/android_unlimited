package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.adapter.bean.MyTaskBean;
import com.wuxiantao.wxt.utils.DateUtils;

import java.util.List;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.MY_TASK_ADS_NUM;
import static com.wuxiantao.wxt.config.Constant.MY_TASK_CHECK_IN;
import static com.wuxiantao.wxt.config.Constant.MY_TASK_IS_INVITE;
import static com.wuxiantao.wxt.config.Constant.MY_TASK_IS_ORDER;
import static com.wuxiantao.wxt.config.Constant.MY_TASK_ONLINE_TIME;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DailyTaskRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-8 下午12:44
 * Description:${DESCRIPTION} 日常任务
 */
public class DailyTaskRecViewAdapter extends RcvBaseAdapter<MyTaskBean> {

    private Map<String,Object> dailyTaskMap;

    public DailyTaskRecViewAdapter(Context context, List<MyTaskBean> list,Map<String,Object>  dailyTaskMap) {
        super(context, list);
        this.dailyTaskMap = dailyTaskMap;
    }

    public void upDateMap(Map<String,Object> map){
        this.dailyTaskMap = map;
        this.notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder holder, MyTaskBean myTaskBean, int position) {
        boolean is_check = (boolean) dailyTaskMap.get(MY_TASK_CHECK_IN);
        boolean is_order = (boolean) dailyTaskMap.get(MY_TASK_IS_ORDER);
        boolean is_invite = (boolean) dailyTaskMap.get(MY_TASK_IS_INVITE);
        int online_time = (int) dailyTaskMap.get(MY_TASK_ONLINE_TIME);
        int ads_num = (int) dailyTaskMap.get(MY_TASK_ADS_NUM);
        switch (position) {
            //每日签到
            case 0:
                if (is_check){
                    holder.setText(R.id.item_daily_task_btn,mContext.getString(R.string.check_in_checked));
                }else {
                    holder.setText(R.id.item_daily_task_btn,mContext.getString(R.string.check_in));
                }
                holder.setViewOnClickListener(R.id.item_daily_task_btn, v -> {
                    if (listener != null){
                        listener.onCheckIn();
                    }
                });
                holder.setViewEnabled(R.id.item_daily_task_btn,!is_check);
                break;
            //每日一单
            case 1:
                holder.setViewEnabled(R.id.item_daily_task_btn,!is_order);
                if (!is_order){
                    holder.setText(R.id.item_daily_task_btn,mContext.getString(R.string.go_buy_order));
                }else {
                    holder.setText(R.id.item_daily_task_btn,mContext.getString(R.string.finish));
                }
                holder.setViewOnClickListener(R.id.item_daily_task_btn, v -> {
                    if (listener != null){
                        listener.onBuyOrder();
                    }
                });
                break;
            //邀请好友
            case 2:
                holder.setViewEnabled(R.id.item_daily_task_btn,!is_invite);
                if (!is_invite){
                    holder.setText(R.id.item_daily_task_btn,mContext.getString(R.string.go_invite));
                }else {
                    holder.setText(R.id.item_daily_task_btn,mContext.getString(R.string.finish));
                }
                holder.setViewOnClickListener(R.id.item_daily_task_btn, v -> {
                    if (listener != null){
                        listener.onInviteFriend();
                    }
                });
                break;
            //在线30分钟
            case 3:
                //如果大于30分钟显示已完成
                if (online_time > 30 * 60){
                    holder.setText(R.id.item_daily_task_btn, mContext.getString(R.string.finish));
                }else {
                    String time = DateUtils.secondParse(online_time);
                    holder.setText(R.id.item_daily_task_btn,time);
                }
                holder.setViewEnabled(R.id.item_daily_task_btn,false);
                break;
            //广告贡献
            case 4:
                holder.setViewEnabled(R.id.item_daily_task_btn,ads_num < 3);
                if (ads_num >= 3){
                    holder.setText(R.id.item_daily_task_btn,mContext.getString(R.string.finish));
                }else {
                    holder.setText(R.id.item_daily_task_btn,mContext.getString(R.string.ads_click_num,ads_num));
                }
                holder.setViewOnClickListener(R.id.item_daily_task_btn, v -> {
                    if (listener != null){
                        listener.onAdsDevote();
                    }
                });
                break;
        }
        holder.setImageResource(R.id.item_daily_task_icon, myTaskBean.getIcon());
        holder.setText(R.id.item_daily_task_title, myTaskBean.getTitle());
        holder.setText(R.id.item_daily_task_content, myTaskBean.getContent());
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_daily_task;
    }

    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    public interface OnItemClickListener{
        void onCheckIn();
        void onBuyOrder();
        void onInviteFriend();
        void onAdsDevote();
    }
}
