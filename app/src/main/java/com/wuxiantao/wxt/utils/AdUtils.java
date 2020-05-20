package com.wuxiantao.wxt.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdDislike;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import com.bytedance.sdk.openadsdk.TTRewardVideoAd;
import com.ssm.sp.SPSecuredUtils;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.ad.TTAdManagerHolder;
import com.wuxiantao.wxt.ad.information.InformationInteractionListener;
import com.wuxiantao.wxt.ad.native_interaction.ExpressInteractionListener;
import com.wuxiantao.wxt.ad.native_interaction.NativeAdInteractionListener;
import com.wuxiantao.wxt.ad.splash.listener.SplashAdListener;
import com.wuxiantao.wxt.ad.video.listener.AdVideoDownLoadListener;
import com.wuxiantao.wxt.ad.video.listener.AdVideoInteractionListener;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.listener.RewardVideoListener;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;

import java.lang.ref.WeakReference;
import java.util.List;

import static com.wuxiantao.wxt.config.Constant.INFORMATION_AD_ID;
import static com.wuxiantao.wxt.config.Constant.IS_REVIEW;
import static com.wuxiantao.wxt.config.Constant.NATIVE_INTERACTION_AD_ID;
import static com.wuxiantao.wxt.config.Constant.SPLASH_AD_ID;
import static com.wuxiantao.wxt.config.Constant.VIDEO_AD_ID;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AdUtils
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-9 下午2:49
 * Description:${DESCRIPTION} 开屏广告/激励视频广告
 */
public class AdUtils {


    //开屏广告
    public static void initSplashAd(Activity act, FrameLayout view, ImageView img,SplashAdListener.LoadAdListener loadAdListener) {
        WeakReference<Activity> reference = new WeakReference<>(act);
        //step1:初始化sdk
        TTAdManager mTTAdManager = TTAdManagerHolder.get();
        //step2:(可选，强烈建议在合适的时机调用):申请部分权限，如read_phone_state,防止获取不了imei时候，下载类广告没有填充的问题。
        TTAdManagerHolder.get().requestPermissionIfNecessary(reference.get());
        //step3:创建TTAdNative对象,用于调用广告请求接口
        TTAdNative mTTAdNative = mTTAdManager.createAdNative(reference.get());
        //step4:请求广告，调用开屏广告异步请求接口，对请求回调的广告作渲染处理
        mTTAdNative.loadSplashAd(splashAdSlot, new SplashAdListener(view, img,loadAdListener));
    }

    //激励视频
    public static void initRewardVideoAd(Activity act,RewardVideoListener listener) {
        WeakReference<Activity> reference = createReference(act);
        LoadingDialog dialog = new LoadingDialog.Build(act).build();
        TTAdNative mTTAdNative = createTTAdNative(reference);
        showDialog(dialog,reference);
        //step5:请求广告
        mTTAdNative.loadRewardVideoAd(videoAdSlot, new TTAdNative.RewardVideoAdListener() {
            @Override
            public void onError(int code, String message) {
                ToastUtils.error(message);
                disMissDialog(dialog,reference);
            }

            //视频广告的素材加载完毕，比如视频url等，在此回调后，可以播放在线视频，网络不好可能出现加载缓冲，影响体验。
            @Override
            public void onRewardVideoAdLoad(TTRewardVideoAd ttRewardVideoAd) {
                if (ttRewardVideoAd != null){
                    disMissDialog(dialog,reference);
                    //设置是否在视频播放页面展示下载bar
                    ttRewardVideoAd.setShowDownLoadBar(true);
                    //展示广告，并传入广告展示的场景
                    ttRewardVideoAd.showRewardVideoAd(reference.get());
                    ttRewardVideoAd.setRewardAdInteractionListener(new AdVideoInteractionListener(listener));
                    ttRewardVideoAd.setDownloadListener(new AdVideoDownLoadListener());
                }
            }
            //视频广告加载后，视频资源缓存到本地的回调，在此回调后，播放本地视频，流畅不阻塞。
            @Override
            public void onRewardVideoCached() {
//                ToastUtils.error("视频看完了哈");
            }
        });
    }


    //插屏广告
    public static void initNativeInteractionAd(Activity act, ExpressInteractionListener listener) {
        boolean isReview = (boolean) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(IS_REVIEW,false);
        if (isReview){
            return;
        }
        WeakReference<Activity> reference = createReference(act);
        LoadingDialog dialog = new LoadingDialog.Build(act).build();
        TTAdNative mTTAdNative = createTTAdNative(reference);
        showDialog(dialog,reference);
        mTTAdNative.loadInteractionExpressAd(nativeAdSlot, new TTAdNative.NativeExpressAdListener() {
            @Override
            public void onError(int code, String message) {
                disMissDialog(dialog,reference);
                LogUtils.loge("加载插屏广告错误:" + code + "," + message);
                ToastUtils.error(message);
            }

            @Override
            public void onNativeExpressAdLoad(List<TTNativeExpressAd> list) {
                disMissDialog(dialog,reference);
                if (list == null || list.size() <= 0){
                    return;
                }
                 mTTAd = list.get(0);
                mTTAd.setExpressInteractionListener(new NativeAdInteractionListener(mTTAd,reference,listener));
                if (mTTAd.getInteractionType() != TTAdConstant.INTERACTION_TYPE_DOWNLOAD){
                    return;
                }
                mTTAd.setDownloadListener(new AdVideoDownLoadListener());
                mTTAd.render();
            }
        });
    }


    //信息流广告
    public static void initInformationInteractionAd(Activity act, ViewGroup viewGroup) {
        boolean isReview = (boolean) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(IS_REVIEW,false);
        if (isReview){
            return;
        }
        WeakReference<Activity> reference = createReference(act);
        TTAdNative mTTAdNative = createTTAdNative(reference);
        mTTAdNative.loadNativeExpressAd(informationAdSlot, new TTAdNative.NativeExpressAdListener() {
            @Override
            public void onError(int code, String message) {
                ToastUtils.error("load ads error code:" + code + ",msg:" + message);
                viewGroup.removeAllViews();
                viewGroup.setVisibility(View.GONE);
            }

            @Override
            public void onNativeExpressAdLoad(List<TTNativeExpressAd> list) {
                if (list == null || list.isEmpty()){
                    return;
                }
                mTTAd = list.get(0);
                mTTAd.setExpressInteractionListener(new InformationInteractionListener(viewGroup));

                //使用默认个性化模板中默认dislike弹出样式
                mTTAd.setDislikeCallback(reference.get(), new TTAdDislike.DislikeInteractionCallback() {
                    @Override
                    public void onSelected(int position, String value) {
                        //用户选择不喜欢原因后，移除广告展示
                        viewGroup.removeAllViews();
                        viewGroup.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancel() {

                    }
                });

                if (mTTAd.getInteractionType() != TTAdConstant.INTERACTION_TYPE_DOWNLOAD){
                    return;
                }
                mTTAd.setDownloadListener(new AdVideoDownLoadListener());
                mTTAd.render();
            }
        });
    }


    public static void destroy(){
        if (mTTAd != null){
            mTTAd.destroy();
        }
    }

    private static TTNativeExpressAd mTTAd;


    private static WeakReference<Activity> createReference(Activity act){
        return new WeakReference<>(act);
    }

    private static TTAdNative createTTAdNative(WeakReference<Activity> reference){
        //step1:初始化sdk
        TTAdManager mTTAdManager = TTAdManagerHolder.get();
        //step2:(可选，强烈建议在合适的时机调用):申请部分权限，如read_phone_state,防止获取不了imei时候，下载类广告没有填充的问题。
        TTAdManagerHolder.get().requestPermissionIfNecessary(reference.get());
        //step3:创建TTAdNative对象,用于调用广告请求接口
        return mTTAdManager.createAdNative(reference.get());
    }


    //创建视频广告请求参数AdSlot,具体参数含义参考文档
    private static AdSlot videoAdSlot = new AdSlot.Builder()
            .setCodeId(VIDEO_AD_ID)
            .setSupportDeepLink(true)
            .setImageAcceptedSize(1080, 1920)
            .setRewardName("金币") //奖励的名称
            .setRewardAmount(3)  //奖励的数量
            .setUserID("")//用户id,必传参数
            .setMediaExtra("media_extra") //附加参数，可选
            //必填参数，期望视频的播放方向：TTAdConstant.HORIZONTAL 或 TTAdConstant.VERTICAL
            .setOrientation(TTAdConstant.VERTICAL)
            .build();

    //创建开屏广告请求参数AdSlot,具体参数含义参考文档
    private static AdSlot splashAdSlot = new AdSlot.Builder()
            .setCodeId(SPLASH_AD_ID)
            .setSupportDeepLink(true)
            .setImageAcceptedSize(1080, 1920)
            .build();

    //创建插屏广告请求参数AdSlot,具体参数含义参考文档
    private static AdSlot nativeAdSlot = new AdSlot.Builder()
            //广告位id
            .setCodeId(NATIVE_INTERACTION_AD_ID)
            .setSupportDeepLink(true)
            .setAdCount(1) //请求广告数量为1到3条
            //这个参数设置即可，不影响模板广告的size
            .setImageAcceptedSize(640,320)
            //期望模板广告view的size,单位dp
            .setExpressViewAcceptedSize(600,900)
            .build();


    //创建信息流广告请求参数AdSlot,具体参数含义参考文档
    private static AdSlot informationAdSlot = new AdSlot.Builder()
            //广告位id
            .setCodeId(INFORMATION_AD_ID)
            .setSupportDeepLink(true)
            .setAdCount(1) //请求广告数量为1到3条
            //这个参数设置即可，不影响模板广告的size
            .setImageAcceptedSize(640, 300)
            //期望模板广告view的size,单位dp
            .setExpressViewAcceptedSize(
                    (float) (BaseApplication.getAppContext().getResources().getDimension(R.dimen.dp_60) * 1.78),
                    (float) (BaseApplication.getAppContext().getResources().getDimension(R.dimen.dp_140) * 1.78))
            .build();

    private static void showDialog(LoadingDialog dialog,WeakReference<Activity> reference){
        if (dialog != null && !reference.get().isFinishing()){
            dialog.showLoadingDialog();
        }
    }

    private static void disMissDialog(LoadingDialog dialog,WeakReference<Activity> reference){
        if (dialog != null && !reference.get().isFinishing()){
            dialog.dismissLoadingDialog();
        }
    }
}
