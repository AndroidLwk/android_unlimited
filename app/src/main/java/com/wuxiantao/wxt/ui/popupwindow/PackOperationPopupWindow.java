package com.wuxiantao.wxt.ui.popupwindow;

import android.content.Context;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.MyBoxInfo;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;

public class PackOperationPopupWindow extends BasePopupWindow {
    public PackOperationPopupWindow(BaseBuild build) {
        super(build);
    }

    public static class Build extends BaseBuild {
        public Build(Context context) {
            super(context, R.layout.popupwindow_packoperation);
            setOnButtonListener(R.id.sbt_goOpenCard, R.id.sbt_cardTransfer, R.id.sbt_carUse, R.id.sbt_synthesis, R.id.sbt_discard, R.id.iv_close);
        }

        public Build setWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }

        public Build setButton(MyBoxInfo.ListBean myBackpackBean) {
            setViewVisibility(R.id.sbt_goOpenCard, myBackpackBean.getPid() == 226 ? View.VISIBLE : View.GONE);
            setViewVisibility(R.id.sbt_cardTransfer, myBackpackBean.getIs_send() == 1 ? View.VISIBLE : View.GONE);
            setViewVisibility(R.id.sbt_carUse, myBackpackBean.getIs_can_use() == 1 ? View.VISIBLE : View.GONE);
            setViewVisibility(R.id.sbt_synthesis, myBackpackBean.getIs_suipian() == 1 ? View.VISIBLE : View.GONE);
            setViewVisibility(R.id.sbt_discard, myBackpackBean.getIs_discard() == 1 ? View.VISIBLE : View.GONE);
            return this;
        }

        @Override
        public BasePopupWindow builder() {
            return new PackOperationPopupWindow(this);
        }

        @Override
        public void onClick(int viewId) {
            switch (viewId) {
                case R.id.sbt_goOpenCard:
                    listener.goOpenCard();
                    break;
                case R.id.sbt_cardTransfer:
                    listener.cardTransfer();
                    break;
                case R.id.sbt_carUse:
                    listener.carUse();
                    break;
                case R.id.sbt_synthesis:
                    listener.synthesis();
                    break;
                case R.id.sbt_discard:
                    listener.discard();
                    break;
                case R.id.iv_close:
                    dismiss();
                    break;
            }
        }

        public Build setOnPopupClickListener(OnPopupClickListener listener) {
            this.listener = listener;
            return this;
        }

        private OnPopupClickListener listener;

        public interface OnPopupClickListener {
            void goOpenCard();//去刮卡

            void cardTransfer();//转赠

            void carUse();//使用

            void synthesis();//合成

            void discard();//丢弃
        }
    }
}
