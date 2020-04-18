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
 * Description:${DESCRIPTION} 推广语
 */
public class PromotionLanguagePopupWindow extends BasePopupWindow {


    public PromotionLanguagePopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        private OnSharePromotionListener listener;
        private String title;
        private String shareUrl;
        public Build(Context context) {
            super(context, R.layout.popupwindow_promotion_language);
            setOnButtonListener(R.id.popup_promotion_language_share);
        }

        public Build setWindowTextData(String title,String shareUrl){
            this.title = title;
            this.shareUrl = shareUrl;
            setText(title,R.id.popup_promotion_language_title);
            setText(shareUrl,R.id.popup_promotion_language_url);
            return this;
        }


        public Build setOnSharePromotionListener(OnSharePromotionListener listener){
            this.listener = listener;
            return this;
        }

        @Override
        public BasePopupWindow builder() {
            return new PromotionLanguagePopupWindow(this);
        }


        @Override
        public void onClick(int viewId) {
            if (viewId == R.id.popup_promotion_language_share){
                if (listener != null){
                    if (!isEmpty(title) && !isEmpty(shareUrl)){
                        listener.onSharePromotion(title + "\n" + shareUrl);
                    }
                }
            }
        }

        public interface OnSharePromotionListener{
            void onSharePromotion(String content);
        }
    }

}
