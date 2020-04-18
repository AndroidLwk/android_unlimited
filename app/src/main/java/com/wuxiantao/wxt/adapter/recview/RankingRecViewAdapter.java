package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.TodayShareDayBean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DepositRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-26 上午10:01
 * Description:${DESCRIPTION} 邀请好友登陆->今日邀请奖励排行榜 适配器
 */
public class RankingRecViewAdapter extends RcvBaseAdapter<TodayShareDayBean.ListBean> {

    public RankingRecViewAdapter(Context context, List<TodayShareDayBean.ListBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, TodayShareDayBean.ListBean bean, int position) {
        holder.setText(R.id.item_invite_friend_login_ranking_count,String.valueOf(position + 1));
        holder.setRoundImageResource(R.id.item_invite_friend_login_ranking_icon,bean.getHeadimg());
        holder.setText(R.id.item_invite_friend_login_ranking_name,bean.getNickname());
        holder.setText(R.id.item_invite_friend_login_ranking_deposit,String.valueOf(bean.getDeposit_amount()));
        holder.setVisibility(R.id.item_invite_friend_login_ranking_hat,position < 3 ? View.VISIBLE : View.INVISIBLE);
        int hat1 = R.mipmap.vip1_hat;
        int hat2 = R.mipmap.vip2_hat;
        int hat3 = R.mipmap.vip3_hat;
        holder.setViewBackGroundResources(R.id.item_invite_friend_login_ranking_hat,position == 0 ? hat1 : position == 1 ? hat2 : hat3);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_invite_friend_login_ranking;
    }
}
