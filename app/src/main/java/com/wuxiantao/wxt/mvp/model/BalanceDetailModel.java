package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.BalanceDetailBean;
import com.wuxiantao.wxt.bean.ScanBean;
import com.wuxiantao.wxt.bean.ScratchCardDetailsBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.RecordingApiService;

import static com.wuxiantao.wxt.config.Constant.PAGE_SIZE;

/**
 * Copyright (C), 成都都爱玩科技有限公司
 * Date: 2020/5/17--15:02
 * Description: 余额明细  刮刮卡明细  互扫奖励
 * Author: lht
 */

public class BalanceDetailModel extends BaseModel{


    public void obtainBalanceDetails(BaseObserver<BalanceDetailBean> observer, String token, int page){
        HttpManager.newInstance()
                .createService(RecordingApiService.class)
                .obtainBalanceDetails(token,page,20)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    /**
     * 刮刮卡明细
     * @param observer
     * @param token
     * @param page
     */
    public void obtainCardDetails(BaseObserver<ScratchCardDetailsBean> observer, String token, int page){
        HttpManager.newInstance()
                .createService(RecordingApiService.class)
                .obtainCardDetails(token,page,20)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    /**
     * 互扫奖励
     * @param observer
     * @param token
     * @param pid
     */
    public void scanAward(BaseObserver<ScanBean> observer, String token, String pid){
        HttpManager.newInstance()
                .createService(RecordingApiService.class)
                .scanAward(token,pid)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

}
