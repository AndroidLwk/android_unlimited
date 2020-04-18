package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.AddressListBean;
import com.wuxiantao.wxt.mvp.contract.AddressContract;
import com.wuxiantao.wxt.mvp.model.AddressModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.RESOURCES;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AddressPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-5 下午1:31
 * Description:${DESCRIPTION}
 */
public class AddressPresenter extends BasePresenter<AddressContract.IAddressView> implements AddressContract.IAddressPresenter {

    private AddressContract.IAddressView view;
    private AddressModel model = new AddressModel();

    @Override
    public void getAddressList(String token, int page) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<AddressListBean> observer = new BaseObserver<AddressListBean>() {
            @Override
            public void onSuccess(AddressListBean bean) {
                view.getAddressListSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getAddressListFailure(errorMsg);
            }
        };
        model.getAddressList(observer,token,page);
    }

    @Override
    public void setDefaultAddress(String token, String address_id) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<List> observer = new BaseObserver<List>(view) {
            @Override
            public void onSuccess(List bean) {
                view.setDefaultAddressSuccess(RESOURCES.getString(R.string.set_address_success));
            }

            @Override
            public void onFailure(String errorMsg) {
                view.setDefaultAddressFailure(errorMsg);
            }
        };
        model.setDefaultAddress(observer,token,address_id);
    }

    @Override
    public void deleteAddress(String token, String address_id) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<List> observer = new BaseObserver<List>(view) {
            @Override
            public void onSuccess(List bean) {
                view.deleteAddressSuccess(RESOURCES.getString(R.string.delete_address_success));
            }

            @Override
            public void onFailure(String errorMsg) {
                view.deleteAddressFailure(errorMsg);
            }
        };
        model.deleteAddress(observer,token,address_id);
    }
}
