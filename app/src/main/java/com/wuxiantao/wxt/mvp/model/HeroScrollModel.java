package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.adapter.bean.HeroScrolllBean;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.BoxTypeBean;
import com.wuxiantao.wxt.bean.ComposeHeroBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.CommissionService;

import java.util.List;

public class HeroScrollModel extends BaseModel {
    public void myScroll(BaseObserver<List<HeroScrolllBean>> observer, String token, String pid) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .myScroll(token, pid)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
    public void composeHero(BaseObserver<ComposeHeroBean> observer, String token, String cid) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .composeHero(token, cid)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
    public void getScrollCate(BaseObserver<List<BoxTypeBean>> observer, String token) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .getScrollCate(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
