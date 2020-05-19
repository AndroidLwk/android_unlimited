package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.BoxTypeBean;
import com.wuxiantao.wxt.bean.CardInfoBean;
import com.wuxiantao.wxt.bean.ExchangeBean;
import com.wuxiantao.wxt.bean.MyBoxInfo;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.CommissionService;

import java.util.List;
import java.util.Map;

public class MyBackpackModel extends SettingPassWordModel {
    //我的背包
    public void myBox(BaseObserver<MyBoxInfo> observer, Map<String, Object> map) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .myBox(map)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    //背包分类
    public void getBoxCate(BaseObserver<List<BoxTypeBean>> observer, String token) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .getBoxCate(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    //使用卡片
    public void useCard(BaseObserver<CardInfoBean> observer, String token, String cid, String num) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .useCard(token, cid, num)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
    //销毁卡片
    public void discard(BaseObserver<CardInfoBean> observer, String token, String card_id, String num) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .discard(token, card_id, num)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
    //转赠刮刮卡和碎片
    public void exchange(BaseObserver<ExchangeBean> observer, Map<String, Object> map) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .exchange(map)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
