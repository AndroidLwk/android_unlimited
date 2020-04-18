package com.wuxiantao.wxt.net.download;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.wuxiantao.wxt.mvp.presenter.BasePresenter;
import com.wuxiantao.wxt.net.base.DownObserver;
import com.wuxiantao.wxt.net.callback.ReqProgressCallBack;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseDownloadPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-25 下午3:27
 * Description:${DESCRIPTION}
 */
public class BaseDownloadPresenter<V extends DownloadView> extends BasePresenter<V> implements ReqProgressCallBack {

    private V  view;


    protected void onDownloadFile(DownloadModel model,String url){
        if (view == null){
            view = getMvpView();
        }
        DownObserver<ResponseBody> observer = new DownObserver<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody body) {
                new DownLoadThread(view,body).start();
            }
            @Override
            public void onFailure(String errorMsg) {
                view.downLoadFileFailure(errorMsg);
            }
        };
        model.onDownloadFile(observer,url,this);
    }


    private  class DownLoadThread extends Thread{

        private ResponseBody body;
        private byte[] bys;
        private Bitmap bitmap;
        private V view;
        DownLoadThread(V view,ResponseBody body){
            this.body = body;
            this.view = view;
        }

        @Override
        public void run() {
            try {
                bys = body.bytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bitmap = BitmapFactory.decodeByteArray(bys, 0, bys.length);
            view.downLoadFileSuccess(bitmap);
        }
    }


    @Override
    public void onProgress(String totalSize, String currentSize, String progress, boolean done) {
        if (view != null){
            view.onProgress(totalSize,currentSize,progress,done);
        }
    }


}
