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
 * Description:${DESCRIPTION} 红包取现
 */
public class RedBagWithdrawalPopupWindow extends BasePopupWindow {


    public RedBagWithdrawalPopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        private OnPopupClickListener listener;
        private boolean isShare;

        public Build(Context context) {
            super(context, R.layout.popupwindow_withdrawal_red_bag,true);
            setOnButtonListener(R.id.popup_withdrawal_red_bg_share,R.id.popup_withdrawal_red_bg_close);
        }

        public Build setOnPopupClickListener(OnPopupClickListener l){
            this.listener = l;
            return this;
        }

        public Build setPopupWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }

        public Build setMoneyText(String money){
            if (!isEmpty(money)){
                setText(money,R.id.popup_withdrawal_red_bg_money);
            }
            return this;
        }

        @Override
        public void onWindowDismiss() {
            if (listener != null) {
                listener.onConfirm(isShare);
            }
        }

        @Override
        public BasePopupWindow builder() {
            return new RedBagWithdrawalPopupWindow(this);
        }

        @Override
        public void onClick(int viewId) {
            if (viewId == R.id.popup_withdrawal_red_bg_share) {
                isShare = true;
            }
            else if (viewId == R.id.popup_withdrawal_red_bg_close){
                isShare = false;
            }
        }

        public interface OnPopupClickListener{
              void onConfirm(boolean isShare);
        }
    }

}
