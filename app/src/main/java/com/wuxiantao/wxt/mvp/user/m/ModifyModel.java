package com.wuxiantao.wxt.mvp.user.m;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.mvp.model.BaseModel;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.UserApiService;

import java.util.List;
import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ModifyModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-6 下午2:56
 * Description:${DESCRIPTION}
 */
public class ModifyModel extends BaseModel {

    public void modifyPersonal(BaseObserver<List> observer,Map<String,Object> parameters){
        HttpManager.newInstance()
                .createService(UserApiService.class)
                .modifyPersonal(parameters)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
