package com.wuxiantao.wxt.ui.popupwindow;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.FansiDetailBean;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;
import com.wuxiantao.wxt.utils.BigDecimalUtils;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ActivationFriendPopupWindow
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-19 上午10:44
 * Description:${DESCRIPTION} 粉丝信息弹窗
 */
public class FanSiInfoPopupWindow extends BasePopupWindow {


    public FanSiInfoPopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        private OnCopyClickListener listener;

        public Build(Context context) {
            super(context, R.layout.popupwindow_fansi_info);
            setOnButtonListener(
                    R.id.popup_fansi_info_close,
                    R.id.popup_fansi_info_id_copy,
                    R.id.popup_fansi_info_wechat_copy);
        }

        public Build setWindowDatas(FansiDetailBean bean){
            if (bean == null){
                return this;
            }
            setCircleImageResource(R.id.popup_fansi_info_head_img,bean.getInfo().getHeadimg());
            String name = bean.getInfo().getNickname();
            String wechat_no = bean.getInfo().getWechat();
            setText(name,R.id.popup_fansi_info_nick_name);
            setText(bean.getInfo().getShare_code(),R.id.popup_fansi_invite_id);
           if (!isEmpty(wechat_no)){
               setText(getString(R.string.weichat_num),R.id.popup_fansi_wechat_type);
               setText(wechat_no,R.id.popup_fansi_wechat);
           }else {
               setText(getString(R.string.wechat_nickname),R.id.popup_fansi_wechat_type);
               setText(name,R.id.popup_fansi_wechat);
           }
            setText(BigDecimalUtils.round(String.valueOf(bean.getLastcol()),2),R.id.popup_fansi_last_month);
            setText(BigDecimalUtils.round(bean.getRates(),2),R.id.popup_fansi_total);
            setText(getString(R.string.reg_time,mContext.getString(R.string._zero)),R.id.popup_fansi_reg_time);
            return this;
        }

        public Build setOnCopyClickListener(OnCopyClickListener l){
            this.listener = l;
            return this;
        }

        public Build setPopupWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }

        public Build setPopupWindowWidth(int width) {
            super.setPopupWindowWidth(width);
            return this;
        }

        @Override
        public BasePopupWindow builder() {
            return new FanSiInfoPopupWindow(this);
        }




        @Override
        public void onClick(int viewId) {
            switch (viewId){
                case R.id.popup_fansi_info_close:

                    break;
                case R.id.popup_fansi_info_id_copy:
                    if (listener != null){
                        listener.onCopyInviteId(getTextString(R.id.popup_fansi_invite_id));
                    }
                    break;
                case R.id.popup_fansi_info_wechat_copy:
                    if (listener != null){
                        listener.onCopyWeChatId(getTextString(R.id.popup_fansi_wechat));
                    }
                    break;
            }
        }
    }


    public interface OnCopyClickListener{
        void onCopyWeChatId(String wechat);
        void onCopyInviteId(String inviteId);
    }
}
