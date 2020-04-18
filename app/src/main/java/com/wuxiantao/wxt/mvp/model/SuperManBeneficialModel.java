package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.SuperManBeneBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.TaoBaoApiService;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SuperManBeneficialModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-23 上午10:58
 * Description:${DESCRIPTION}
 */
public class SuperManBeneficialModel extends BaseModel {

    public void getShoppingList(BaseObserver<SuperManBeneBean> observer,int page,int pagesize){
        HttpManager.newInstance()
                .createService(TaoBaoApiService.class)
                .getShoppingList(page,pagesize)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
