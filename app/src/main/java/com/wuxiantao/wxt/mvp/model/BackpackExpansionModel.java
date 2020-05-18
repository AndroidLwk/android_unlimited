package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.AlipayBean;
import com.wuxiantao.wxt.bean.KuorongInfoBean;
import com.wuxiantao.wxt.bean.OrderStatusBean;
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.mvp.pay.OrderPayModel;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.CommissionService;

public class BackpackExpansionModel extends OrderPayModel {
    //余额扩容
    public void addbox_balance(BaseObserver<OrderStatusBean> observer, String token, String type) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .addbox_balance(token, type)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    //支付宝扩容
    public void addbox_alipay(BaseObserver<AlipayBean> observer, String token, String type) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .addbox_alipay(token, type)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    //微信扩容
    public void addbox_wx(BaseObserver<WeChatPayBean> observer, String token, String type) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .addbox_wx(token, type)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    //扩容页面信息
    public void kuorongInfo(BaseObserver<KuorongInfoBean> observer, String token) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .kuorongInfo(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
