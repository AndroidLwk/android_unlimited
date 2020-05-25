package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.BalanceDetailBean;
import com.wuxiantao.wxt.mvp.contract.BalanceDetailContract;
import com.wuxiantao.wxt.mvp.model.BalanceDetailModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Copyright (C), 成都都爱玩科技有限公司
 * Date: 2020/5/17--15:05
 * Description: 余额明细
 * Author: lht
 */
public class BalanceDetailPresenter extends BasePresenter<BalanceDetailContract.IDetailView> implements BalanceDetailContract.IDetailPresenter {

    private BalanceDetailModel model = new BalanceDetailModel();
    private BalanceDetailContract.IDetailView view;


    @Override
    public void obtainBalanceDetails(String token, int page,int type) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<BalanceDetailBean> observer = new BaseObserver<BalanceDetailBean>(view) {
            @Override
            public void onSuccess(BalanceDetailBean bean) {
                view.obtainBalanceDetailSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.obtainBalanceDetailFailure(errorMsg);
            }
        };
        model.obtainBalanceDetails(observer,token,page,type);
    }
}
