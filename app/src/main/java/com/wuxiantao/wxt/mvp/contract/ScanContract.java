package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.ScanBean;
import com.wuxiantao.wxt.bean.ScratchCardDetailsBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Copyright (C), 成都都爱玩科技有限公司
 * Date: 2020/5/20--14:57
 * Description: 扫描页面
 * Author: lht
 */
public interface ScanContract {
    interface IScanView extends MvpView {
        //将一些操作界面的方法在这里声明
        void scanAwardSuccess(ScanBean bean);
        void scanAwardFailure(String failure);
    }

    interface IScanPresenter extends MvpPresenter<ScanContract.IScanView> {
        //互扫奖励
        void scanAward(String token, String pid);
    }
}
