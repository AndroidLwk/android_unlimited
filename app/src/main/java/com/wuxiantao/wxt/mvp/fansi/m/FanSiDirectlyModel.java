package com.wuxiantao.wxt.mvp.fansi.m;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.FansiDirectlyBean;
import com.wuxiantao.wxt.mvp.model.BaseModel;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.FanSiApiService;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FanSiPotentialModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-5 下午6:24
 * Description:${DESCRIPTION} 直属粉丝
 */
 public class FanSiDirectlyModel extends BaseModel {

    public   void obtainDirectlyFansi(BaseObserver<FansiDirectlyBean> observer, Map<String,Object> parameters){
        HttpManager.newInstance()
                .createService(FanSiApiService.class)
                .obtainDirectlyFansi(parameters)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

}
