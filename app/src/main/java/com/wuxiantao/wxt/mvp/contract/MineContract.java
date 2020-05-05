package com.wuxiantao.wxt.mvp.contract;


import com.wuxiantao.wxt.bean.MyDepositBean;
import com.wuxiantao.wxt.mvp.order.OrderTypeMvpPresenter;
import com.wuxiantao.wxt.mvp.order.OrderTypeView;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:12 8-19
 * Description: ${DESCRIPTION} MainContract -主界面的Presenter和View的契约类
 * Author: Administrator Shiming-Shi
 */

public interface MineContract {

    interface IMineView extends OrderTypeView {
        //将一些操作界面的方法在这里声明
        void obtainMyDepositSuccess(MyDepositBean bean);
        void obtainMyDepositFailure(String failure);
    }

    interface ILoginPresenter extends OrderTypeMvpPresenter<IMineView> {
        //将一些逻辑处理的方法在此声明
        void obtainMyDeposit(String token);
        void getLatelyOrder(int type,String token,String order_no);
    }
}
