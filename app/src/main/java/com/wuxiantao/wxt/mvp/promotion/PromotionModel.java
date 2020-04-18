package com.wuxiantao.wxt.mvp.promotion;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.PromotionLanguageBean;
import com.wuxiantao.wxt.mvp.model.BaseModel;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.ShareApiService;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:PromotionModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-6 下午1:48
 * Description:${DESCRIPTION}
 */
public class PromotionModel extends BaseModel {

    public void getPromotionLanguage(BaseObserver<PromotionLanguageBean> observer,int type){
        HttpManager.newInstance()
                .createService(ShareApiService.class)
                .getPromotionLanguage(type)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
