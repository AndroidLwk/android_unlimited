package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.AddressApiService;

import java.util.List;
import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ReceiptModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-13 上午11:47
 * Description:${DESCRIPTION}
 */
public class ReceiptModel extends BaseModel {

    /**
     *  修改收货地址
     * @param observer observer
     * @param //token  用户unionid 必填
     * @param //username 收货人名称 必填
     * @param //phone 手机号 必填
     * @param //province 省份 必填
     * @param //city 城市 必填
     * @param //area 区 必填
     * @param //address 详细地址 选填
     * @param //address_id 地址ID
     */
    public void updateAddress(BaseObserver<List> observer, Map<String,Object> parameters){
        HttpManager.newInstance()
                .createService(AddressApiService.class)
                .updateAddress(parameters)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    /**
     *  添加收货地址
     * @param observer observer
     * @param //token  用户unionid 必填
     * @param //username 收货人名称 必填
     * @param //phone 手机号 必填
     * @param //province 省份 必填
     * @param //city 城市 必填
     * @param //area 区 必填
     * @param //address 详细地址 选填
     * @param //is_defualt 默认值0 选填
     */
    public void addAddress(BaseObserver<String> observer, Map<String,Object> parameters){
        HttpManager.newInstance()
                .createService(AddressApiService.class)
                .addAddress(parameters)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
