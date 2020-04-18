package com.wuxiantao.wxt.mvp.activate;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.mvp.promotion.PromotionModel;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.RedBagService;

import java.util.List;
import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ActivateModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-4 下午10:47
 * Description:${DESCRIPTION}
 */
class ActivateModel extends PromotionModel {

    void onActivateFriend(BaseObserver<List> observer, Map<String, Object> map){
        HttpManager.newInstance()
                .createService(RedBagService.class)
                .onActivateFriend(map)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    void onActivateDoubleFriend(BaseObserver<List> observer, Map<String, Object> map){
        HttpManager.newInstance()
                .createService(RedBagService.class)
                .onActivateDoubleFriend(map)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
