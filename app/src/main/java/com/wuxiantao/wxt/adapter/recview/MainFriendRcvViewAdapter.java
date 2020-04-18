package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.bean.MainFriendBean;
import com.wuxiantao.wxt.imgloader.GlideImgManager;
import com.wuxiantao.wxt.ui.custom.button.StateButton;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainFriendRcvViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:20-1-15 上午10:21
 * Description:${DESCRIPTION}
 */
public class MainFriendRcvViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<MainFriendBean> list;

    public MainFriendRcvViewAdapter(Context context,List<MainFriendBean> list){
        this.mContext = context;
        this.list = list;
    }

    public void updateList(List<MainFriendBean> list){
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType){
            case MainFriendBean.TYPE_INVITE:
                View inviteView = LayoutInflater.from(mContext).inflate(R.layout.item_exclusive_friends_invite,viewGroup,false);
                InviteHolder inviteHolder = new InviteHolder(inviteView);
                x.view().inject(inviteHolder,inviteView);
                return inviteHolder;
            case MainFriendBean.TYPE_FRIEND:
                View friendView = LayoutInflater.from(mContext).inflate(R.layout.item_exclusive_friends_work,viewGroup,false);
                FriendHolder friendHolder = new FriendHolder(friendView);
                x.view().inject(friendHolder,friendView);
                return friendHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder h, int position) {
        int viewType = getItemViewType(position);
        switch (viewType){
            case MainFriendBean.TYPE_INVITE:
                InviteHolder inviteHolder = (InviteHolder) h;
                inviteHolder.tweery_icon.setVisibility(position == 0 ? View.VISIBLE : View.INVISIBLE);
                inviteHolder.icon.setOnClickListener(v -> {
                    if (listener != null){
                        listener.onInviteFriend();
                    }
                });
                inviteHolder.invite.setOnClickListener(v -> {
                    if (listener != null){
                        listener.onInviteFriend();
                    }
                });
                break;
            case MainFriendBean.TYPE_FRIEND:
                FriendHolder friendHolder = (FriendHolder) h;
                String url = list.get(position).getUrl();
                String name = list.get(position).getName();
                GlideImgManager.loadCircleImg(mContext,url,friendHolder.icon);
                friendHolder.name.setText(name);
                break;
        }
    }


    private static class InviteHolder extends RecyclerView.ViewHolder{
        @ViewInject(R.id.item_exclusive_friends_invite_icon)
        ImageView icon;
        @ViewInject(R.id.item_exclusive_friends_tweery_icon)
        ImageView tweery_icon;
        @ViewInject(R.id.item_exclusive_friends_invite)
        StateButton invite;
        InviteHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    private static class FriendHolder extends RecyclerView.ViewHolder{
        @ViewInject(R.id.item_exclusive_friends_work_icon)
        ImageView icon;
        @ViewInject(R.id.item_exclusive_friends_work_name)
        TextView name;
        FriendHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onInviteFriend();
    }
}
