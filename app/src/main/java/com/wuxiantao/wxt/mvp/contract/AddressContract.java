package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.AddressListBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AddressContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-5 下午1:28
 * Description:${DESCRIPTION}
 */
public interface AddressContract {

    interface IAddressView extends MvpView{
        void getAddressListSuccess(AddressListBean bean);
        void getAddressListFailure(String failure);
        void setDefaultAddressSuccess(String msg);
        void setDefaultAddressFailure(String failure);
        void deleteAddressSuccess(String msg);
        void deleteAddressFailure(String failure);
    }

    interface IAddressPresenter extends MvpPresenter<IAddressView>{
        void getAddressList(String token,int page);
        void setDefaultAddress(String token,String address_id);
        void deleteAddress(String token,String address_id);
    }
}
