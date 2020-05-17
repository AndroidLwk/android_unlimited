package com.wuxiantao.wxt.mvp.contract;


import com.wuxiantao.wxt.bean.BalanceDetailBean;
import com.wuxiantao.wxt.bean.ScratchCardDetailsBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Copyright (C), 成都都爱玩科技有限公司
 * Date: 2020/5/17--17:30
 * Description: 刮刮卡明细
 * Author: lht
 */

public interface ScratchCardDetailContract {

    interface IDetailView extends MvpView {
        //将一些操作界面的方法在这里声明
        void obtainCardDetailSuccess(ScratchCardDetailsBean bean);
        void obtainCardDetailFailure(String failure);
    }

    interface IDetailPresenter extends MvpPresenter<IDetailView> {
        //将一些逻辑处理的方法在此声明
        void obtainCardDetails(String token, int page);
    }
}
