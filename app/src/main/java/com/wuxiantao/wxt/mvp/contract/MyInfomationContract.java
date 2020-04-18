package com.wuxiantao.wxt.mvp.contract;


import com.wuxiantao.wxt.bean.PersonalInfoBean;
import com.wuxiantao.wxt.bean.TaoBaoLoginBean;
import com.wuxiantao.wxt.mvp.upload.UpLoadFileMvpPresenter;
import com.wuxiantao.wxt.mvp.upload.UpLoadFileView;
import com.wuxiantao.wxt.mvp.user.p.ModifyMvpPresenter;
import com.wuxiantao.wxt.mvp.user.v.ModifyView;

import java.util.Map;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:12 8-19
 * Description: ${DESCRIPTION} MainContract -主界面的Presenter和View的契约类
 * Author: Administrator Shiming-Shi
 */

public interface MyInfomationContract {

    interface IInfomationView extends UpLoadFileView,ModifyView {
        //将一些操作界面的方法在这里声明
        void obtainPersonalSuccess(PersonalInfoBean bean);
        void obtainPersonalFailure(String failure);
        void taoBaoLoginSuccess(TaoBaoLoginBean bean);
        void taoBaoLoginFailure(String failure);
        void onStopAppSuccess(String msg);
        void onStopAppFailure(String failure);
    }

    interface IInfomationPresenter extends UpLoadFileMvpPresenter<IInfomationView>, ModifyMvpPresenter<IInfomationView> {
        //将一些逻辑处理的方法在此声明
        void obtainPersonal(String token);
        void taoBaoLogin(Map<String,Object> parameters);
        void onStopApp(String token);
    }

}
