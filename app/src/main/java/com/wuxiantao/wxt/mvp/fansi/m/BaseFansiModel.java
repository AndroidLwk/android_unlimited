package com.wuxiantao.wxt.mvp.fansi.m;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.FansiDetailBean;
import com.wuxiantao.wxt.mvp.model.BaseModel;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.FanSiApiService;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseFansiModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-5 下午6:23
 * Description:${DESCRIPTION}
 */
 public class BaseFansiModel extends BaseModel {

    public void obtainFansiDetail(BaseObserver<FansiDetailBean> observer, int uid){
        HttpManager.newInstance()
                .createService(FanSiApiService.class)
                .obtainFansiDetail(uid)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


}
