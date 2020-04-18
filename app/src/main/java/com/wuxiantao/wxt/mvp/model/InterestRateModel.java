package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.InterestRateBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.SuperApiService;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:InterestRateModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-9 上午10:31
 * Description:${DESCRIPTION}
 */
public class InterestRateModel extends BaseModel {

    public void obtainRate(BaseObserver<InterestRateBean> observer, String token){
        HttpManager.newInstance()
                .createService(SuperApiService.class)
                .obtainRate(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
