package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.HighAreaListBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.HighAreService;

import static com.wuxiantao.wxt.config.Constant.PAGE_SIZE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:HighAreaModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-18 上午9:46
 * Description:${DESCRIPTION}
 */
public class HighAreaModel extends BaseModel {

    public void getHighAreaList(BaseObserver<HighAreaListBean> observer,String token,int page){
        HttpManager.newInstance()
                .createService(HighAreService.class)
                .getHighAreaList(token,page,PAGE_SIZE)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
