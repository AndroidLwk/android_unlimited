package com.wuxiantao.wxt.mvp.delegate;

import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 9:56 8-19
 * Description: ${DESCRIPTION} 创建一个ActivityDelegateImp，实现了ActivityDelegate，在生命周期里控制presenter与MvpView
 * Author: Administrator Shiming-Shi
 */

public class ActivityDelegateImp<P extends MvpPresenter,V extends MvpView> implements ActyvityDelegate {

    private BaseDelegateCallback<P,V> baseDelegateCallback;

    private MvpInternalDelegate<P,V> mvpInternalDelegate;


    //传入BaseDelegateCallback 去控制Presenter
    public ActivityDelegateImp(BaseDelegateCallback<P,V> baseDelegateCallback){
           if (baseDelegateCallback == null){
               throw new NullPointerException("the basemvpDelegateCallback in ActivityDelegateImpn is null");
           }
           this.baseDelegateCallback = baseDelegateCallback;
           mvpInternalDelegate = new MvpInternalDelegate<>(this.baseDelegateCallback);
    }


    @Override
    public void onCreate() {
        mvpInternalDelegate.createPresenter();
        mvpInternalDelegate.attachView();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        mvpInternalDelegate.detachView();
    }
}
