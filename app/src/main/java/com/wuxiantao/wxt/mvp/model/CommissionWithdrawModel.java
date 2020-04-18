package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.CheckInInfoBean;
import com.wuxiantao.wxt.bean.CommissionWithdrawInfoBean;
import com.wuxiantao.wxt.mvp.withdraw.WithdrawModel;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.CommissionService;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CommissionWithdrawModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-17 上午8:24
 * Description:${DESCRIPTION}
 */
public class CommissionWithdrawModel extends WithdrawModel {

    //获取佣金提现信息
    public void getCommissionWithdrawInfo(BaseObserver<CommissionWithdrawInfoBean> observer, String token){
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .getCommissionWithdrawInfo(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


    public void getCheckInInfo(BaseObserver<CheckInInfoBean> observer, String token){
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .getCheckInInfo(token)
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

}
