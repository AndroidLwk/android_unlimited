package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.MerchandiseDetailBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.TaoBaoApiService;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MerchandiseDetailModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-16 下午9:35
 * Description:${DESCRIPTION}
 */
public class MerchandiseDetailModel extends BaseModel {

    public void getProductDetail(BaseObserver<MerchandiseDetailBean> observer, long id){
        HttpManager.newInstance()
                .createService(TaoBaoApiService.class)
                .getProductDetail(id)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
