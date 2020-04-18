package com.wuxiantao.wxt.mvp.banner;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.BannerBean;
import com.wuxiantao.wxt.mvp.model.BaseModel;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.SuperApiService;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BannerModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-12 下午2:39
 * Description:${DESCRIPTION}
 */
public class BannerModel extends BaseModel {

    public void gainBanner(BaseObserver<BannerBean> observer, int type){
        HttpManager.newInstance()
                .createService(SuperApiService.class)
                .gainBanner(type)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
