package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.InstructBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.SuperApiService;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:InstructModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-14 下午12:32
 * Description:${DESCRIPTION}
 */
public class InstructModel extends BaseModel {

    public void instruct(BaseObserver<InstructBean> observer){
        HttpManager.newInstance()
                .createService(SuperApiService.class)
                .instruct()
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
