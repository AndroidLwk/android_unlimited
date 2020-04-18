package com.wuxiantao.wxt.ad.native_interaction;

import android.app.Activity;
import android.view.View;

import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import com.wuxiantao.wxt.utils.ToastUtils;

import java.lang.ref.WeakReference;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:NativeAdInteractionListener
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-17 下午3:44
 * Description:${DESCRIPTION}
 */
public class NativeAdInteractionListener implements TTNativeExpressAd.AdInteractionListener {

    private TTNativeExpressAd mTTAd;
    private WeakReference<Activity> reference;
    private ExpressInteractionListener listener;

    public NativeAdInteractionListener(TTNativeExpressAd mTTAd, WeakReference<Activity> reference,ExpressInteractionListener listener){
        this.mTTAd = mTTAd;
        this.reference = reference;
        this.listener = listener;
    }

    @Override
    public void onAdDismiss() {
        //广告关闭
    }

    @Override
    public void onAdClicked(View view, int type) {
        //广告被点击
        if (listener != null){
            listener.onAdClicked(view,type);
        }
    }

    @Override
    public void onAdShow(View view, int type) {
        //广告展示

    }

    @Override
    public void onRenderFail(View view, String msg, int code) {
        ToastUtils.error(msg + "code:" + code);
    }

    @Override
    public void onRenderSuccess(View view, float width, float height) {
        //渲染成功 返回view的宽高 单位 dp
        if (mTTAd != null){
            mTTAd.showInteractionExpressAd(reference.get());
        }
    }
}
