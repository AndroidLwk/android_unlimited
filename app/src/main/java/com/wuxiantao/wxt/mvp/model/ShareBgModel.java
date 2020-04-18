package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.ShareBackGroundBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.ShareApiService;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ShareBgModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 上午9:00
 * Description:${DESCRIPTION}
 */
public class ShareBgModel extends BaseModel {


    public void  getShareBackGround(BaseObserver<ShareBackGroundBean> observer,int page,int pageSize){
        HttpManager.newInstance()
                .createService(ShareApiService.class)
                .getShareBackGround(page,pageSize)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }



}
