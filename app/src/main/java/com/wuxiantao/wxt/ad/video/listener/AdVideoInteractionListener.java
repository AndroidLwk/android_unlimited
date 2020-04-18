package com.wuxiantao.wxt.ad.video.listener;

import com.bytedance.sdk.openadsdk.TTRewardVideoAd;
import com.wuxiantao.wxt.listener.RewardVideoListener;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AdVideoInteractionListener
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-5 下午4:46
 * Description:${DESCRIPTION}
 */
public class AdVideoInteractionListener implements TTRewardVideoAd.RewardAdInteractionListener {

    private RewardVideoListener listener;

    public AdVideoInteractionListener(RewardVideoListener listener){
       this.listener = listener;
    }

    @Override
    public void onAdShow() {
        //rewardVideoAd show

    }

    @Override
    public void onAdVideoBarClick() {
        //rewardVideoAd bar click
    }

    @Override
    public void onAdClose() {
        //rewardVideoAd close
        if (listener != null){
            listener.playCompletion();
        }
    }

    @Override
    public void onVideoComplete() {
        //视频播放完成回调
    }

    @Override
    public void onVideoError() {
        //rewardVideoAd error
    }

    //视频播放完成后，奖励验证回调，rewardVerify：是否有效，rewardAmount：奖励梳理，rewardName：奖励名称
    @Override
    public void onRewardVerify(boolean rewardVerify, int rewardAmount, String rewardName) {

    }

    @Override
    public void onSkippedVideo() {

    }


}
