package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.AlipayBean;
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.mvp.contract.BalanceContract;
import com.wuxiantao.wxt.mvp.contract.MyMemberContract;
import com.wuxiantao.wxt.mvp.contract.PayContract;
import com.wuxiantao.wxt.mvp.model.MyMemberModel;
import com.wuxiantao.wxt.mvp.model.PayModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.List;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.RESOURCES;

/**
 * Copyright (C), 成都都爱玩科技有限公司
 * Date: 2020/5/16--16:04
 * Description: 充值的Presenter
 * Author: lht
 */
public class PayPresenter extends BasePresenter<PayContract.IPayView> implements PayContract.IPayePresenter{

    private PayContract.IPayView view;
    private PayModel model = new PayModel();



    @Override
    public void onAliPay(Map<String, Object> parameters) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<AlipayBean> observer = new BaseObserver<AlipayBean>(view) {
            @Override
            public void onSuccess(AlipayBean bean) {
                view.onAliPay(bean.getOrder_id(), bean.getAlipay_message());
            }
            @Override
            public void onFailure(String errorMsg) {
                view.onOrderCreateFailure(errorMsg);
            }
        };
        model.onCreateAliOrder(observer,parameters);
    }

    @Override
    public void onWechatPay(Map<String, Object> parameters) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<WeChatPayBean> observer = new BaseObserver<WeChatPayBean>(view) {
            @Override
            public void onSuccess(WeChatPayBean bean) {
                view.onWeChatPay(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onOrderCreateFailure(errorMsg);
            }
        };
        model.onCreateWeChatOrder(observer,parameters);
    }

    @Override
    public void checkOrderStatus(String token, String order) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<List> observer = new BaseObserver<List>() {
            @Override
            public void onSuccess(List s) {
                view.onOrderPaySuccess(RESOURCES.getString(R.string.pay_success));
            }
            @Override
            public void onFailure(String errorMsg) {
                view.onOrderPayFailure(errorMsg);
            }
        };
        model.checkOrderStatus(observer, token, order);
    }
}
