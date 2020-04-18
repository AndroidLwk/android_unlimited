package com.wuxiantao.wxt.mvp.delegate;

import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseDelegateCallback
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 上午11:36
 * Description:${DESCRIPTION} BaseDelegateCallback-用于对获取MvpView、创建以及获取presenter
 */
public interface BaseDelegateCallback<P extends MvpPresenter,V extends MvpView>{

    void setPresenter();

    P  getPresenter();

    P createPresenter();

    V getMvpView();
}
