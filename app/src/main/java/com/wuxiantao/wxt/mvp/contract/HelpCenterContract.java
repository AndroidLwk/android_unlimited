package com.wuxiantao.wxt.mvp.contract;


import com.wuxiantao.wxt.bean.HelpCenterBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

import java.util.List;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:12 8-19
 * Description: ${DESCRIPTION} MainContract -主界面的Presenter和View的契约类
 * Author: Administrator Shiming-Shi
 */

public interface HelpCenterContract {

    interface IHelpCenterView extends MvpView {
        //将一些操作界面的方法在这里声明
        void onGetImgListSuccess(List<HelpCenterBean> beanList);
        void onGetImgListFailure(String failure);
    }

    interface IHelpCenterPresenter extends MvpPresenter<IHelpCenterView> {
        //将一些逻辑处理的方法在此声明
        void onGetImgList(String token,int page);
    }
}
