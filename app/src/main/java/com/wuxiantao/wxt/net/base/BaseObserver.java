package com.wuxiantao.wxt.net.base;

import android.content.Intent;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.mvp.view.MvpView;
import com.wuxiantao.wxt.net.exception.RxExceptionUtil;
import com.wuxiantao.wxt.utils.FileUtils;
import com.wuxiantao.wxt.utils.LogUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.wuxiantao.wxt.config.Constant.RECEIVE_LOGIN;
import static com.wuxiantao.wxt.config.Constant.RESOURCES;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseObserver
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-27 下午7:33
 * Description:${DESCRIPTION} 创建Base抽象类实现Observer 610704
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    private MvpView view;

    public BaseObserver(MvpView view){
        this.view = view;
    }

    public BaseObserver(){

    }

    @Override
    public void onSubscribe(Disposable d) {
        if (view != null){
            view.showLoading();
        }
        LogUtils.loge("onSubscribe=========>");
    }

    @Override
    public void onNext(BaseResponse<T> response) {
        LogUtils.loge("onNext=========>");
        //对基础数据 进行统一处理
        if (response != null && response.isSuccess()){
            if (response.getMsg().contains(RESOURCES.getString(R.string.no_order))){
                onFailure(response.getMsg());
            }else {
                onSuccess(response.getData());
            }
        }else {
            onFailure(response.getMsg());
        }
    }

    @Override
    public void onError(Throwable e) {
        String errorMsg = e.getMessage();
        //保存错误信息到手机
        FileUtils.saveHttpErrorInfoToFile(errorMsg);
        LogUtils.loge("onError=========>");
        LogUtils.loge("getMessage=========>" + errorMsg);
        //发送广播重新登陆
        if (renewLogin(errorMsg)){
            Intent intent = new Intent();
            intent.setAction(RECEIVE_LOGIN);
            BaseApplication.getAppContext().sendBroadcast(intent);
        }
        else {
            onFailure(RxExceptionUtil.exceptionHandler(e));
        }
        if (view != null){
            view.dismissLoading();
        }
    }

    private boolean renewLogin(String errorMsg){
        return errorMsg.contains(RESOURCES.getString(R.string.login_out))
                || errorMsg.contains(RESOURCES.getString(R.string.invalid_token));
    }

    @Override
    public void onComplete() {
        LogUtils.loge("onComplete=========>");
        if (view != null){
            view.dismissLoading();
        }
    }

    public abstract void onSuccess(T t);

    public abstract void onFailure(String errorMsg);
}
