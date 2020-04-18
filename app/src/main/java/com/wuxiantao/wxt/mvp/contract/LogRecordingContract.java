package com.wuxiantao.wxt.mvp.contract;


import com.wuxiantao.wxt.bean.LogRecordingBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:12 8-19
 * Description: ${DESCRIPTION} MainContract -主界面的Presenter和View的契约类
 * Author: Administrator Shiming-Shi
 */

public interface LogRecordingContract {

    interface IRecordingView extends MvpView {
        //将一些操作界面的方法在这里声明
        void obtainLogRecordingSuccess(LogRecordingBean bean);
        void obtainLogRecordingFailure(String failure);
    }

    interface IRecordingPresenter extends MvpPresenter<IRecordingView> {
        //将一些逻辑处理的方法在此声明
        void obtainLogRecording(String token, int page, int type);
    }
}
