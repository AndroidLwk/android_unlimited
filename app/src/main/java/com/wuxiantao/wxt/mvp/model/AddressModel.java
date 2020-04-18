package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.AddressListBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.AddressApiService;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.PAGE_SIZE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AddressModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-5 下午1:29
 * Description:${DESCRIPTION}
 */
public class AddressModel extends BaseModel {

    public void getAddressList(BaseObserver<AddressListBean> observer,String token, int page){
        HttpManager.newInstance()
                .createService(AddressApiService.class)
                .getAddressList(token,page,PAGE_SIZE)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void setDefaultAddress(BaseObserver<List> observer,String token, String address_id){
        HttpManager.newInstance()
                .createService(AddressApiService.class)
                .setDefaultAddress(token,address_id)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void deleteAddress(BaseObserver<List> observer,String token, String address_id){
        HttpManager.newInstance()
                .createService(AddressApiService.class)
                .deleteAddress(token,address_id)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
