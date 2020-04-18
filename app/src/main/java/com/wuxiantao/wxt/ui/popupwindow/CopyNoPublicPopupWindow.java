package com.wuxiantao.wxt.ui.popupwindow;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;

import static com.wuxiantao.wxt.config.Constant.PUBLIC_NICKNAME;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ActivationFriendPopupWindow
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-19 上午10:44
 * Description:${DESCRIPTION} 公众号复制
 */
public class CopyNoPublicPopupWindow extends BasePopupWindow {


    public CopyNoPublicPopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        private OnCopyListener listener;

        public Build(Context context) {
            super(context, R.layout.popupwindow_copy_no_public);
            setOnButtonListener(R.id.popup_copy_no_public_close,R.id.popup_copy_no_public_copy);
        }


        public Build setPopupWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }

        public Build setOnCopyListener(OnCopyListener listener){
            this.listener = listener;
            return this;
        }

        @Override
        public BasePopupWindow builder() {
            return new CopyNoPublicPopupWindow(this);
        }


        @Override
        public void onClick(int viewId) {
            switch (viewId){
                case R.id.popup_copy_no_public_close:

                    break;
                case R.id.popup_copy_no_public_copy:
                    if (listener != null){
                        listener.onCopyNoPublic(PUBLIC_NICKNAME);
                    }
                    break;
            }
        }
    }

    public interface OnCopyListener {
        void onCopyNoPublic(String publicNo);
    }
}
