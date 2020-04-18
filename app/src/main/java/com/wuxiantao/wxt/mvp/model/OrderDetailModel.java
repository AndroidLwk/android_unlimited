package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.TaoBaoOrderDetailBean;
import com.wuxiantao.wxt.bean.YouXuanOrderDetailBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.OrderApiService;
import com.wuxiantao.wxt.net.service.TaoBaoApiService;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OrderDetailModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-22 下午5:30
 * Description:${DESCRIPTION}
 */
public class OrderDetailModel extends BaseModel {

    public void getOrderYouXuanDeatailInfo(BaseObserver<YouXuanOrderDetailBean> bean, String token, int id){
        HttpManager.newInstance()
                .createService(OrderApiService.class)
                .getOrderYouXuanDetailInfo(token,id)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(bean);
    }

    public void getOrderTaoBaoDeatailInfo(BaseObserver<TaoBaoOrderDetailBean> bean, String token, int id){
        HttpManager.newInstance()
                .createService(TaoBaoApiService.class)
                .getTaoBaoOrderDetailInfo(token,id)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(bean);
    }
}
