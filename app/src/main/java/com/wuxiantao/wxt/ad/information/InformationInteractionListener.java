package com.wuxiantao.wxt.ad.information;

import android.view.View;
import android.view.ViewGroup;

import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import com.wuxiantao.wxt.utils.LogUtils;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:InformationInteractionListener
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-20 下午5:17
 * Description:${DESCRIPTION}
 */
public class InformationInteractionListener implements TTNativeExpressAd.ExpressAdInteractionListener {

    private ViewGroup mExpressContainer;

    public InformationInteractionListener(ViewGroup viewGroup){
        this.mExpressContainer = viewGroup;
    }

    @Override
    public void onAdClicked(View view, int type) {
        //广告被点击
    }

    @Override
    public void onAdShow(View view, int type) {
        //广告展示
    }

    @Override
    public void onRenderFail(View view,String msg, int code) {
        //渲染失败
        if (mExpressContainer != null){
            mExpressContainer.removeAllViews();
        }
        LogUtils.loge("onRenderFail============>" + msg + ",code:" + code);
    }

    @Override
    public void onRenderSuccess(View view,float width, float height) {
        //返回view的宽高 单位 dp
        //在渲染成功回调时展示广告，提升体验
        if (mExpressContainer != null){
            mExpressContainer.setVisibility(View.VISIBLE);
            mExpressContainer.removeAllViews();
            mExpressContainer.addView(view);
        }
    }
}
