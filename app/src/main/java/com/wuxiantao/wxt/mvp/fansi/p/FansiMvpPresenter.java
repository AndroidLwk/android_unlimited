package com.wuxiantao.wxt.mvp.fansi.p;

import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FansiMvpPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-5 下午6:28
 * Description:${DESCRIPTION}
 */
public interface FansiMvpPresenter<V extends MvpView> extends MvpPresenter<V> {

    void obtainFansiDetail(int uid);
    void obtainFansi(Map<String, Object> parameters);
}
