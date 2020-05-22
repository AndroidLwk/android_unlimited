package com.wuxiantao.wxt.ui.popupwindow;

import android.content.Context;
import android.text.TextUtils;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;
import com.wuxiantao.wxt.utils.ToastUtils;

/**
 * 背包选择数量
 */
public class MyPackNumPoupWindow extends BasePopupWindow {
    public MyPackNumPoupWindow(BaseBuild build) {
        super(build);
    }

    public static class Build extends BaseBuild {
        public Build(Context context) {
            super(context, R.layout.popupwindow_mypacknum);
            setOnButtonListener(R.id.sbt_confirm, R.id.sbt_cancel);
        }

        public Build setTitle(String title1, String title2) {
            setText(title1, R.id.tv_title);
            setText(title2, R.id.tv_edit_title);
            return this;
        }

        public MyPackNumPoupWindow.Build setWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }

        private OnConfirmClickListener listener;
        public Build setConfirmClickListener(OnConfirmClickListener listener) {
            this.listener = listener;
            return this;
        }
        @Override
        public BasePopupWindow builder() {
            return new MyPackNumPoupWindow(this);
        }

        @Override
        public void onClick(int viewId) {
            switch (viewId) {
                case R.id.sbt_confirm:
                    if (TextUtils.isEmpty(getTextString(R.id.edit_num))) {
                        ToastUtils.showToast("请输入数量");
                        return;
                    }
                    listener.onConfirm(getTextString(R.id.edit_num));
                    break;
                case R.id.sbt_cancel:
                    dismiss();
                    break;
            }
        }
    }


    public interface OnConfirmClickListener {
        void onConfirm(String num);
    }
}
