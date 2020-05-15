package com.wuxiantao.wxt.ui.popupwindow;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;

public class TradingHallPopupWindow extends BasePopupWindow {
    public TradingHallPopupWindow(BaseBuild build) {
        super(build);
    }

    public static class Build extends BaseBuild {

        public Build(Context context) {
            super(context, R.layout.popupwindow_tradinghall);
            setOnButtonListener(R.id.sbt_confirm);
        }

        @Override
        public BasePopupWindow builder() {
            return new TradingHallPopupWindow(this);
        }
        public TradingHallPopupWindow.Build setWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }
        @Override
        public void onClick(int viewId) {
            dismiss();
        }
    }
}
