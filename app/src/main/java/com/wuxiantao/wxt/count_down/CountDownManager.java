package com.wuxiantao.wxt.count_down;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;

import com.wuxiantao.wxt.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CountDownManager
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-15 下午7:52
 * Description:${DESCRIPTION} 倒计时通用封装 RxJava+Lifecycle
 */
public class CountDownManager implements LifecycleObserver {

    private DisposableObserver mDisposableObserver;
    private CountDownCallBack callBack;
    private DisposableObserver<Long>  observer;

    public CountDownManager(){

    }

    public void setCallBack(CountDownCallBack callBack) {
        this.callBack = callBack;
    }

    public CountDownManager startCountDown(@NonNull long time) {
        return startCountDown(time,TimeUnit.SECONDS);
    }

    private long getTimePeriod(TimeUnit unit){
        switch (unit){
            case DAYS:
                return 0;
            case HOURS:
                return 0;
            case MINUTES:
                return 0;
            case SECONDS:
                return 1;
            case MILLISECONDS:
                return 1000;
            case MICROSECONDS:
                return 10000;
        }
        return 1;
    }

    private CountDownManager startCountDown(@NonNull long s,TimeUnit unit) {
        long time = s - getCurrentTimeStamp();
        if (time <= 0){
            return this;
        }
        if (observer == null){
            observer = createObserver();
        }
        //设置0延迟，每隔一秒发送一条数据
        mDisposableObserver = Observable.interval(0,getTimePeriod(unit) , unit)
                .subscribeOn(Schedulers.io())
                //UI线程
                .observeOn(AndroidSchedulers.mainThread())
                //设置总共发送的次数
                .take(time)
                .map(aLong -> time - aLong)
                .subscribeWith(observer);
        return this;
    }


    public CountDownManager start(@NonNull long time) {
        if (observer == null){
            observer = createObserver();
        }
        //设置0延迟，每隔一秒发送一条数据
        mDisposableObserver = Observable.interval(0,1,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                //UI线程
                .observeOn(AndroidSchedulers.mainThread())
                //设置总共发送的次数
                .take(time)
                .map(aLong -> time - aLong)
                .subscribeWith(observer);
        return this;
    }

    private DisposableObserver<Long> createObserver(){
         observer = new DisposableObserver<Long>() {
             @Override
             public void onNext(Long aLong) {
                 if (callBack != null) {
                     callBack.onNext(aLong);
                 }
             }

             @Override
             public void onError(Throwable e) {

             }

             @Override
             public void onComplete() {
                 if (callBack != null) {
                     callBack.onComplete();
                 }
             }
         };
         return observer;
    }

    //取消订阅
    public    void cancelCallback() {
        if (mDisposableObserver != null && !mDisposableObserver.isDisposed()) {
            mDisposableObserver.dispose();
            mDisposableObserver = null;
            LogUtils.loge("取消订阅==============>");
        }
    }

    private long getCurrentTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(LifecycleOwner source) {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {

    }

    //高版本的UIController默认已经实现了LifecycleOwner接口，可以直接监听生命周期
    //想要使用的话必须在UIController里面使用getLifecycle().addObserver(CountDownManager.getInstance());
    //getLifecycle() UIController自带方法
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destroy() {
        cancelCallback();
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny(LifecycleOwner source, Lifecycle.Event event) {

    }


}
