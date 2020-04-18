package com.wuxiantao.wxt.net.download;

import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DownloadMvpPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-25 下午3:26
 * Description:${DESCRIPTION}
 */
public interface DownloadMvpPresenter<V extends DownloadView> extends MvpPresenter<V> {

    void onDownloadFile(String url);
}
