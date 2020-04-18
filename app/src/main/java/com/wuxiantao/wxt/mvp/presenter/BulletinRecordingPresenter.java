package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.LeaderboardBean;
import com.wuxiantao.wxt.mvp.contract.BulletinRecordingContract;
import com.wuxiantao.wxt.mvp.model.BulletinRecordingModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午2:26
 * Description:${DESCRIPTION}
 */
public class BulletinRecordingPresenter extends BasePresenter<BulletinRecordingContract.IRecordingView> implements BulletinRecordingContract.IRecordingPresenter {

    private BulletinRecordingContract.IRecordingView view;
    private BulletinRecordingModel model = new BulletinRecordingModel();

    @Override
    public void obtainRecording(String token, int page, int type) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<LeaderboardBean> observer = new BaseObserver<LeaderboardBean>(view) {
            @Override
            public void onSuccess(LeaderboardBean bean) {
                view.obtainRecordingSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.obtainRecordingFailure(errorMsg);
            }
        };
        model.obtainRecording(observer,token,page,type);
    }
}
