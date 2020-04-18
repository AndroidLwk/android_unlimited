package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.MyDepositBean;
import com.wuxiantao.wxt.bean.TaobaoLatelyOrderBean;
import com.wuxiantao.wxt.bean.YouXuanLatelyOrderBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.OrderApiService;
import com.wuxiantao.wxt.net.service.UserApiService;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:39 8-19
 * Description: ${DESCRIPTION} MainModel-主界面里数据的获取（数据库中获取或者联网请求在此实现将结果通过接口回调）
 * Author: Administrator Shiming-Shi
 */

public class MineModel extends BaseModel{


    public void obtainMyDeposit(BaseObserver<MyDepositBean> observer, String token){
        HttpManager.newInstance()
                .createService(UserApiService.class)
                .obtainMyDeposit(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


    public void getTaobaoLatelyOrder(BaseObserver<TaobaoLatelyOrderBean> observer, String token,String order_no){
        HttpManager.newInstance()
                .createService(OrderApiService.class)
                .getTaobaoLatelyOrder(token,order_no)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void getYouXuanLatelyOrder(BaseObserver<YouXuanLatelyOrderBean> observer, String token,String order_no){
        HttpManager.newInstance()
                .createService(OrderApiService.class)
                .getYouXuanLatelyOrder(token,order_no)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

}
