package com.wuxiantao.wxt.ui.popupwindow;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ActivationFriendPopupWindow
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-19 上午10:44
 * Description:${DESCRIPTION} 新用户专享
 */
public class newUserGiftPopupWindow extends BasePopupWindow {


    public newUserGiftPopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        private OnViewClickListener listener;

        public Build(Context context) {
            super(context, R.layout.popupwindow_new_user_gift);
            setOnButtonListener(R.id.popup_new_user_receive);
        }

        public Build setUserIcon(String head_img){
            if (isEmpty(head_img)){
                return this;
            }
            setCircleImageResource(R.id.popup_new_user_icon,head_img);
            return this;
        }

        public Build setPopupWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }

        public Build setOnViewClickListener(OnViewClickListener listener) {
            this.listener = listener;
            return this;
        }

        @Override
        public BasePopupWindow builder() {
            return new newUserGiftPopupWindow(this);
        }

        @Override
        public void onClick(int viewId) {
            if (viewId == R.id.popup_new_user_receive){
                if (listener != null){
                    listener.onReceiveGift();
                }
            }
        }

        public interface OnViewClickListener{
            void onReceiveGift();
        }
    }

}
