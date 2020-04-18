package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ReceiptContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-13 上午11:45
 * Description:${DESCRIPTION}
 */
public interface ReceiptContract {

    interface IReceiptView extends MvpView{
        void updateAddressSuccess(String msg);
        void updateAddressFailure(String failure);
        void addAddressSuccess(String addresId);
        void addAddressFailure(String failure);
    }

    interface IReceiptPresenter extends MvpPresenter<IReceiptView>{
        void updateAddress(Map<String,Object> parameters);
        void addAddress(Map<String,Object> parameters);
    }
}
