package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.DepositDayBean;
import com.wuxiantao.wxt.bean.TodayShareDayBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.InviteApiService;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:InviteFriendLoginModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-16 下午3:45
 * Description:${DESCRIPTION}
 */
public class InviteFriendLoginModel extends BaseModel {

    public void depositDay(BaseObserver<DepositDayBean> observer,String token){
        HttpManager.newInstance()
                .createService(InviteApiService.class)
                .depositDay(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void todayShare(BaseObserver<TodayShareDayBean> observer,String token){
        HttpManager.newInstance()
                .createService(InviteApiService.class)
                .todayShare(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
