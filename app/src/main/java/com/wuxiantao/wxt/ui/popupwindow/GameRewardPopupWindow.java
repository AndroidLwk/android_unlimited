package com.wuxiantao.wxt.ui.popupwindow;

import android.content.Context;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;
import com.wuxiantao.wxt.utils.DateUtils;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ActivationFriendPopupWindow
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-19 上午10:44
 * Description:${DESCRIPTION} 分红体验结束弹对话框
 */
public class GameRewardPopupWindow extends BasePopupWindow {


    public GameRewardPopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        public Build(Context context) {
            super(context, R.layout.popupwindow_game_reward,true);
            setOnButtonListener(R.id.popup_game_reward_know);
        }

        //type:类型 1.体验前  2.体验后
        public Build setWindowData(int type,String data){
            if (isEmpty(data)){
                return this;
            }
            String title = mContext.getString(R.string.congratulations);
            if (type == 1){
                setText(mContext.getString(R.string.congratulations_rex,
                        title,mContext.getString(R.string.time_experience)),R.id.popup_game_reward_title);
                setViewVisibility(R.id.popup_game_reward_time, View.VISIBLE);
                setViewVisibility(R.id.popup_game_reward_yuan,View.GONE);
                setText(mContext.getString(R.string.game_reward_end_time),R.id.popup_game_reward_model);
                try {
                    data = DateUtils.timeParse(Long.valueOf(data) - System.currentTimeMillis() / 1000);
                }catch (Exception e){
                    return this;
                }
            }else {
                setViewVisibility(R.id.popup_game_reward_time, View.GONE);
                setViewVisibility(R.id.popup_game_reward_yuan,View.VISIBLE);
                setText(title,R.id.popup_game_reward_title);
                setText(mContext.getString(R.string.game_reward_balance),R.id.popup_game_reward_model);
            }
            setText(data,R.id.popup_game_reward_money);
            return this;
        }


        public Build setPopupWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }


        @Override
        public BasePopupWindow builder() {
            return new GameRewardPopupWindow(this);
        }


        @Override
        public void onClick(int viewId) {
            if (viewId == R.id.popup_game_reward_know) {
                if (listener != null){
                    listener.onWindowDismiss();
                }
            }
        }

        private OnWindowDismissListener listener;

        public Build setOnWindowDismissListener(OnWindowDismissListener listener){
            this.listener = listener;
            return this;
        }
        public interface OnWindowDismissListener{
            void onWindowDismiss();
        }
    }

}
