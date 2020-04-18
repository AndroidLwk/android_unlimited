package com.wuxiantao.wxt.ui.popupwindow;

import android.content.Context;
import android.text.TextUtils;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ActivationFriendPopupWindow
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-19 上午10:44
 * Description:${DESCRIPTION} 查看邀请我的人
 */
public class InviteMePopupWindow extends BasePopupWindow {


    public InviteMePopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        private OnPopupClickListener listener;
        private String weChat;
        public Build(Context context) {
            super(context, R.layout.popupwindow_invite_me);
            setOnButtonListener(R.id.popup_invite_me_copy, R.id.popup_invite_me_close);
        }

        public Build setPopupWindowWidth(int width) {
            super.setPopupWindowWidth(width);
            return this;
        }


        public <T> Build setCircleImageResource(T url) {
            super.setCircleImageResource(R.id.popup_invite_me_img, url);
            return this;
        }


        public Build setNickNameText(String str) {
            setText(str, R.id.popup_invite_me_name);
            return this;
        }


        public Build setWeChatText(String str) {
            setText(str, R.id.popup_invite_me_wechat);
            this.weChat = str;
            return this;
        }

        public Build setOnPopupClickListener(OnPopupClickListener l) {
            this.listener = l;
            return this;
        }


        public Build setPopupWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }

        @Override
        public BasePopupWindow builder() {
            return new InviteMePopupWindow(this);
        }

        @Override
        public void onClick(int viewId) {
            switch (viewId) {
                case R.id.popup_invite_me_copy:
                    if (listener != null) {
                        if (weChat != null && !TextUtils.isEmpty(weChat)){
                            listener.onWeChatCopy(weChat);
                        }
                    }
                    break;
                case R.id.popup_invite_me_close:

                    break;
            }
        }

        public interface OnPopupClickListener {
            void onWeChatCopy(String weChat);
        }
    }

}
