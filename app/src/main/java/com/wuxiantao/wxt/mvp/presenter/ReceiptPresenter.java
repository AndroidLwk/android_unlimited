package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.contract.ReceiptContract;
import com.wuxiantao.wxt.mvp.model.ReceiptModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.List;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.RESOURCES;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ReceiptPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-13 上午11:55
 * Description:${DESCRIPTION}
 */
public class ReceiptPresenter extends BasePresenter<ReceiptContract.IReceiptView> implements ReceiptContract.IReceiptPresenter {

    private ReceiptModel model = new ReceiptModel();
    private ReceiptContract.IReceiptView view;

    @Override
    public void updateAddress(Map<String, Object> parameters) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<List> observer = new BaseObserver<List>(view) {
            @Override
            public void onSuccess(List s) {
                view.updateAddressSuccess(RESOURCES.getString(R.string.update_address_success));
            }

            @Override
            public void onFailure(String errorMsg) {
                view.updateAddressFailure(errorMsg);
            }
        };
        model.updateAddress(observer,parameters);
    }

    @Override
    public void addAddress(Map<String, Object> parameters) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<String> observer = new BaseObserver<String>(view) {
            @Override
            public void onSuccess(String addresId) {
                view.addAddressSuccess(addresId);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.addAddressFailure(errorMsg);
            }
        };
        model.addAddress(observer,parameters);
    }


}
