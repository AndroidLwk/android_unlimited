package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.mvp.pay.OrderPayModel;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.GameApiService;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:H5GameModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-12-29 上午10:44
 * Description:${DESCRIPTION}
 */
public class H5GameModel extends OrderPayModel {

    public void onGetLoadingImg(BaseObserver<String> observer){
        HttpManager.newInstance()
                .createService(GameApiService.class)
                .onGetGameLoadingImg()
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


}
