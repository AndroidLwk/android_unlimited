package com.wuxiantao.wxt.ui.popupwindow;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;
import com.wuxiantao.wxt.utils.BigDecimalUtils;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ActivationFriendPopupWindow
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-19 上午10:44
 * Description:${DESCRIPTION} 收益大厅->达成目标弹窗
 */
public class FinishAimsPopupWindow extends BasePopupWindow {


    public FinishAimsPopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        private OnWindClickListener listener;

        public Build(Context context) {
            super(context, R.layout.popupwindow_finish_aims);
            setOnButtonListener(R.id.popup_finish_aims_confirm);
        }

        public Build setOnWindClickListener(OnWindClickListener listener){
            this.listener = listener;
            return this;
        }

        public Build setWindowData(String income,String money,String speed){
            //收益
            setText(BigDecimalUtils.round(income),R.id.popup_finish_aims_income);
            //下一段目标
            setText(BigDecimalUtils.round(money),R.id.popup_finish_aims_money);
            //加速权益
            setText(BigDecimalUtils.round(speed),R.id.popup_finish_aims_speed);
            return this;
        }

        @Override
        public BasePopupWindow builder() {
            return new FinishAimsPopupWindow(this);
        }


        @Override
        public void onClick(int viewId) {
            if (viewId == R.id.popup_finish_aims_confirm){
                if (listener != null){
                    listener.onWindowClick();
                }
            }
        }

        public interface OnWindClickListener{
            void onWindowClick();
        }
    }

}
