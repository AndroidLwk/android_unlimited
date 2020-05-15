package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.MyBoxInfo;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.CommissionService;

import java.util.Map;

public class MyBackpackModel extends BaseModel {
    //我的背包
    public void myBox(BaseObserver<MyBoxInfo> observer, Map<String, Object> map) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .myBox(map)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
