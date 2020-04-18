package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.TaoBaoOrderDetailBean;
import com.wuxiantao.wxt.bean.YouXuanOrderDetailBean;
import com.wuxiantao.wxt.mvp.contract.OrderDetailsContract;
import com.wuxiantao.wxt.mvp.model.OrderDetailModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OrderDetailPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-22 下午5:34
 * Description:${DESCRIPTION}
 */
public class OrderDetailPresenter extends BasePresenter<OrderDetailsContract.IOrderDetailsView> implements OrderDetailsContract.IOrderDetailsPresenter {

    private OrderDetailsContract.IOrderDetailsView view;
    private OrderDetailModel model = new OrderDetailModel();

    @Override
    public void getYouXuanOrderDetail(String token, int order_id) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<YouXuanOrderDetailBean> observer = new BaseObserver<YouXuanOrderDetailBean>(view) {
            @Override
            public void onSuccess(YouXuanOrderDetailBean bean) {
                view.getYouXuanOrderDetailSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getYouXuanOrderDetailFailure(errorMsg);
            }
        };
        model.getOrderYouXuanDeatailInfo(observer,token,order_id);
    }

    @Override
    public void getTaoBaoOrderDetail(String token, int order_id) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<TaoBaoOrderDetailBean> observer = new BaseObserver<TaoBaoOrderDetailBean>(view) {
            @Override
            public void onSuccess(TaoBaoOrderDetailBean bean) {
                view.getTaoBaoOrderDetailSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getTaoBaoOrderDetailFailure(errorMsg);
            }
        };
        model.getOrderTaoBaoDeatailInfo(observer,token,order_id);
    }
}
