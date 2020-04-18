package com.wuxiantao.wxt.ui.popupwindow;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.ShareInviteFriendRecViewAdapter;
import com.wuxiantao.wxt.ui.custom.decoration.CommonItemDecoration;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;

import java.util.Arrays;
import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ActivationFriendPopupWindow
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-19 上午10:44
 * Description:${DESCRIPTION} 分享邀请好友进群
 */
public class ShareInviteFriendPopupWindow extends BasePopupWindow {


    public ShareInviteFriendPopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        private OnShreChannelListener listener;

        public Build(Context context) {
            super(context, R.layout.popupwindow_share_invite_friend);
            setOnButtonListener(R.id.popup_share_invite_friend_close);
            init();
        }

        public Build setOnShreChannelListener(OnShreChannelListener listener){
            this.listener = listener;
            return this;
        }

        private void init(){
            LinearLayoutManager manager = new LinearLayoutManager(mContext);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            CommonItemDecoration itemDecoration = new CommonItemDecoration(200,0);
            List<String> list = Arrays.asList(mContext.getResources().getStringArray(R.array.share_title));
            ShareInviteFriendRecViewAdapter adapter = new ShareInviteFriendRecViewAdapter(mContext,list);
            setRcvConfig(R.id.popup_share_invite_friend_rv,manager,itemDecoration,adapter);
            adapter.setOnItemClickListener(position -> {
                switch (position){
                    //分享好友
                    case 0:
                        if (listener != null){
                            listener.onShareWeChatFriend();
                        }
                        break;
                        //分享朋友圈
                    case 1:
                        if (listener != null){
                            listener.onShareWeChatGroup();
                        }
                        break;
                }
                dismiss();
            });
        }




        @Override
        public BasePopupWindow builder() {
            return new ShareInviteFriendPopupWindow(this);
        }

        @Override
        public void onClick(int viewId) {
            switch (viewId){
                case R.id.popup_share_invite_friend_close:

                    break;
            }
        }

        public interface OnShreChannelListener{
            void onShareWeChatFriend();
            void onShareWeChatGroup();
        }
    }

}
