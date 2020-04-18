package com.wuxiantao.wxt.ui.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;
import com.wuxiantao.wxt.utils.AdUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.wuxiantao.wxt.config.Constant.FEED_ADS_TIME;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ActivationFriendPopupWindow
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-19 上午10:44
 * Description:${DESCRIPTION} 激活好友 打造抽奖券
 */
public class RedBagActivationPopupWindow extends BasePopupWindow {


    public RedBagActivationPopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        private OnPopupClickListener listener;
        private DisposableObserver<Long>  observer;
        private DisposableObserver mDisposableObserver;

        public Build(Context context) {
            super(context, R.layout.popupwindow_activation_red_bag);
            setOnButtonListener(R.id.popup_activation_one_build,
                    R.id.popup_activation_two_build, R.id.popup_activation_red_bag_close);
            AdUtils.initInformationInteractionAd((Activity) context, findViewById(R.id.popup_activation_red_bag_ads));
            startTimer();
        }

        public Build setOnPopupClickListener(OnPopupClickListener l) {
            this.listener = l;
            return this;
        }

        public Build setPopupWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }

        private void startTimer() {
            //设置0延迟，每隔一秒发送一条数据
            mDisposableObserver = Observable.interval(0,1,TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    //UI线程
                    .observeOn(AndroidSchedulers.mainThread())
                    //设置总共发送的次数
                    .take(FEED_ADS_TIME)
                    .map(aLong -> FEED_ADS_TIME - aLong)
                    .subscribeWith(createObserver());
        }

        private DisposableObserver<Long> createObserver(){
            observer = new DisposableObserver<Long>() {
                @Override
                public void onNext(Long aLong) {
                    setText(getString(R.string.time,aLong),R.id.popup_activation_red_bag_time);
                }

                @Override
                public void onError(Throwable e) {

                }
                @Override
                public void onComplete() {
                    findViewById(R.id.popup_activation_red_bag_close).setVisibility(View.VISIBLE);
                    findViewById(R.id.popup_activation_red_bag_time).setVisibility(View.GONE);
                    setButtonEnabled(R.id.popup_activation_one_build,true);
                }
            };
            return observer;
        }

        @Override
        public void onWindowDismiss() {
            cancelCallback();
            super.onWindowDismiss();
        }


        //取消订阅
        private   void cancelCallback() {
            if (mDisposableObserver != null && !mDisposableObserver.isDisposed()) {
                mDisposableObserver.dispose();
                mDisposableObserver = null;
            }
        }

        public Build setWindowData(int act_num, String imgUrl, int type) {
            setText(getString(R.string.today_3_regex, act_num), R.id.popup_activation_red_bag_count);
            if (type == 2) {
                //助手
                setCircleImageResource(R.id.popup_activation_red_bag_img, R.mipmap.assistant_activa);
            } else {
                setCircleImageResource(R.id.popup_activation_red_bag_img, imgUrl);
            }
            return this;
        }

        public static final int BUILD_SPEED_ONE = 1;
        public static final int BUILD_SPEED_TWO = 2;

        @Override
        public BasePopupWindow builder() {
            return new RedBagActivationPopupWindow(this);
        }

        @Override
        public void onClick(int viewId) {
            switch (viewId) {
                case R.id.popup_activation_one_build:
                    if (listener != null) {
                        listener.onActivation(BUILD_SPEED_ONE);
                    }
                    break;
                case R.id.popup_activation_two_build:
                    if (listener != null) {
                        listener.onActivation(BUILD_SPEED_TWO);
                    }
                    break;
                case R.id.popup_activation_red_bag_close:

                    break;
            }
        }

        public interface OnPopupClickListener {
            void onActivation(int type);
        }
    }

}
