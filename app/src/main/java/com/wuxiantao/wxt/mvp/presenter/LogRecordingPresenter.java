package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.LogRecordingBean;
import com.wuxiantao.wxt.mvp.contract.LogRecordingContract;
import com.wuxiantao.wxt.mvp.model.LogRecordingModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午2:26
 * Description:${DESCRIPTION}
 */
public class LogRecordingPresenter extends BasePresenter<LogRecordingContract.IRecordingView> implements LogRecordingContract.IRecordingPresenter {

    private LogRecordingModel model = new LogRecordingModel();
    private LogRecordingContract.IRecordingView view;

    @Override
    public void obtainLogRecording(String token, int page, int type) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<LogRecordingBean> observer = new BaseObserver<LogRecordingBean>(view) {
            @Override
            public void onSuccess(LogRecordingBean bean) {
                view.obtainLogRecordingSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.obtainLogRecordingFailure(errorMsg);
            }
        };
        model.obtainLogRecording(observer,token,page,type);
    }
}
