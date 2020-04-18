package com.wuxiantao.wxt.mvp.contract;


import com.wuxiantao.wxt.mvp.login.LoginMvpPresenter;
import com.wuxiantao.wxt.mvp.login.LoginView;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:12 8-19
 * Description: ${DESCRIPTION} MainContract -主界面的Presenter和View的契约类
 * Author: Administrator Shiming-Shi
 */

public interface LoginContract {

    interface ILoginView extends LoginView {
        //将一些操作界面的方法在这里声明

    }

    interface ILoginPresenter extends LoginMvpPresenter<ILoginView> {
        //将一些逻辑处理的方法在此声明

    }
}
