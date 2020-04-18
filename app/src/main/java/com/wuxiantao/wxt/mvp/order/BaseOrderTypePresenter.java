package com.wuxiantao.wxt.mvp.order;

import com.wuxiantao.wxt.bean.OrderTypeBean;
import com.wuxiantao.wxt.mvp.presenter.BasePresenter;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseOrderTypePresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-26 上午10:54
 * Description:${DESCRIPTION}
 */
public class BaseOrderTypePresenter<V extends OrderTypeView> extends BasePresenter<V> {

    private V view;
    private OrderTypeModel model = new OrderTypeModel();

    protected void getOrderType(String token){
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<OrderTypeBean> observer = new BaseObserver<OrderTypeBean>() {
            @Override
            public void onSuccess(OrderTypeBean bean) {
                 view.getOrderTypeSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getOrderTypeFailure(errorMsg);
            }
        };
        model.getOrderType(observer,token);
    }
}
