package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.MyLuckyInfoBean;
import com.wuxiantao.wxt.bean.RandGetCardBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.CommissionService;

import io.reactivex.Observer;

public class PointToCardModel extends TaskHallFragmentModel {
    //开始刮卡
    public void startStraping(Observer observer, String token) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .startStraping(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    //我的幸运值(狂点页面)
    public void myLuckyInfo(BaseObserver<MyLuckyInfoBean> observer, String token, String type) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .myLuckyInfo(token, type)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    //看视频
    public void randGetCard(BaseObserver<RandGetCardBean> observer, String token, String type) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .randGetCard(token, type)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
