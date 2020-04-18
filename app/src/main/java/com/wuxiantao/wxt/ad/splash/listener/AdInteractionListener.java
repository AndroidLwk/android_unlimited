package com.wuxiantao.wxt.ad.splash.listener;

import android.view.View;

import com.bytedance.sdk.openadsdk.TTSplashAd;
import com.wuxiantao.wxt.utils.LogUtils;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AdInteractionListener
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-5 下午5:07
 * Description:${DESCRIPTION}
 */
public class AdInteractionListener  implements TTSplashAd.AdInteractionListener {

    private SplashAdListener.LoadAdListener loadAdListener;

    public AdInteractionListener(SplashAdListener.LoadAdListener loadAdListener){
        this.loadAdListener = loadAdListener;
    }

    @Override
    public void onAdClicked(View view, int i) {
        LogUtils.loge("开屏广告点击==========>");
    }

    @Override
    public void onAdShow(View view, int i) {
        LogUtils.loge("开屏广告展示==========>");
    }

    @Override
    public void onAdSkip() {
        LogUtils.loge("开屏广告跳过==========>");
        goToMainActivity();
    }

    @Override
    public void onAdTimeOver() {
        LogUtils.loge("开屏广告倒计时结束==========>");
        goToMainActivity();
    }

    private void goToMainActivity(){
        if (loadAdListener != null){
            loadAdListener.onGoToMenuActivity();
        }
    }
}
