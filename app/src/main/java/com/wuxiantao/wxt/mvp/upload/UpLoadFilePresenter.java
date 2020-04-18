package com.wuxiantao.wxt.mvp.upload;

import com.wuxiantao.wxt.bean.UpdataLoadFileBean;
import com.wuxiantao.wxt.mvp.presenter.BasePresenter;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.callback.ReqProgressCallBack;
import com.wuxiantao.wxt.utils.LogUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BasePresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 上午11:31
 * Description:${DESCRIPTION}
 */
public class UpLoadFilePresenter<V extends UpLoadFileView> extends BasePresenter<V> {

    private UpLoadFileModel model = new UpLoadFileModel();
    private V view;

    public void upLoadFile(File file){
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<UpdataLoadFileBean> observer = new BaseObserver<UpdataLoadFileBean>(view){
            @Override
            public void onSuccess(UpdataLoadFileBean bean) {
                view.upLoadFileSuccess(bean.getFilePath());
            }

            @Override
            public void onFailure(String errorMsg) {
                view.upLoadFileFailure(errorMsg);
            }
        };
        ReqProgressCallBack callBack = new ReqProgressCallBack() {
            @Override
            public void onProgress(String totalSize, String currentSize, String progress, boolean done) {
                LogUtils.loge("总大小:" + totalSize);
                LogUtils.loge("当前大小:" + currentSize);
                LogUtils.loge("当前进度:" + progress);
                LogUtils.loge("是否上传完毕:" + done);
            }
        };
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        model.uploadFiles(observer,callBack,part);
    }

}
