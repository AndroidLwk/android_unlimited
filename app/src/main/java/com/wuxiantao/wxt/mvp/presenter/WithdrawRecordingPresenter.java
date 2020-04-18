package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.WithdrawRecordingBean;
import com.wuxiantao.wxt.mvp.contract.WithdrawRecordingContract;
import com.wuxiantao.wxt.mvp.model.WithdrawRecordingModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午2:26
 * Description:${DESCRIPTION}
 */
public class WithdrawRecordingPresenter extends BasePresenter<WithdrawRecordingContract.IRecordingView> implements WithdrawRecordingContract.IRecordingPresenter {

    private WithdrawRecordingModel model = new WithdrawRecordingModel();
    private WithdrawRecordingContract.IRecordingView view;

    @Override
    public void obtainRecording(String token, int page) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<WithdrawRecordingBean> observer = new BaseObserver<WithdrawRecordingBean>(view) {
            @Override
            public void onSuccess(WithdrawRecordingBean s) {
                view.obtainRecordingSuccess(s);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.obtainRecordingFailure(errorMsg);
            }
        };
        model.obtainWithdrawRecording(observer,token,page);
    }


}
