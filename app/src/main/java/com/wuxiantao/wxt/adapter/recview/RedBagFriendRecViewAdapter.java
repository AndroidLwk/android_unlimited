package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.FriendListBean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:RedBagFriendRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-15 下午1:42
 * Description:${DESCRIPTION} 红包好友列表
 */
public class RedBagFriendRecViewAdapter extends RcvBaseAdapter<FriendListBean.ListBean> {

    public RedBagFriendRecViewAdapter(Context context, List<FriendListBean.ListBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, FriendListBean.ListBean bean, int position) {
        String name = bean.getNickname();
        String url = bean.getHeadimg();
        holder.setRoundImageResource(R.id.item_rea_bag_friend_list_icon, url, 5.0f);
        holder.setText(R.id.item_rea_bag_friend_list_name, name);
//        holder.setText(R.id.item_rea_bag_friend_list_money, mContext.getString(R.string.yuan_regex, bean.getColloct()));
        //状态:0待激活 1加速中
//        int status = bean.getStatus();
//        switch (status) {
//            case 0:
//                holder.setText(R.id.item_rea_bag_friend_list_status, R.string.resting);
//                holder.setViewBackGroundColor(R.id.item_rea_bag_friend_list_status, "#AF999A");
//                int current_id = bean.getId();
//                int act_num = bean.getAct_num();
//                holder.setViewOnClickListener(R.id.item_rea_bag_friend_list_layout, v -> {
//                    if (listener != null) {
//                        listener.onActivationFriend(current_id,act_num,url);
//                    }
//                });
//                holder.setViewOnClickListener(R.id.item_rea_bag_friend_list_status, v -> {
//                    if (listener != null) {
//                        listener.onActivationFriend(current_id,act_num,url);
//                    }
//                });
//                break;
//            case 1:
//                holder.setText(R.id.item_rea_bag_friend_list_status, R.string.workinging);
//                holder.setViewBackGroundColor(R.id.item_rea_bag_friend_list_status, "#FF5454");
//                break;
//        }
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_red_bag_friend_list;
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onActivationFriend(int id, int act_num, String imgUrl);
    }
}
