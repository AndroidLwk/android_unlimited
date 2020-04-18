package com.wuxiantao.wxt.mvp.vercode;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.LoginApiSerVice;

import java.util.List;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:39 8-19
 * Description: ${DESCRIPTION} MainModel-主界面里数据的获取（数据库中获取或者联网请求在此实现将结果通过接口回调）
 * Author: Administrator Shiming-Shi
 */

public class ObtainCodeModel {


    public void obtainCode(BaseObserver<List> observer, String mobile, int type){
        HttpManager.newInstance()
                .createService(LoginApiSerVice.class)
                .obtainCode(mobile,type)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


}
