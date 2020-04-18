package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.ProfitRecordingBean;
import com.wuxiantao.wxt.mvp.contract.ProfitRecordingContract;
import com.wuxiantao.wxt.mvp.model.ProfitRecordingModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ProfitRecordingPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 上午9:45
 * Description:${DESCRIPTION}
 */
public class ProfitRecordingPresenter extends BasePresenter<ProfitRecordingContract.IRecordingView> implements ProfitRecordingContract.IRecordingPresenter {

    private ProfitRecordingContract.IRecordingView view;
    private ProfitRecordingModel model = new ProfitRecordingModel();

    @Override
    public void getProfitRecording(String token, int page) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<ProfitRecordingBean> observer = new BaseObserver<ProfitRecordingBean>(view) {
            @Override
            public void onSuccess(ProfitRecordingBean bean) {
                view.getProfitRecordingSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getProfitRecordingFailure(errorMsg);
            }
        };
        model.getProfitRecording(observer,token,page);
    }
}
