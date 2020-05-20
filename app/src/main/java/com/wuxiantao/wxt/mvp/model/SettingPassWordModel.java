package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.IsSetPayPassword;
import com.wuxiantao.wxt.bean.ListNullBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.UserApiService;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:39 8-19
 * Description: ${DESCRIPTION} MainModel-主界面里数据的获取（数据库中获取或者联网请求在此实现将结果通过接口回调）
 * Author: Administrator Shiming-Shi
 */

public class SettingPassWordModel extends BaseModel{

    public void setUserLoginPassWord(String token, BaseObserver<ListNullBean> observer, String password_old, String password_new){
        HttpManager.newInstance()
                .createService(UserApiService.class)
                .setUserLoginPassWord(token,password_old,password_new)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
    //调用设置支付密码
    public void setUserPayPassword(Map<String,Object> parameters, BaseObserver<ListNullBean> observer){
        HttpManager.newInstance()
                .createService(UserApiService.class)
                .setUserPayPassword(parameters)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    //是否设置交易密码
    public void isSetPayPassword(String token, BaseObserver<IsSetPayPassword> observer){
        HttpManager.newInstance()
                .createService(UserApiService.class)
                .isSetPayPassword(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
