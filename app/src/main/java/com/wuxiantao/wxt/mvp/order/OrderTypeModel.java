package com.wuxiantao.wxt.mvp.order;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.OrderTypeBean;
import com.wuxiantao.wxt.mvp.model.BaseModel;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.OrderApiService;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OrderTypeModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-26 上午10:51
 * Description:${DESCRIPTION}
 */
public class OrderTypeModel extends BaseModel {

    protected void getOrderType(BaseObserver<OrderTypeBean> observer,String token){
        HttpManager.newInstance()
                .createService(OrderApiService.class)
                .getOrderType(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

}
