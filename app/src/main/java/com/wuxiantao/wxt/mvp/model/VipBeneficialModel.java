package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.InviteApiService;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:VipBeneficialModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-16 上午11:30
 * Description:${DESCRIPTION}
 */
public class VipBeneficialModel extends BaseModel {

    public void receiveMemberActive(BaseObserver<List> observer, String token){
        HttpManager.newInstance()
                .createService(InviteApiService.class)
                .receiveMemberActive(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

}
