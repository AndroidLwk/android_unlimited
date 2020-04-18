package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.net.callback.ReqProgressCallBack;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.service.BaseApiService;
import com.wuxiantao.wxt.net.http.HttpManager;

import io.reactivex.Observer;
import okhttp3.ResponseBody;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:39 8-19
 * Description: ${DESCRIPTION} MainModel-主界面里数据的获取（数据库中获取或者联网请求在此实现将结果通过接口回调）
 * Author: Administrator Shiming-Shi
 */

public class MainModel {

    public void downLoadImage(Observer<ResponseBody> observer,ReqProgressCallBack callBack){
        HttpManager.newInstance()
                .createResponseService(BaseApiService.class,callBack)
                .downLoadFile()
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

}
