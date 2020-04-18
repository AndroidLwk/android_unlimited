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
 * Description:${DESCRIPTION} 操作倒计时对话框
 */
public class OperatePromptPopupWindow extends BasePopupWindow {


    public OperatePromptPopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        public Build(Context context) {
            super(context, R.layout.popupwindow_operate_prompt);
            setOnButtonListener(R.id.popup_operate_prompt_know);
        }

        public Build setCountDowntime(String diff) {
            setText(diff, R.id.popup_operate_prompt_time);
            return this;
        }

        @Override
        public BasePopupWindow builder() {
            return new OperatePromptPopupWindow(this);
        }


        @Override
        public void onClick(int viewId) {
            if (viewId == R.id.popup_operate_prompt_know) {

            }
        }

    }

}
