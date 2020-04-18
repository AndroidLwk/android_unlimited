package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.LotteryRecordingBean;
import com.wuxiantao.wxt.mvp.contract.LotteryRecordingContract;
import com.wuxiantao.wxt.mvp.model.LotteryRecordingModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:LotteryRecordingPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-11 下午2:21
 * Description:${DESCRIPTION}
 */
public class LotteryRecordingPresenter extends BasePresenter<LotteryRecordingContract.IRecordingView> implements LotteryRecordingContract.ILotteryPresenter {

    private LotteryRecordingContract.IRecordingView view;
    private LotteryRecordingModel model = new LotteryRecordingModel();

    @Override
    public void getLotteryRecording(String token, int page) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<LotteryRecordingBean> observer = new BaseObserver<LotteryRecordingBean>() {
            @Override
            public void onSuccess(LotteryRecordingBean bean) {
                view.getRecordingSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getRecordingFailure(errorMsg);
            }
        };
        model.getLotteryRecording(observer,token,page);
    }
}
