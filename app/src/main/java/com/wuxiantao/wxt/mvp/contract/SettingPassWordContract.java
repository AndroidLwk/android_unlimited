package com.wuxiantao.wxt.mvp.contract;


import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

import java.util.Map;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:12 8-19
 * Description: ${DESCRIPTION} MainContract -主界面的Presenter和View的契约类
 * Author: Administrator Shiming-Shi
 */

public interface SettingPassWordContract {

    interface ISettingView extends MvpView {
        //将一些操作界面的方法在这里声明
        void setPassWordsSuccess(String msg);
        void setPassWordsFailure(String failure);

        //设置支付密码
        void setPayPasswordSuccess(String msg);
        void setPayPasswordFailure(String failure);
    }

    interface ISettingPresenter extends MvpPresenter<ISettingView> {
        //将一些逻辑处理的方法在此声明
        void setUserLoginPassWord(String token,String password_old, String password_new);
        //调用设置支付密码
        void setUserPayPassword(Map<String, Object> parameters);
    }
}
