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
 * Description:${DESCRIPTION} 红包取款机/专属好友/储钱罐
 */
public class RedBagMachinePopupWindow extends BasePopupWindow {


    public RedBagMachinePopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        public Build(Context context) {
            super(context, R.layout.popupwindow_red_bag_machine);
            setOnButtonListener(R.id.popup_red_bag_machine_know);
        }

        public Build setWindowTitle(String title){
            setText(title,R.id.popup_red_bag_machine_title);
            return this;
        }

        public Build setWindowMessage(String msg){
            setText(msg,R.id.popup_red_bag_machine_message);
            return this;
        }

        public Build setPopupWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }

        public Build setLayoutVisibility(int visibility){
            setViewVisibility(R.id.popup_red_bag_machine_layout,visibility);
            setViewVisibility(R.id.popup_red_bag_msg,visibility);
            return this;
        }


        @Override
        public BasePopupWindow builder() {
            return new RedBagMachinePopupWindow(this);
        }


        @Override
        public void onClick(int viewId) {
            if (viewId == R.id.popup_red_bag_machine_know){

            }
        }
    }

}
