package com.wuxiantao.wxt.ui.popupwindow;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;

/**
 * 转赠弹框（刮刮卡）
 */
public class TransferScratchCardPopupWindow extends BasePopupWindow {
    public TransferScratchCardPopupWindow(BaseBuild build) {
        super(build);
    }

    public static class Build extends BaseBuild {
        public OnConfirmClickListener listener;

        public Build(Context context) {
            super(context, R.layout.popwindow_transferscratchcard);
            setOnButtonListener(R.id.sbt_confirm, R.id.sbt_cancel);
        }

        public Build setOnItemClickListener(OnConfirmClickListener listener) {
            this.listener = listener;
            return this;
        }

        public Build setTitleText(String text) {
            setText(text, R.id.tv_title);
            return this;
        }

        @Override
        public BasePopupWindow builder() {
            return new TradingHallPopupWindow(this);
        }

        public TransferScratchCardPopupWindow.Build setWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }

        @Override
        public void onClick(int viewId) {
            switch (viewId) {
                case R.id.sbt_cancel:
                    dismiss();
                    break;
                case R.id.sbt_confirm:
                    listener.onConfirm(getTextString(R.id.edit_id), getTextString(R.id.edit_num), getTextString(R.id.edit_sxxh), getTextString(R.id.edit_pass));
                    break;
            }
        }
    }


    public interface OnConfirmClickListener {
        void onConfirm(String id, String num, String sxxh, String pass);
    }
}
