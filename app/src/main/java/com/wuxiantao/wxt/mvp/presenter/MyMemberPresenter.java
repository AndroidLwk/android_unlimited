package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.AlipayBean;
import com.wuxiantao.wxt.bean.OrderStatusBean;
import com.wuxiantao.wxt.bean.VipStatusInfoBean;
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.mvp.contract.MyMemberContract;
import com.wuxiantao.wxt.mvp.model.ConfirmOrderModel;
import com.wuxiantao.wxt.mvp.model.MyMemberModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.RESOURCES;

public class MyMemberPresenter extends BasePresenter<MyMemberContract> {
    private MyMemberContract view;
    private MyMemberModel model = new MyMemberModel();
    private ConfirmOrderModel orderModel = new ConfirmOrderModel();

    public void getVipStatusInfo(String token) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<VipStatusInfoBean> observer = new BaseObserver<VipStatusInfoBean>() {
            @Override
            public void onSuccess(VipStatusInfoBean bean) {
                view.showMymemberInfo(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onFailure(errorMsg);
            }
        };
        model.getVipStatusInfo(observer, token);
    }

    public void onOrderCreate(Map<String, Object> parameters, int payType) {
        //支付宝
        if (payType == 1) {
            orderModel.onOrderCreateAlipay(createAlipayObserver(), parameters);
        } else {
            //微信
            orderModel.onOrderCreateWeChat(createWeChatObserver(), parameters);
        }
    }

    private BaseObserver createWeChatObserver() {
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
        return observer;
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
        orderModel.checkOrderStatus(observer, token, order_no);
    }

    private BaseObserver createAlipayObserver() {
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
        return observer;
    }
}