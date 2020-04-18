package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.LotteryRecordingBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:LotteryRecordingContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-11 下午2:20
 * Description:${DESCRIPTION}
 */
public interface LotteryRecordingContract {

    interface IRecordingView extends MvpView{
        void getRecordingSuccess(LotteryRecordingBean bean);
        void getRecordingFailure(String failure);
    }

    interface ILotteryPresenter extends MvpPresenter<IRecordingView>{
        void getLotteryRecording(String token,int page);
    }
}
