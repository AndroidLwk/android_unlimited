package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.FriendListBean;
import com.wuxiantao.wxt.bean.MakeGoldBean;
import com.wuxiantao.wxt.bean.RedBagInfoBean;
import com.wuxiantao.wxt.bean.UnfastenRedbagBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.RedBagService;

import java.util.Map;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:39 8-19
 * Description: ${DESCRIPTION} MainModel-主界面里数据的获取（数据库中获取或者联网请求在此实现将结果通过接口回调）
 * Author: Administrator Shiming-Shi
 */

public class DepositModel extends BaseModel{


    public void getRedBagInfo(BaseObserver<RedBagInfoBean> observer, String token){
        HttpManager.newInstance()
                .createService(RedBagService.class)
                .getRedBagInfo(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void openRedBag(BaseObserver<UnfastenRedbagBean> observer,String token){
        HttpManager.newInstance()
                .createService(RedBagService.class)
                .unfastenRedBag(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void onMakeGold(BaseObserver<MakeGoldBean> observer, String token){
        HttpManager.newInstance()
                .createService(RedBagService.class)
                .onMakeGold(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


    public void onMakeGoldDouble(BaseObserver<MakeGoldBean> observer, String token){
        HttpManager.newInstance()
                .createService(RedBagService.class)
                .onMakeGoldDouble(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }




    public void onGetFriendList(BaseObserver<FriendListBean> observer, Map<String,Object> map){
        HttpManager.newInstance()
                .createService(RedBagService.class)
                .onGetFriendList(map)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
