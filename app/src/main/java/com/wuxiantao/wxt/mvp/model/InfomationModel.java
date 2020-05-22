package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.NetSwitchBean;
import com.wuxiantao.wxt.bean.NoticeBean;
import com.wuxiantao.wxt.bean.PersonalInfoBean;
import com.wuxiantao.wxt.bean.StopAppBean;
import com.wuxiantao.wxt.mvp.version.VersionModel;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.CommissionService;
import com.wuxiantao.wxt.net.service.DragonApiService;
import com.wuxiantao.wxt.net.service.NetSwitchService;
import com.wuxiantao.wxt.net.service.UserApiService;

import java.util.List;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:39 8-19
 * Description: ${DESCRIPTION} MainModel-主界面里数据的获取（数据库中获取或者联网请求在此实现将结果通过接口回调）
 * Author: Administrator Shiming-Shi
 */

public class InfomationModel extends VersionModel {

    public void getSwitchType(BaseObserver<NetSwitchBean> observer, String url){
        HttpManager.newInstance()
                .createService(NetSwitchService.class)
                .getSwitchType(url)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


    public void onStartApp(BaseObserver<List<String>> observer, String token){
        HttpManager.newInstance()
                .createService(DragonApiService.class)
                .onStartApp(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void onStopApp(BaseObserver<StopAppBean> observer, String token){
        HttpManager.newInstance()
                .createService(DragonApiService.class)
                .onStopApp(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void obtainPersonal(BaseObserver<PersonalInfoBean> observer, String token){
        HttpManager.newInstance()
                .createService(UserApiService.class)
                .obtainPersonal(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
    public void notice(BaseObserver<NoticeBean> observer, String token){
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .notice(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
