package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.CardInfoBean;
import com.wuxiantao.wxt.bean.MySignInfo;
import com.wuxiantao.wxt.bean.MyTaskInfoBean;
import com.wuxiantao.wxt.bean.RandGetCardBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.CommissionService;

import java.util.List;

public class TaskHallFragmentModel extends BaseModel {
    //获取任务大厅信息
    public void getMyTaskInfo(BaseObserver<MyTaskInfoBean> observer, String token) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .taskInfo(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    //活跃度奖励领取
    public void newestActive(BaseObserver<List> observer, String token, String type) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .newestActive(token, type)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    //任务页签到
    public void taskSign(BaseObserver<MySignInfo> observer, String token) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .taskSign(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    //领取刮出的卡片
    public void getCard(BaseObserver<CardInfoBean> observer, String token, String type) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .getCard(token, type)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
    //看视频
    public void randGetCard(BaseObserver<RandGetCardBean> observer, String token, String type) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .randGetCard(token, type)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}