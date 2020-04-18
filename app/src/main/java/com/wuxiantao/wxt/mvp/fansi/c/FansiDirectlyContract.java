package com.wuxiantao.wxt.mvp.fansi.c;


import com.wuxiantao.wxt.bean.FansiDirectlyBean;
import com.wuxiantao.wxt.mvp.fansi.p.FansiMvpPresenter;
import com.wuxiantao.wxt.mvp.fansi.view.FansiView;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:12 8-19
 * Description: ${DESCRIPTION} MainContract -主界面的Presenter和View的契约类
 * Author: Administrator Shiming-Shi  直属粉丝
 */

public interface FansiDirectlyContract {

    interface IFansiView extends FansiView {
        //将一些操作界面的方法在这里声明
        void obtainFansSuccess(FansiDirectlyBean bean);
    }

    interface IFansiPresenter extends FansiMvpPresenter<IFansiView> {
        //将一些逻辑处理的方法在此声明

    }
}
