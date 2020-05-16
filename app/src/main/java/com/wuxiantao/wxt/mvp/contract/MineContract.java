package com.wuxiantao.wxt.mvp.contract;


import com.wuxiantao.wxt.bean.MyMoneyCashBean;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:12 8-19
 * Description: ${DESCRIPTION} MainContract -主界面的Presenter和View的契约类
 * Author: Administrator Shiming-Shi
 */

public interface MineContract {

    interface IMineView extends MvpView {
        void onFailure(String msg);
        void  showMyMoneyCash(MyMoneyCashBean info);
    }


}
