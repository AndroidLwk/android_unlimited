package com.wuxiantao.wxt.mvp.version;

import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:VersionMvpPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-15 下午1:19
 * Description:${DESCRIPTION}
 */
public interface VersionMvpPresenter<V extends VersionView> extends MvpPresenter<V> {
    void getAppCurrentVersion();
}
