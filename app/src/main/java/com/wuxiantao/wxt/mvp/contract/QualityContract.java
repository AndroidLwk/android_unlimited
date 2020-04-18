package com.wuxiantao.wxt.mvp.contract;


import com.wuxiantao.wxt.bean.ActiveStatusBean;
import com.wuxiantao.wxt.bean.VipStatusInfoBean;
import com.wuxiantao.wxt.mvp.banner.BannerMvpPresenter;
import com.wuxiantao.wxt.mvp.banner.BannerView;
import com.wuxiantao.wxt.mvp.pay.OrderPayMvpPresenter;
import com.wuxiantao.wxt.mvp.pay.OrderPayView;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:12 8-19
 * Description: ${DESCRIPTION} MainContract -主界面的Presenter和View的契约类
 * Author: Administrator Shiming-Shi
 */

public interface QualityContract {

    interface IQualityView extends BannerView, OrderPayView {
        //将一些操作界面的方法在这里声明
        void isActiveStatusSuccess(ActiveStatusBean bean);
        void isActiveStatusFailure(String failure);

        void getVipStatusInfoSuccess(VipStatusInfoBean bean);
        void getVipStatusInfoFailure(String failure);
    }

    interface IQualityPresenter extends BannerMvpPresenter<IQualityView>, OrderPayMvpPresenter<IQualityView> {
        //将一些逻辑处理的方法在此声明
        void isActiveStatus(String token);
        void getVipStatusInfo(String token);
    }
}
