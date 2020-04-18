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
 * Description:${DESCRIPTION} 隐私政策弹窗
 */
public class PrivacyPolicyPopupWindow extends BasePopupWindow {


    public PrivacyPolicyPopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        public Build(Context context) {
            super(context, R.layout.popupwindow_privacy_policy);
            setOnButtonListener(R.id.popup_privacy_policy_refuse,R.id.popup_privacy_policy_agree);
        }

        @Override
        public BasePopupWindow builder() {
            return new PrivacyPolicyPopupWindow(this);
        }


        @Override
        public void onClick(int viewId) {
            //拒绝
            if (viewId == R.id.popup_privacy_policy_refuse){

            }
            //同意
            else if (viewId == R.id.popup_privacy_policy_agree){

            }
        }
    }

}
