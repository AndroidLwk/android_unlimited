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
 * Description:${DESCRIPTION} 版本更新
 */
public class VersionUpdatePopupWindow extends BasePopupWindow {


    public VersionUpdatePopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        private OnPopupClickListener listener;

        public Build(Context context) {
            super(context, R.layout.popupwindow_version_update);
            setOnButtonListener(R.id.popup_version_update_immediately);
        }

        public Build setPopupWindowWidth(int width) {
            super.setPopupWindowWidth(width);
            return this;
        }

        public Build setOnPopupClickListener(OnPopupClickListener l){
            this.listener = l;
            return this;
        }


        @Override
        public BasePopupWindow builder() {
            return new VersionUpdatePopupWindow(this);
        }

        @Override
        public void onClick(int viewId) {
            if (viewId == R.id.popup_version_update_immediately){
                if (listener != null){
                    listener.onUpdateImmediately();
                }
            }
        }

        public interface OnPopupClickListener{
            void onUpdateImmediately();
        }
    }

}
