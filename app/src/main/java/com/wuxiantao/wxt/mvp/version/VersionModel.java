package com.wuxiantao.wxt.mvp.version;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.CurrentVersionBean;
import com.wuxiantao.wxt.mvp.model.BaseModel;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.SuperApiService;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:VersionModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-15 下午1:20
 * Description:${DESCRIPTION}
 */
public class VersionModel extends BaseModel {

    public void getAppCurrentVersion(BaseObserver<CurrentVersionBean> observer){
        HttpManager.newInstance()
                .createService(SuperApiService.class)
                .getAppCurrentVersion()
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
