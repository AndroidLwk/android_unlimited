package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.ShareAdBean;
import com.wuxiantao.wxt.bean.SharePicBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.CommissionService;

public class MyInvitationCodeModel {
    //分享二维码图片
    public void getSharePic(BaseObserver<SharePicBean> observer, String token) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .getSharePic(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
    //分享成功调用接口
    public void getShareAward(BaseObserver<ShareAdBean> observer, String token) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .getShareAward(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
