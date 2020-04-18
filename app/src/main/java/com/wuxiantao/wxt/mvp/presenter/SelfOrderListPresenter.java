package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.SelfOrderListBean;
import com.wuxiantao.wxt.mvp.contract.SelfOrderListContract;
import com.wuxiantao.wxt.mvp.model.SelfOrderListModel;
import com.wuxiantao.wxt.mvp.pay.BaseOrderPresenter;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SelfOrderListPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-18 上午9:45
 * Description:${DESCRIPTION}
 */
public class SelfOrderListPresenter extends BaseOrderPresenter<SelfOrderListContract.IOrderListView> implements SelfOrderListContract.IOrderListPresenter {

    private SelfOrderListContract.IOrderListView view;
    private SelfOrderListModel model = new SelfOrderListModel();

    @Override
    public void getSelfOrderList(Map<String, Object> parameters) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<SelfOrderListBean> observer = new BaseObserver<SelfOrderListBean>() {
            @Override
            public void onSuccess(SelfOrderListBean selfOrderListBean) {
                  view.getSelfOrderListSuccess(selfOrderListBean);
            }

            @Override
            public void onFailure(String errorMsg) {
                  view.getSelfOrderListFailure(errorMsg);
            }
        };
        model.getSelfOrderList(observer,parameters);
    }

    @Override
    public void onOrderCreate(Map<String, Object> parameters, int payType) {
        super.onOrderCreate(model,parameters,payType);
    }

    @Override
    public void checkOrderStatus(String token, String order_no) {
        super.checkOrderStatus(model,token,order_no);
    }
}
