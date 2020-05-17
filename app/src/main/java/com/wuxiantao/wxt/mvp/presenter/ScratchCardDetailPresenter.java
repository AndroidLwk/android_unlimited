package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.BalanceDetailBean;
import com.wuxiantao.wxt.bean.ScratchCardDetailsBean;
import com.wuxiantao.wxt.mvp.contract.BalanceDetailContract;
import com.wuxiantao.wxt.mvp.contract.ScratchCardDetailContract;
import com.wuxiantao.wxt.mvp.model.BalanceDetailModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Copyright (C), 成都都爱玩科技有限公司
 * Date: 2020/5/17--17:24
 * Description: 刮刮卡明细
 * Author: lht
 */
public class ScratchCardDetailPresenter extends BasePresenter<ScratchCardDetailContract.IDetailView> implements ScratchCardDetailContract.IDetailPresenter {

    private BalanceDetailModel model = new BalanceDetailModel();
    private ScratchCardDetailContract.IDetailView view;



    @Override
    public void obtainCardDetails(String token, int page) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<ScratchCardDetailsBean> observer = new BaseObserver<ScratchCardDetailsBean>(view) {

            @Override
            public void onSuccess(ScratchCardDetailsBean bean) {
                view.obtainCardDetailSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.obtainCardDetailFailure(errorMsg);
            }
        };
        model.obtainCardDetails(observer,token,page);
    }
}
