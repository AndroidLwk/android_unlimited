package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.CompositeDragonBean;
import com.wuxiantao.wxt.bean.CompositeScrapBean;
import com.wuxiantao.wxt.bean.DragonInfoBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.DragonApiService;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SynthesisDetailModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-7 下午6:28
 * Description:${DESCRIPTION}
 */
public class SynthesisDetailModel extends BaseModel {

    public void getDragonInfo(BaseObserver<DragonInfoBean> observer, String token){
        HttpManager.newInstance()
                .createService(DragonApiService.class)
                .getDragonInfo(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void onCompositeScrap(BaseObserver<CompositeScrapBean> observer, String token){
        HttpManager.newInstance()
                .createService(DragonApiService.class)
                .onCompositeScrap(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void onCompositeDragon(BaseObserver<CompositeDragonBean> observer, String token){
        HttpManager.newInstance()
                .createService(DragonApiService.class)
                .onCompositeDragon(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
