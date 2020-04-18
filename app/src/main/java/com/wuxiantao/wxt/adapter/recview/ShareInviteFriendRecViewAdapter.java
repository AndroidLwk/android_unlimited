package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ShareInviteFriendRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-20 上午9:58
 * Description:${DESCRIPTION} 分享邀请好友进群列表
 */
public class ShareInviteFriendRecViewAdapter extends RcvBaseAdapter<String> {

    private List<Integer> iconList = new ArrayList<>();

    public ShareInviteFriendRecViewAdapter(Context context, List<String> list) {
        super(context, list);
        iconList.add(R.mipmap.share_wechat_friend);
        iconList.add(R.mipmap.share_wechat_group);
    }

    @Override
    protected void convert(BaseViewHolder holder, String s, int position) {
        holder.setImageResource(R.id.item_share_invite_friend_icon,iconList.get(position));
        holder.setText(R.id.item_share_invite_friend_title,s);
        holder.setViewOnClickListener(R.id.item_share_invite_friend_layout, v -> {
            if (listener != null){
                listener.onItemClick(position);
            }
        });
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_share_invite_friend;
    }

    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener l){
        this.listener = l;
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
