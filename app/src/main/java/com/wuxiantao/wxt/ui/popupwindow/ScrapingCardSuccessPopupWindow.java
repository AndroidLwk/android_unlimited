package com.wuxiantao.wxt.ui.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;
import com.wuxiantao.wxt.utils.AdUtils;

public class ScrapingCardSuccessPopupWindow extends BasePopupWindow {


    public ScrapingCardSuccessPopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        public Build(Context context) {
            super(context, R.layout.layout_popupwindow_scrapcard_success, true);
            AdUtils.initInformationInteractionAd((Activity) context, findViewById(R.id.popup_open_card_ads));
            setOnButtonListener(R.id.popup_open_card_close, R.id.popup_open_card_confirm
            );
        }

        private ScrapingCardSuccessPopupWindow.Build.OnClickListener listener;

        public ScrapingCardSuccessPopupWindow.Build setOnClickListener(ScrapingCardSuccessPopupWindow.Build.OnClickListener listener) {
            this.listener = listener;
            return this;
        }

        public Build setWindowData(String msg, String img) {
            if (isEmpty(msg)) {
                return this;
            }
            if (msg.contains("成功") || msg.contains("签到")) {
                setText(getString(R.string.pointtocard_text5, msg), R.id.tv_openCard_name);
                setViewVisibility(R.id.lt_shuangbei, View.VISIBLE);
            } else {
                setText(msg, R.id.tv_openCard_name);
                setText("好遗憾!", R.id.popup_open_card_title);
                setText("确定", R.id.popup_open_card_confirm);
                setViewVisibility(R.id.lt_shuangbei, View.GONE);

            }
            if (!TextUtils.isEmpty(img)) {
                setGlide(R.id.popup_open_card_dragon, img);
            }
            return this;
        }

        public Build setWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }
        @Override
        public void onWindowDismiss() {
            super.onWindowDismiss();

        }

        @Override
        public BasePopupWindow builder() {
            return new ScrapingCardSuccessPopupWindow(this);
        }

        @Override
        public void onClick(int viewId) {
            listener.onConfirm(getChecked(R.id.cb_isSeeAds));
        }

        public interface OnClickListener {
            void onConfirm(boolean checkboxStatus);
        }
    }

}
