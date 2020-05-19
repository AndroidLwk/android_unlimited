package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.AlipayBean;
import com.wuxiantao.wxt.bean.BoxTypeBean;
import com.wuxiantao.wxt.bean.CardInfoBean;
import com.wuxiantao.wxt.bean.ExchangeBean;
import com.wuxiantao.wxt.bean.MyBoxInfo;
import com.wuxiantao.wxt.bean.OrderStatusBean;
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.CommissionService;
import com.wuxiantao.wxt.net.service.OrderApiService;

import java.util.List;
import java.util.Map;

public class MyBackpackModel extends SettingPassWordModel {
    //我的背包
    public void myBox(BaseObserver<MyBoxInfo> observer, Map<String, Object> map) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .myBox(map)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    //背包分类
    public void getBoxCate(BaseObserver<List<BoxTypeBean>> observer, String token) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .getBoxCate(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    //使用卡片
    public void useCard(BaseObserver<CardInfoBean> observer, String token, String cid, String num) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .useCard(token, cid, num)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    //销毁卡片
    public void discard(BaseObserver<CardInfoBean> observer, String token, String card_id, String num) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .discard(token, card_id, num)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    //转赠刮刮卡和碎片(余额)
    public void exchange(BaseObserver<ExchangeBean> observer, Map<String, Object> map) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .exchange(map)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    //转赠刮刮卡和碎片(支付宝)
    public void exchange_alipay(BaseObserver<AlipayBean> observer, Map<String, Object> map) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .exchange_alipay(map)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    //转赠刮刮卡和碎片(微信)
    public void exchange_wx(BaseObserver<WeChatPayBean> observer, Map<String, Object> map) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .exchange_wx(map)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
    /**
     * 验证订单状态
     * @param observer observer
     * @param token token
     * @param order_id 订单号
     */
    public void checkOrderStatus(BaseObserver<OrderStatusBean> observer, String token, String order_id){
        HttpManager.newInstance()
                .createService(OrderApiService.class)
                .checkOrderStatus(token,order_id)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
