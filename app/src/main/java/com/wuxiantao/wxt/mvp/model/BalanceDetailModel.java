package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.BalanceDetailBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.RecordingApiService;

import static com.wuxiantao.wxt.config.Constant.PAGE_SIZE;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:39 8-19
 * Description: ${DESCRIPTION} MainModel-主界面里数据的获取（数据库中获取或者联网请求在此实现将结果通过接口回调）
 * Author: Administrator Shiming-Shi
 */

public class BalanceDetailModel extends BaseModel{


    public void obtainBalanceDetails(BaseObserver<BalanceDetailBean> observer, String token, int page){
        HttpManager.newInstance()
                .createService(RecordingApiService.class)
                .obtainBalanceDetails(token,page,PAGE_SIZE)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


}
