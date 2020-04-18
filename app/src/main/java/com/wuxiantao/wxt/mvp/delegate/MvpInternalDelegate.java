package com.wuxiantao.wxt.mvp.delegate;

import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MvpInternalDelegate
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 上午11:35
 * Description:${DESCRIPTION} MvpInternalDelegate-用于控制MvpPresenter与MvpView
 */
public class MvpInternalDelegate<P extends MvpPresenter,V extends MvpView> {

    private BaseDelegateCallback<P,V> callback;

    public MvpInternalDelegate(BaseDelegateCallback<P,V> callback){
        this.callback = callback;
    }

    public P createPresenter(){
        P p = callback.getPresenter();
        if (p == null){
            p = callback.createPresenter();
        }
        if (p == null){
//            p = callback.createPresenter();
            throw new NullPointerException("callback.createPresenter() is null in MvpInternalDelegate");
        }
        return p;
    }

    public void attachView(){
        P p = callback.getPresenter();
        if (p != null){
            p.attachView(callback.getMvpView());
        }
    }

    public void detachView(){
        callback.getPresenter().detachView();
    }
}
