package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.OnLineServiceBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.download.DownloadModel;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.UserApiService;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OnLineServiceModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-21 下午1:56
 * Description:${DESCRIPTION}
 */
public class OnLineServiceModel extends DownloadModel {

    public void getOnLineServiceInfo(BaseObserver<OnLineServiceBean> observer, String token){
        HttpManager.newInstance()
                .createService(UserApiService.class)
                .getOnLineServiceInfo(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
