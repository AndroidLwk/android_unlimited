package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.TaoBaoOrderListBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.TaoBaoApiService;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoOrderListModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-18 上午9:42
 * Description:${DESCRIPTION}
 */
public class TaoBaoOrderListModel extends BaseModel {

    public void getTaoBaoOrderList(BaseObserver<TaoBaoOrderListBean> observer, Map<String,Object> parameters){
        HttpManager.newInstance()
                .createService(TaoBaoApiService.class)
                .getTaoBaoOrderList(parameters)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
