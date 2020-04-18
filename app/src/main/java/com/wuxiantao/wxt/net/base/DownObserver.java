package com.wuxiantao.wxt.net.base;

import com.wuxiantao.wxt.net.exception.RxExceptionUtil;
import com.wuxiantao.wxt.utils.LogUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DownObserver
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-9 下午6:04
 * Description:${DESCRIPTION}
 */
public abstract class DownObserver<T> implements Observer<T> {

    protected DownObserver() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.loge("onError=========>");
        LogUtils.loge("getMessage=========>" + e.getMessage());
        onFailure(RxExceptionUtil.exceptionHandler(e));
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);

    public abstract void onFailure(String errorMsg);
}
