package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.TaoBaoSortBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.TaoBaoApiService;
import com.wuxiantao.wxt.net.service.UserApiService;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoSortModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-17 下午4:41
 * Description:${DESCRIPTION}
 */
public class TaoBaoSortModel extends BaseModel {

    public void getTaoBaoSort(BaseObserver<List<TaoBaoSortBean>> observer){
        HttpManager.newInstance()
                .createService(TaoBaoApiService.class)
                .getTaoBaoSort()
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


    public void receiveRedBag(BaseObserver<List> observer,String token){
        HttpManager.newInstance()
                .createService(UserApiService.class)
                .receiveRedBag(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
