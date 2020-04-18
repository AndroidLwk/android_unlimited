package com.wuxiantao.wxt.ui.popupwindow;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;

import static com.wuxiantao.wxt.config.Constant.RESOURCES;
import static com.wuxiantao.wxt.config.Constant.WUXIANTAO_URL;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ActivationFriendPopupWindow
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-19 上午10:44
 * Description:${DESCRIPTION} 潜在粉丝->激活弹窗
 */
public class ActivationFriendPopupWindow extends BasePopupWindow {


    public ActivationFriendPopupWindow(Build build) {
        super(build);
    }

    public static class Build extends BaseBuild {

        public Build(Context context) {
            super(context, R.layout.popupwindow_activation_friend);
            setOnButtonListener(
                    R.id.popup_activation_friend_close,
                    R.id.popup_activation_friend_copy_nick_name,
                    R.id.popup_activation_friend_copy_writing);
        }


        public <T> Build setCircleImageResource(T url) {
            super.setCircleImageResource(R.id.popup_activation_friend_head_img,url);
            return this;
        }


        public Build setNickName(String text) {
            super.setText(text,R.id.popup_activation_friend_nick_name);
            this.name = text;
            return this;
        }

        @Override
        public BasePopupWindow builder() {
            return new ActivationFriendPopupWindow(this);
        }

        @Override
        public void onClick(int viewId) {
            switch (viewId){
                case R.id.popup_activation_friend_close:

                    break;
                case R.id.popup_activation_friend_copy_nick_name:
                    if (listener != null){
                        listener.onCopyWriting(name);
                    }
                    break;
                case R.id.popup_activation_friend_copy_writing:
                    if (listener != null){
                        listener.onCopyWriting(RESOURCES.getString(R.string.activation_fansi_wrting,WUXIANTAO_URL));
                    }
                    break;
            }
        }

        private OnCopyListener listener;
        private String name;

        public Build setOnCopyListener(OnCopyListener listener){
            this.listener = listener;
            return this;
        }

        public interface OnCopyListener {
            void onCopyWriting(String writing);
        }
    }

}
