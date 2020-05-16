package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.MyMoneyCashBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.CommissionService;

public class MyDepositModel extends  BaseModel{
    //我的余额和累计提现金额
    public void myMoneyCash(BaseObserver<MyMoneyCashBean> observer, String token) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .myMoneyCash(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


}
