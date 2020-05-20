package com.wuxiantao.wxt.mvp.presenter;
import com.wuxiantao.wxt.bean.ScanBean;
import com.wuxiantao.wxt.bean.ScratchCardDetailsBean;
import com.wuxiantao.wxt.mvp.contract.ScanContract;
import com.wuxiantao.wxt.mvp.model.BalanceDetailModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Copyright (C), 成都都爱玩科技有限公司
 * Date: 2020/5/20--14:59
 * Description: 扫描
 * Author: lht
 */
public class ScannerPresenter extends BasePresenter<ScanContract.IScanView> implements ScanContract.IScanPresenter  {

    private BalanceDetailModel model = new BalanceDetailModel();
    private ScanContract.IScanView view;
    @Override
    public void scanAward(String token, String pid) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<ScanBean> observer = new BaseObserver<ScanBean>(view) {

            @Override
            public void onSuccess(ScanBean bean) {
                view.scanAwardSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.scanAwardFailure(errorMsg);
            }
        };
        model.scanAward(observer,token,pid);
    }
}
