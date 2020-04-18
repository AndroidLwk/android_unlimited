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
 * Description:${DESCRIPTION} 淘宝授权窗口
 */
public class ApprovePopupWindow extends BasePopupWindow {


    public ApprovePopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        private OnPopupClickListener listener;

        public Build(Context context) {
            super(context, R.layout.popupwindow_approve);
            setOnButtonListener(R.id.popup_approve_approve, R.id.popup_approve_close);
        }

        public Build setPopupWindowWidth(int width) {
            super.setPopupWindowWidth(width);
            return this;
        }

        public Build setOnPopupClickListener(OnPopupClickListener l){
            this.listener = l;
            return this;
        }

        public Build setPopupWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }

        public Build setOnKeyBackListener(OnKeyBackListener listener) {
            super.setOnKeyBackListener(listener);
            return this;
        }

        @Override
        public BasePopupWindow builder() {
            return new ApprovePopupWindow(this);
        }

        @Override
        public void onClick(int viewId) {
            switch (viewId){
                case R.id.popup_approve_approve:
                    if (listener != null){
                        listener.onApprove();
                    }
                    break;
                case R.id.popup_approve_close:
                    if (listener != null){
                        listener.onClose();
                    }
                    break;
            }
        }

        public interface OnPopupClickListener{
            void onApprove();
            void onClose();
        }
    }

}
