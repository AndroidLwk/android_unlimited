package com.wuxiantao.wxt.ui.popupwindow;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;

public class NoticePopupWindow extends BasePopupWindow {
    public NoticePopupWindow(Build build) {
        super(build);
    }

    public static class Build extends BaseBuild {
        public Build(Context context) {
            super(context, R.layout.popupwindow_notice, true);
            setOnButtonListener(R.id.sbt_confirm);
        }

        public Build setWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }

        public Build setContent(String content) {
            setHtml(content, R.id.tv_content);
            return this;
        }

        @Override
        public BasePopupWindow builder() {
            return new NoticePopupWindow(this);
        }

        @Override
        public void onClick(int viewId) {
            switch (viewId) {
                case R.id.sbt_confirm:
                    dismiss();
                    break;
            }
        }


    }

}
