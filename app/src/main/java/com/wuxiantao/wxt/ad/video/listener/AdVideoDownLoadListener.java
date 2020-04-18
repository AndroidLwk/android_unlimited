package com.wuxiantao.wxt.ad.video.listener;

import com.bytedance.sdk.openadsdk.TTAppDownloadListener;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AdVideoDownLoadListener
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-5 下午4:40
 * Description:${DESCRIPTION} 视频下载监听
 */
public class AdVideoDownLoadListener implements TTAppDownloadListener {


    @Override
    public void onIdle() {

    }

    @Override
    public void onDownloadActive(long totalBytes, long currBytes, String fileName, String appName) {
        //下载中，点击下载区域暂停
    }

    @Override
    public void onDownloadPaused(long totalBytes, long currBytes, String fileName, String appName) {
        //下载暂停，点击下载区域继续
    }

    @Override
    public void onDownloadFailed(long totalBytes, long currBytes, String fileName, String appName) {
        //下载失败，点击下载区域重新下载
    }

    @Override
    public void onDownloadFinished(long totalBytes, String fileName, String appName) {
        //下载完成，点击下载区域重新下载
    }

    @Override
    public void onInstalled(String fileName, String appName) {
        //安装完成，点击下载区域打开
    }
}
