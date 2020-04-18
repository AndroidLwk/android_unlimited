package com.wuxiantao.wxt.ui.popupwindow;

import android.app.Activity;
import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;
import com.wuxiantao.wxt.utils.AdUtils;
import com.wuxiantao.wxt.utils.PropUtils;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ActivationFriendPopupWindow
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-19 上午10:44
 * Description:${DESCRIPTION} 开牌之后获得奖励
 */
public class OpenCardPopupWindow extends BasePopupWindow {


    public OpenCardPopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        public Build(Context context) {
            super(context, R.layout.popupwindow_open_card,true);
            setOnButtonListener(R.id.popup_open_card_close, R.id.popup_open_card_confirm,
                    R.id.popup_open_card_double);
            AdUtils.initInformationInteractionAd((Activity) context, findViewById(R.id.popup_open_card_ads));
        }

        private OnClickListener listener;

        public Build setOnClickListener(OnClickListener listener) {
            this.listener = listener;
            return this;
        }

        public Build setWindowType(String title, int dragon_id,String num) {
            if (isEmpty(title) || isEmpty(num)) {
                return this;
            }
            setText(getString(R.string.open_card_successed,title,num), R.id.popup_open_card_title);
            int res_id = PropUtils.queryPictureId(dragon_id);
            setImageResource(R.id.popup_open_card_dragon, res_id);
            return this;
        }

        public Build setWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }

        @Override
        public void onWindowDismiss() {
            super.onWindowDismiss();
            if (listener != null) {
                listener.onConfirm(isVideoDouble);
            }
        }

        @Override
        public BasePopupWindow builder() {
            return new OpenCardPopupWindow(this);
        }

        @Override
        public void onClick(int viewId) {
            switch (viewId) {
                case R.id.popup_open_card_close:
                case R.id.popup_open_card_confirm:
                    isVideoDouble = false;
                    break;
                case R.id.popup_open_card_double:
                    isVideoDouble = true;
                    break;
            }
        }

        private boolean isVideoDouble;

        public interface OnClickListener {
            void onConfirm(boolean isVideoDouble);
        }
    }

}
