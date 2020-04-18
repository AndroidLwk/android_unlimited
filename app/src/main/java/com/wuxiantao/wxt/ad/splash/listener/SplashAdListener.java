package com.wuxiantao.wxt.ad.splash.listener;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTSplashAd;
import com.wuxiantao.wxt.utils.ToastUtils;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SplashAdListener
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-5 下午5:10
 * Description:${DESCRIPTION}
 */
public class SplashAdListener implements TTAdNative.SplashAdListener {

    private FrameLayout view;
    private ImageView img;
    private LoadAdListener loadAdListener;

    public SplashAdListener(FrameLayout view,ImageView img,LoadAdListener loadAdListener){
        this.view = view;
        this.img = img;
        this.loadAdListener = loadAdListener;
    }


    @Override
    public void onError(int code, String message) {
        ToastUtils.error(message);
        goToMainActivity();
    }

    @Override
    public void onTimeout() {
        ToastUtils.error("开屏广告加载超时");
        goToMainActivity();
    }

    @Override
    public void onSplashAdLoad(TTSplashAd ttSplashAd) {
        if (ttSplashAd == null) {
            return;
        }
        //获取SplashView
        View splashView = ttSplashAd.getSplashView();
        if (splashView != null) {
            img.setVisibility(View.GONE);
            if (view != null){
                view.removeAllViews();
                //把SplashView 添加到ViewGroup中,注意开屏广告view：width >=70%屏幕宽；height >=50%屏幕高
                view.addView(splashView);
                //设置不开启开屏广告倒计时功能以及不显示跳过按钮,如果这么设置，您需要自定义倒计时逻辑
                //ttSplashAd.setNotAllowSdkCountdown();
            }
        }else {
            goToMainActivity();
        }
        //设置SplashView的交互监听器
        ttSplashAd.setSplashInteractionListener(new AdInteractionListener(loadAdListener));
        if (ttSplashAd.getInteractionType() == TTAdConstant.INTERACTION_TYPE_DOWNLOAD){
            ttSplashAd.setDownloadListener(new SplashDownloadListener());
        }
    }

    private void goToMainActivity(){
        if (loadAdListener != null){
            loadAdListener.onGoToMenuActivity();
        }
    }

    public interface LoadAdListener{
        void onGoToMenuActivity();
    }
}
