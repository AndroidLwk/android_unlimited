package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.EnrollBonusBean;
import com.wuxiantao.wxt.bean.MyCardInfo;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.CommissionService;

public class ScrapingCardFragmentModel extends BaseModel {
    //刮刮卡页面信息
    public void getMyCardInfo(BaseObserver<MyCardInfo> observer, String token) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .myCardInfo(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    //开始分红
    public void enrollBonus(BaseObserver<EnrollBonusBean> observer, String token, String type) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .enrollBonus(token, type)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
