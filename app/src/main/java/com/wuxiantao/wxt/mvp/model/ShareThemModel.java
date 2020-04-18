package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.InviteFriendNumBean;
import com.wuxiantao.wxt.bean.ShareCodeBean;
import com.wuxiantao.wxt.bean.ShareRewardBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.base.DownObserver;
import com.wuxiantao.wxt.net.callback.ReqProgressCallBack;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.DownLoadService;
import com.wuxiantao.wxt.net.service.InviteApiService;
import com.wuxiantao.wxt.net.service.RedBagService;
import com.wuxiantao.wxt.net.service.ShareApiService;

import okhttp3.ResponseBody;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ShareThemModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 上午10:49
 * Description:${DESCRIPTION}
 */
public class ShareThemModel extends ShareBgModel {


    public void createShareCode(BaseObserver<ShareCodeBean> observer, String token, int key, int type) {
        HttpManager.newInstance()
                .createService(ShareApiService.class)
                .createShareCode(token, key, type)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void inviteFriendNum(BaseObserver<InviteFriendNumBean> observer, String token, int type) {
        HttpManager.newInstance()
                .createService(InviteApiService.class)
                .inviteFriendNum(token, type)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void downLoadImg(DownObserver<ResponseBody> observer, String url, ReqProgressCallBack callBack) {
        HttpManager.newInstance()
                .createResponseService(DownLoadService.class, callBack)
                .downLoadImg(url)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


    public void onShareReward(BaseObserver<ShareRewardBean> observer, String token){
        HttpManager.newInstance()
                .createService(RedBagService.class)
                .onShareReward(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
