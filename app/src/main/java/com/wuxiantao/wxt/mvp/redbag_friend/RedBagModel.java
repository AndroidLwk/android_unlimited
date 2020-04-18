package com.wuxiantao.wxt.mvp.redbag_friend;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.FriendListBean;
import com.wuxiantao.wxt.mvp.model.BaseModel;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.RedBagService;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:RedBagModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-18 下午3:42
 * Description:${DESCRIPTION}
 */
class RedBagModel extends BaseModel {

    void getRedBagFriendList(BaseObserver<FriendListBean> observer, Map<String, Object> parameters){
        HttpManager.newInstance()
                .createService(RedBagService.class)
                .getRedBagFriendList(parameters)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


}
