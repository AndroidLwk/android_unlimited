package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

import java.util.Map;

/**
 * Copyright (C), 成都都爱玩科技有限公司
 * Date: 2020/5/16--15:52
 * Description: 充值的presenter和view的契约类
 * Author: lht
 */
public interface PayContract {
    interface IPayView extends MvpView{
        void getPayInfoSucceed();//获取支付信息成功
        void onWeChatPay(WeChatPayBean infoBean);
        void onAliPay(String order_id,String res);

        void onOrderPaySuccess(String msg);//查询订单成功
        void onOrderPayFailure(String failure);//查询订单失败

        void onOrderCreateFailure(String failure);//订单创建失败
    }


    interface IPayePresenter extends MvpPresenter<IPayView> {
        void onAliPay(Map<String, Object> parameters);
        void onWechatPay(Map<String, Object> parameters);
        void checkOrderStatus(String token,String order);
    }
}
