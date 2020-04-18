package com.wuxiantao.wxt.mvp.pay;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.AlipayBean;
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.mvp.presenter.BasePresenter;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.List;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.RESOURCES;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseOrderPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-13 上午10:39
 * Description:${DESCRIPTION}
 */
public class BaseOrderPresenter<V extends OrderPayView> extends BasePresenter<V> {

    private V view;

    protected void onOrderCreate(OrderPayModel model,Map<String,Object> map,int payType){
        if (view == null){
            view = getMvpView();
        }
        switch (payType){
            case 1:
                model.onOrderCreateAlipay(createAlipayObserver(),map);
                break;
            case 2:
                model.onOrderCreateWeChat(createWeChatObserver(),map);
                break;
            //游戏支付订单创建:支付宝
            case -1:
                model.onCreateAliOrder(createAlipayObserver(),map);
                break;
            //游戏支付订单创建:微信
            case -2:
                model.onCreateWeChatOrder(createWeChatObserver(),map);
                break;
        }

    }

    private BaseObserver createWeChatObserver(){
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


    private BaseObserver createAlipayObserver(){
        BaseObserver<AlipayBean> observer = new BaseObserver<AlipayBean>(view) {
            @Override
            public void onSuccess(AlipayBean bean) {
                view.onAliPay(bean.getOrder_id(),bean.getAlipay_message());
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onOrderCreateFailure(errorMsg);
            }
        };
        return observer;
    }


    protected void checkOrderStatus(OrderPayModel model,String token,String order_id){
        if (view == null){
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
        model.checkOrderStatus(observer,token,order_id);
    }




}
