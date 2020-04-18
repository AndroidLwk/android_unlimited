package com.wuxiantao.wxt.net.download;

import android.graphics.Bitmap;

import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DownloadView
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-25 下午3:24
 * Description:${DESCRIPTION}
 */
public interface DownloadView extends MvpView {

    void downLoadFileSuccess(Bitmap bitmap);
    void downLoadFileFailure(String failure);
    void onProgress(String totalSize,String currentSize,String progress,boolean done);
}
