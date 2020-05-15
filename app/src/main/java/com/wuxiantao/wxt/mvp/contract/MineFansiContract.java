package com.wuxiantao.wxt.mvp.contract;


import com.wuxiantao.wxt.bean.FansiDirectlyBean;
import com.wuxiantao.wxt.bean.MyFansiHeadInfoBean;
import com.wuxiantao.wxt.mvp.fansi.view.FansiView;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

import java.util.Map;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:12 8-19
 * Description: ${DESCRIPTION} MainContract -主界面的Presenter和View的契约类
 * Author: Administrator Shiming-Shi
 */

public interface MineFansiContract {

    interface IFansiView extends MvpView {
        //将一些操作界面的方法在这里声明
        void obtainFansiHeadInfoSuccess(MyFansiHeadInfoBean bean);
        void obtainFansSuccess(FansiDirectlyBean bean);
    }

    interface IFansiPresenter extends MvpPresenter<IFansiView> {
        //将一些逻辑处理的方法在此声明
        void obtainFansiHeadInfo(String token);
        void obtainDirectlyFansi(Map<String, Object> parameters);
    }

}
