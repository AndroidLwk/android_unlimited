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
 * Description:${DESCRIPTION} 红包入账流程
 */
public class ArrivalProcessPopupWindow extends BasePopupWindow {


    public ArrivalProcessPopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        public Build(Context context) {
            super(context, R.layout.popupwindow_arrival_preocess);
            setOnButtonListener(R.id.popup_arrival_preocess_close);
        }


        public Build setPopupWindowWidth(int width) {
            super.setPopupWindowWidth(width);
            return this;
        }

        public Build setPopupWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }


        @Override
        public BasePopupWindow builder() {
            return new ArrivalProcessPopupWindow(this);
        }

        @Override
        public void onClick(int viewId) {
            switch (viewId){
                case R.id.popup_arrival_preocess_close:

                    break;
            }
        }
    }

}
