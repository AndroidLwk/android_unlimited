package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.DefaultAddressBean;
import com.wuxiantao.wxt.mvp.pay.OrderPayModel;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.AddressApiService;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ConfirmOrderModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-5 下午5:12
 * Description:${DESCRIPTION}
 */
public class ConfirmOrderModel extends OrderPayModel {

    public void getDefaultAddress(BaseObserver<DefaultAddressBean> observer,String token){
        HttpManager.newInstance()
                .createService(AddressApiService.class)
                .getDefaultAddress(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
