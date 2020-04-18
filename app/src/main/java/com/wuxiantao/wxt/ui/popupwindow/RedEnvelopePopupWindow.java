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
 * Description:${DESCRIPTION} 分享得现金/升级得现金/邀请新用户得现金
 */
public class RedEnvelopePopupWindow extends BasePopupWindow {


    public RedEnvelopePopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        private OnPopupClickListener listener;

        public Build(Context context) {
            super(context, R.layout.popupwindow_red_envelope,true);
            setOnButtonListener(R.id.popup_red_envelope_layout);
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
                setText(money,R.id.popup_red_envelope_money);
            }
            return this;
        }

        public Build setHeadImg(String img){
            if (!isEmpty(img)){
                setCircleImageResource(R.id.popup_red_envelope_icon,img);
            }
            return this;
        }

        @Override
        public void onWindowDismiss() {
            if (listener != null){
                listener.onConfirm();
            }
        }

        @Override
        public BasePopupWindow builder() {
            return new RedEnvelopePopupWindow(this);
        }

        @Override
        public void onClick(int viewId) {
            if (viewId == R.id.popup_red_envelope_layout){

            }
        }

        public interface OnPopupClickListener{
              void onConfirm();
        }
    }

}
