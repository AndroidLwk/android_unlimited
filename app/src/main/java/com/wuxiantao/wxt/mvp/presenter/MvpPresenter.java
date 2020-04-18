package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MvpPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 上午11:30
 * Description:${DESCRIPTION} MvpPresenter-Presenter的基础类，控制的MvpView的子类
 */
public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);

    void detachView();

    void onDestroy();
}
