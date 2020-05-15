package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.FansiDirectlyBean;
import com.wuxiantao.wxt.bean.MyFansiHeadInfoBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.FanSiApiService;

import java.util.Map;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:39 8-19
 * Description: ${DESCRIPTION} MainModel-主界面里数据的获取（数据库中获取或者联网请求在此实现将结果通过接口回调）
 * Author: Administrator Shiming-Shi
 */

public class MineFansiModel extends BaseModel{


    public void obtainFansiHeadInfo(BaseObserver<MyFansiHeadInfoBean> observer, String token){
        HttpManager.newInstance()
                .createService(FanSiApiService.class)
                .obtainFansiHeadInfo(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


    public   void obtainDirectlyFansi(BaseObserver<FansiDirectlyBean> observer, Map<String,Object> parameters){
        HttpManager.newInstance()
                .createService(FanSiApiService.class)
                .obtainDirectlyFansi(parameters)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

}
