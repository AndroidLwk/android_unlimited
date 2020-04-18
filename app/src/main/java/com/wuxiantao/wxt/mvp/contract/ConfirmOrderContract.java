package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.DefaultAddressBean;
import com.wuxiantao.wxt.mvp.pay.OrderPayMvpPresenter;
import com.wuxiantao.wxt.mvp.pay.OrderPayView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ConfirmOrderContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-13 上午10:44
 * Description:${DESCRIPTION}
 */
public interface ConfirmOrderContract {

    interface IOrderView extends OrderPayView {
        void getDefaultAddressSuccess(DefaultAddressBean infoBean);
        void getDeafaultAddressFailure(String failure);
    }

    interface IOrderPresenter extends OrderPayMvpPresenter<IOrderView>{
        void getDefaultAddress(String token);
    }
}
