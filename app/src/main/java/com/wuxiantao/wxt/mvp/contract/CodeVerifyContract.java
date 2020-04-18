package com.wuxiantao.wxt.mvp.contract;


import com.wuxiantao.wxt.bean.PhoneLoginBean;
import com.wuxiantao.wxt.mvp.login.LoginMvpPresenter;
import com.wuxiantao.wxt.mvp.login.LoginView;

import java.util.Map;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:12 8-19
 * Description: ${DESCRIPTION} MainContract -主界面的Presenter和View的契约类
 * Author: Administrator Shiming-Shi
 */

public interface CodeVerifyContract {

    interface IVerifyView extends LoginView {
        //将一些操作界面的方法在这里声明
        void registerSuccess(PhoneLoginBean bean);
        void registerFailure(String failure);
        void resetPassWordSuccess(String success);
        void resetPassWordFailure(String failure);
        void bindingNumberSuccess(String message);
        void bindingNumberFailure(String failure);
    }

    interface IVerifyPresenter extends LoginMvpPresenter<IVerifyView> {
        //将一些逻辑处理的方法在此声明
        void phoneRegister(String mobile, String code, String password);
        void resetPassWord(String mobile, String code,String newPassWord);
        void bindingNumber(Map<String,Object> parameters);
    }

}
