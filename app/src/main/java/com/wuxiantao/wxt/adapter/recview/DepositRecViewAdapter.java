package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.graphics.Color;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.ToDayDepositBean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DepositRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-26 上午10:01
 * Description:${DESCRIPTION}邀请好友登陆->前日/昨日/今日 获得存款 适配器
 */
public class DepositRecViewAdapter extends RcvBaseAdapter<ToDayDepositBean> {

    public DepositRecViewAdapter(Context context, List<ToDayDepositBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, ToDayDepositBean bean, int position) {
        if (bean.getTitle().contains(mContext.getString(R.string.to_daoy))){
            holder.setTextColor(R.id.item_invite_friend_login_deposit_title, Color.RED);
            holder.setTextColor(R.id.item_invite_friend_login_deposit_dao, Color.RED);
            holder.setTextColor(R.id.item_invite_friend_login_deposit_money, Color.RED);
        }else {
            holder.setTextColor(R.id.item_invite_friend_login_deposit_title,"#AEA4A4");
            holder.setTextColor(R.id.item_invite_friend_login_deposit_dao, "#000000");
            holder.setTextColor(R.id.item_invite_friend_login_deposit_money, "#000000");
        }
        holder.setText(R.id.item_invite_friend_login_deposit_title,bean.getTitle());
        holder.setText(R.id.item_invite_friend_login_deposit_money,bean.getDeposit());
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_invite_friend_login_deposit;
    }
}
