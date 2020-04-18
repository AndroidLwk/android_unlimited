package com.wuxiantao.wxt.ad.splash.listener;

import com.bytedance.sdk.openadsdk.TTAppDownloadListener;
import com.wuxiantao.wxt.utils.ToastUtils;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SplashDownloadListener
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-5 下午5:08
 * Description:${DESCRIPTION}
 */
public class SplashDownloadListener implements TTAppDownloadListener {

    boolean hasShow = false;

    @Override
    public void onIdle() {

    }

    @Override
    public void onDownloadActive(long totalBytes, long currBytes, String fileName, String appName) {
        if (!hasShow){
            ToastUtils.success("下载中...");
            hasShow = true;
        }
    }

    @Override
    public void onDownloadPaused(long totalBytes, long currBytes, String fileName, String appName) {
        ToastUtils.warning("已暂停下载...");
    }

    @Override
    public void onDownloadFailed(long totalBytes, long currBytes, String fileName, String appName) {
        ToastUtils.error("下载失败...");
    }

    @Override
    public void onDownloadFinished(long totalBytes, String fileName, String appName) {

    }

    @Override
    public void onInstalled(String fileName, String appName) {

    }
}
