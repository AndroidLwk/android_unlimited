package com.wuxiantao.wxt.ui.popupwindow;

import android.content.Context;
import android.graphics.Color;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ActivationFriendPopupWindow
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-19 上午10:44
 * Description:${DESCRIPTION} 邀请权益对话框
 */
public class InviteInterestPopupWindow extends BasePopupWindow {


    public InviteInterestPopupWindow(Build build) {
        super(build);
    }

    public static class Build extends BaseBuild {

        private OnSharePlatformListener listener;

        public Build(Context context) {
            super(context, R.layout.popupwindow_invite_interest);
            setOnButtonListener(
                    R.id.popup_invite_interest_close,
                    R.id.popup_invite_interest_wechat_friend,
                    R.id.popup_invite_interest_wechat_group);
        }

        public Build setOnSharePlatformListener(OnSharePlatformListener listener){
            this.listener = listener;
            return this;
        }

        @Override
        public BasePopupWindow builder() {
            return new InviteInterestPopupWindow(this);
        }

        @Override
        public void onClick(int viewId) {
            switch (viewId){
                case R.id.popup_invite_interest_close:

                    break;
                case R.id.popup_invite_interest_wechat_friend:
                    if (listener != null){
                        listener.onWeChatFriend();
                    }
                    break;
                case R.id.popup_invite_interest_wechat_group:
                    if (listener != null){
                        listener.onWeChatGroup();
                    }
                    break;
            }
        }

        public interface OnSharePlatformListener{
            void onWeChatFriend();
            void onWeChatGroup();
        }
    }

}
