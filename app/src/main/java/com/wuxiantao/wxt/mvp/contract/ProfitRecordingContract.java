package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.ProfitRecordingBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ProfitRecordingContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 上午9:43
 * Description:${DESCRIPTION}
 */
public interface ProfitRecordingContract {

    interface IRecordingView extends MvpView{
        void getProfitRecordingSuccess(ProfitRecordingBean bean);
        void getProfitRecordingFailure(String failure);
    }

    interface IRecordingPresenter extends MvpPresenter<IRecordingView>{
        void getProfitRecording(String token,int page);
    }
}
