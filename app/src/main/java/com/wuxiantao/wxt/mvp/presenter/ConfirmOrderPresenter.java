package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.DefaultAddressBean;
import com.wuxiantao.wxt.mvp.contract.ConfirmOrderContract;
import com.wuxiantao.wxt.mvp.model.ConfirmOrderModel;
import com.wuxiantao.wxt.mvp.pay.BaseOrderPresenter;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ConfirmOrderPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-13 上午10:46
 * Description:${DESCRIPTION}
 */
public class ConfirmOrderPresenter extends BaseOrderPresenter<ConfirmOrderContract.IOrderView> implements ConfirmOrderContract.IOrderPresenter {

    private ConfirmOrderModel model = new ConfirmOrderModel();

    private ConfirmOrderContract.IOrderView view;

    @Override
    public void onOrderCreate(Map<String, Object> parameters,int payType) {
        super.onOrderCreate(model,parameters,payType);
    }

    @Override
    public void checkOrderStatus(String token, String order_id) {
        super.checkOrderStatus(model,token,order_id);
    }

    @Override
    public void getDefaultAddress(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<DefaultAddressBean> observer = new BaseObserver<DefaultAddressBean>() {
            @Override
            public void onSuccess(DefaultAddressBean defaultAddressBean) {
                view.getDefaultAddressSuccess(defaultAddressBean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getDeafaultAddressFailure(errorMsg);
            }
        };
        model.getDefaultAddress(observer,token);
    }
}
