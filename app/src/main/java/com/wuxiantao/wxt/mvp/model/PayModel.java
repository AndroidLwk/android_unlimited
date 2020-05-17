package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.AlipayBean;
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.GameApiService;
import com.wuxiantao.wxt.net.service.OrderApiService;
import com.wuxiantao.wxt.net.service.PayService;

import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;

/**
 * Copyright (C), 成都都爱玩科技有限公司
 * Date: 2020/5/16--16:46
 * Description: 充值model
 * Author: lht
 */
public class PayModel extends BaseModel{

    /**
     * 创建支付宝订单
     * @param observer
     * @param map
     */
    public void onCreateAliOrder(BaseObserver<AlipayBean> observer, @FieldMap Map<String,Object> map){
        HttpManager.newInstance()
                .createService(PayService.class)
                .onCreateAliOrder(map)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
    /**
     * 创建微信订单
     * @param observer
     * @param map
     */
    public void onCreateWeChatOrder(BaseObserver<WeChatPayBean> observer, @FieldMap Map<String,Object> map){
        HttpManager.newInstance()
                .createService(PayService.class)
                .onCreateWeChatOrder(map)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    /**
     * 验证订单状态
     * @param observer observer
     * @param token token
     * @param order_id 订单号
     */
    public void checkOrderStatus(BaseObserver<List> observer, String token, String order_id){
        HttpManager.newInstance()
                .createService(OrderApiService.class)
                .checkOrderStatus(token,order_id)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
