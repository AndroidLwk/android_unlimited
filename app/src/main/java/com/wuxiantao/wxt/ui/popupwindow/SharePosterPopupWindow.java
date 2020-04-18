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
 * Description:${DESCRIPTION} 分享专属海报
 */
public class SharePosterPopupWindow extends BasePopupWindow {


    public SharePosterPopupWindow(Build build) {
        super(build);
    }

    public static class Build extends BaseBuild {

        private OnPopupClickListener listener;

        public Build(Context context) {
            super(context, R.layout.popupwindow_share_poster);
            setOnButtonListener(R.id.popup_share_poster_close,R.id.popup_share_good_friend,R.id.popup_share_wechat_ring);
        }

        public Build setPopupWindowWidth(int width) {
            super.setPopupWindowWidth(width);
            return this;
        }

        public <T> Build setRoundImageResource(T url) {
            super.setRoundImageResource(R.id.popup_share_poster_img, url);
            return this;
        }

        public Build setOnPopupClickListener(OnPopupClickListener l){
            this.listener = l;
            return this;
        }


        @Override
        public BasePopupWindow builder() {
            return new SharePosterPopupWindow(this);
        }

        @Override
        public void onClick(int viewId) {
            switch (viewId){
                case R.id.popup_share_poster_close:

                    break;
                //分享微信好友
                case R.id.popup_share_good_friend:
                    if (listener != null){
                        listener.onShareWechat();
                    }
                    break;
                //分享到微信朋友圈
                case R.id.popup_share_wechat_ring:
                    if (listener != null){
                        listener.onShareFriends();
                    }
                    break;
            }
        }

        public interface OnPopupClickListener{
            void onShareWechat();
            void onShareFriends();
        }
    }

}
