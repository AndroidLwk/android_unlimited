package com.wuxiantao.wxt.mvp.contract;


import com.wuxiantao.wxt.bean.BalanceDetailBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Copyright (C), 成都都爱玩科技有限公司
 * Date: 2020/5/17--15:02
 * Description: 余额明细
 * Author: lht
 */

public interface BalanceDetailContract {

    interface IDetailView extends MvpView {
        //将一些操作界面的方法在这里声明
        void obtainBalanceDetailSuccess(BalanceDetailBean bean);
        void obtainBalanceDetailFailure(String failure);
    }

    interface IDetailPresenter extends MvpPresenter<IDetailView> {
        //将一些逻辑处理的方法在此声明
        void obtainBalanceDetails(String token,int page,int type);
    }
}
