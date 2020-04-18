package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.ActiveStatusBean;
import com.wuxiantao.wxt.bean.VipStatusInfoBean;
import com.wuxiantao.wxt.mvp.banner.BannerModel;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.InviteApiService;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:QualityModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-16 下午3:32
 * Description:${DESCRIPTION}
 */
public class QualityModel extends BannerModel {

    public void isActiveStatus(BaseObserver<ActiveStatusBean> observer, String token){
        HttpManager.newInstance()
                .createService(InviteApiService.class)
                .isActiveStatus(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


    public void getVipStatusInfo(BaseObserver<VipStatusInfoBean> observer, String token){
        HttpManager.newInstance()
                .createService(InviteApiService.class)
                .getVipStatusInfo(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


}
