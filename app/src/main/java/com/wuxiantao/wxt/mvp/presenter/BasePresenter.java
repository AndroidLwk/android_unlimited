package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.mvp.view.MvpView;

import java.lang.ref.WeakReference;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BasePresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 上午11:31
 * Description:${DESCRIPTION}
 */
public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private WeakReference<V> reference;

    @Override
    public void attachView(V view) {
        if (view == null){
            throw new NullPointerException("view can not be null when in attachview() in BasePresenter");
        }else {
            if (reference == null){
                //将View置为弱引用，当view被销毁回收时，依赖于view的对象（即Presenter）也会被回收，而不会造成内存泄漏
                reference = new WeakReference<>(view);
            }
        }
    }

    @Override
    public void detachView() {
        if (reference != null){
            reference.clear();
            reference = null;
        }
    }

    public V getMvpView(){
        if (isAttach()){
            return reference.get();
        }else {
            throw new NullPointerException("have you ever called attachView() in BasePresenter");
        }
    }

    private boolean isAttach(){
        return reference != null && reference.get() != null;
    }

    @Override
    public void onDestroy() {

    }

}
