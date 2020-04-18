package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.TaskInfoBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.CommissionService;
import com.wuxiantao.wxt.net.service.DragonApiService;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DividendModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-7 下午12:48
 * Description:${DESCRIPTION}
 */
public class MyTaskModel extends BaseModel {


    public void getTaskInfo(BaseObserver<TaskInfoBean> observer,String token){
        HttpManager.newInstance()
                .createService(DragonApiService.class)
                .getTaskInfo(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


    public void checkIn(BaseObserver<List> observer, String token){
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .checkIn(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


    public void onAdsDevote(BaseObserver<List<String>> observer, String token){
        HttpManager.newInstance()
                .createService(DragonApiService.class)
                .onAdsDevote(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

}
