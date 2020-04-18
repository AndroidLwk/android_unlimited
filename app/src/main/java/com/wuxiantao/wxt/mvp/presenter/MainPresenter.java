package com.wuxiantao.wxt.mvp.presenter;

import android.graphics.BitmapFactory;

import com.wuxiantao.wxt.mvp.contract.MainContract;
import com.wuxiantao.wxt.mvp.model.MainModel;
import com.wuxiantao.wxt.net.callback.ReqProgressCallBack;
import com.wuxiantao.wxt.utils.LogUtils;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午2:26
 * Description:${DESCRIPTION}
 */
public class MainPresenter extends BasePresenter<MainContract.IMainView> implements MainContract.IMainPresenter, ReqProgressCallBack {

    private MainContract.IMainView view;
    private MainModel mainModel = new MainModel();

    @Override
    public void downLoadImage() {
        if (view == null){
            view = getMvpView();
        }
        mainModel.downLoadImage(observer,this);
    }

    Observer<ResponseBody> observer  = new Observer<ResponseBody>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(ResponseBody body) {
            try {
                byte[] b = body.bytes();
                view.downLoadImageSuccess(BitmapFactory.decodeByteArray(b,0,b.length));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

    @Override
    public void onProgress(String totalSize, String currentSize, String progress, boolean done) {
        LogUtils.loge("totalSize:" + totalSize);
        LogUtils.loge("currentSize:" + currentSize);
        LogUtils.loge("progress:" + progress);
        LogUtils.loge("done:" + done);
    }
}
