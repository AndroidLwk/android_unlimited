package com.wuxiantao.wxt.mvp.contract;


import com.wuxiantao.wxt.bean.MyCardInfo;
import com.wuxiantao.wxt.bean.MySignInfo;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:12 8-19
 * Description: ${DESCRIPTION} MainContract -主界面的Presenter和View的契约类
 * Author: Administrator Shiming-Shi
 */

public interface ScrapingCardFragmentContract {

    interface IScrapingCardFragmentView extends MvpView {
        //将一些操作界面的方法在这里声明
        void showMyCardInfo(MyCardInfo info);
        void getMyCardInfoFailure(String errorMsg);
        void enrollBonusFailure(String errorMsg);
        void enrollBonusSuccess(String msg);

        void signFailure(String msg);
        void signSuccess(MySignInfo info);
    }

}
