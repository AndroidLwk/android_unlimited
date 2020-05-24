package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.ModifyPersonalBean;
import com.wuxiantao.wxt.bean.PhoneLoginBean;
import com.wuxiantao.wxt.bean.ResetPassBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.LoginApiSerVice;
import com.wuxiantao.wxt.net.service.UserApiService;

import java.util.Map;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:39 8-19
 * Description: ${DESCRIPTION} MainModel-主界面里数据的获取（数据库中获取或者联网请求在此实现将结果通过接口回调）
 * Author: Administrator Shiming-Shi
 */

public class CodeVerifyModel extends LoginModel{

    public void resetPassWord(BaseObserver<ResetPassBean> observer, String mobile, String code, String newPassWord){
        HttpManager.newInstance()
                .createService(LoginApiSerVice.class)
                .resetPassWord(mobile,code,newPassWord)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


    public void phoneRegister(BaseObserver<PhoneLoginBean> observer, String mobile, String code, String password){
        HttpManager.newInstance()
                .createService(LoginApiSerVice.class)
                .phoneRegister(mobile,code,password)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


    public void bindingNumber(BaseObserver<ModifyPersonalBean> observer, Map<String,Object> parameters){
        HttpManager.newInstance()
                .createService(UserApiService.class)
                .modifyPersonal(parameters)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

}
