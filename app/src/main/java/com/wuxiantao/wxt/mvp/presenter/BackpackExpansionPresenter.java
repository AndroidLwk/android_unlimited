package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.AlipayBean;
import com.wuxiantao.wxt.bean.KuorongInfoBean;
import com.wuxiantao.wxt.bean.OrderStatusBean;
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.mvp.contract.BackpackExpansionContract;
import com.wuxiantao.wxt.mvp.model.BackpackExpansionModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.RESOURCES;

public class BackpackExpansionPresenter extends BasePresenter<BackpackExpansionContract> {
    private BackpackExpansionContract view;
    private BackpackExpansionModel model = new BackpackExpansionModel();

    public void kuorongInfo(String token) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<KuorongInfoBean> observer = new BaseObserver<KuorongInfoBean>(view) {
            @Override
            public void onSuccess(KuorongInfoBean bean) {
                view.showKuorongInfo(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.showKuorongInfo_failure(errorMsg);
            }
        };
        model.kuorongInfo(observer, token);
    }

    public void addbox_balance(String token, String type) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<List> observer = new BaseObserver<List>(view) {
            @Override
            public void onSuccess(List bean) {
            view.addbox_balanceSuccess("支付成功!");
            }

            @Override
            public void onFailure(String errorMsg) {
                view.addbox_balanceFailure(errorMsg);
            }
        };
        model.addbox_balance(observer, token, type);
    }

    public void addbox_alipay(String token, String type) {
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
        model.addbox_alipay(observer, token, type);
    }

    public void addbox_wx(String token, String type) {
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
        model.addbox_wx(observer, token, type);
    }

    public void checkOrderStatus(String token, String order_no) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<OrderStatusBean> observer = new BaseObserver<OrderStatusBean>() {
            @Override
            public void onSuccess(OrderStatusBean s) {
                view.onOrderPaySuccess(RESOURCES.getString(R.string.pay_success));
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onOrderPayFailure(errorMsg);
            }
        };
        model.checkOrderStatus(observer, token, order_no);
    }
}
