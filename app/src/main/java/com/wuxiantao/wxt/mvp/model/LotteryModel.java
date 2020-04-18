package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.LotteryBean;
import com.wuxiantao.wxt.bean.LotteryListBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.LotteryApiService;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:LotteryModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-11 下午12:56
 * Description:${DESCRIPTION}
 */
public class LotteryModel extends BaseModel {

    public void getLotteryList(BaseObserver<LotteryListBean> observer, String token) {
        HttpManager.newInstance()
                .createService(LotteryApiService.class)
                .getLotteryList(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void onLottery(BaseObserver<LotteryBean> observer, String token) {
        HttpManager.newInstance()
                .createService(LotteryApiService.class)
                .onLottery(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
