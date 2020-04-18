package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.bean.MyIncomeBean;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.DragonApiService;

import static com.wuxiantao.wxt.config.Constant.PAGE_SIZE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MyIncomeModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-7 下午6:18
 * Description:${DESCRIPTION}
 */
public class MyIncomeModel extends BaseModel {



    public void getMyIncomeList(BaseObserver<MyIncomeBean> observer,String token,int page){
        HttpManager.newInstance()
                .createService(DragonApiService.class)
                .getMyIncomeList(token,page,PAGE_SIZE)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
