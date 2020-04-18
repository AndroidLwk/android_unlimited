package com.wuxiantao.wxt.ui.popupwindow;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ActivationFriendPopupWindow
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-19 上午10:44
 * Description:${DESCRIPTION} 开启红包对话框
 */
public class OpenRedEnvelopePopupWindow extends BasePopupWindow {


    public OpenRedEnvelopePopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        public Build(Context context) {
            super(context, R.layout.popupwindow_open_red_envelope,false);
            setOnButtonListener(R.id.popup_open_red_envelope_close,R.id.popup_open_red_envelope_open);
        }

        private OnClickListener listener;

        public Build setOnClickListener(OnClickListener listener) {
            this.listener = listener;
            return this;
        }

        public Build setGoldBalance(String gold) {
            if (!isEmpty(gold)) {
                setText(getString(R.string.my_gold,gold),R.id.popup_open_red_envelope_title);
                return this;
            }
            return this;
        }

        public Build setWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }

        @Override
        public BasePopupWindow builder() {
            return new OpenRedEnvelopePopupWindow(this);
        }

        @Override
        public void onClick(int viewId) {
            switch (viewId) {
                case R.id.popup_open_red_envelope_close:
                    dismiss();
                    break;
                case R.id.popup_open_red_envelope_open:
                    startAnim(findViewById(R.id.popup_open_red_envelope_open));
                    break;
            }
        }

        private void startAnim(View view){
            ObjectAnimator animator = ObjectAnimator.ofFloat(view,"RotationY",0,180);
            //旋转不停顿
            animator.setInterpolator(new LinearInterpolator());
            //设置动画重复次数
            animator.setRepeatCount(1);
            //重复模式
            animator.setRepeatMode(ObjectAnimator.RESTART);
            //旋转时长
            animator.setDuration(900);
            //开始旋转
            animator.start();
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if (listener != null){
                        listener.onOpenRedEnvelope();
                        dismiss();
                    }
                }
            });
        }

        public interface OnClickListener {
            void onOpenRedEnvelope();
        }

    }

}
