package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.CardInfoBean;
import com.wuxiantao.wxt.bean.MyLuckyInfoBean;
import com.wuxiantao.wxt.bean.StartStrapingBean;
import com.wuxiantao.wxt.mvp.view.MvpView;

public interface PointToCardContract extends MvpView {
    void startStrapSuccess(StartStrapingBean bean);

    void getCardSuccess(CardInfoBean bean);

    void onFailure(String msg);

    void myLuckyInfo(MyLuckyInfoBean info);
    void randGetCardSuccess(String msg);
}
