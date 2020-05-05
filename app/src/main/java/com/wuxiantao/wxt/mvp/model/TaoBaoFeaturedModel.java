package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.SelfEmployedBean;
import com.wuxiantao.wxt.bean.TaoBaoHomeBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.TaoBaoApiService;

import static com.wuxiantao.wxt.config.Constant.PAGE_SIZE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoFeaturedModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-17 下午8:03
 * Description:${DESCRIPTION}
 */
public class TaoBaoFeaturedModel extends BaseModel {
    //淘宝商品
    public void getTaoBaoHome(BaseObserver<TaoBaoHomeBean> observer,int page){
        HttpManager.newInstance()
                .createService(TaoBaoApiService.class)
                .getTaoBaoHome(page,PAGE_SIZE)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    //自营商品
    public void getSelfEmployed(BaseObserver<SelfEmployedBean> observer){
        HttpManager.newInstance()
                .createService(TaoBaoApiService.class)
                .getSelfEmployed()
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
