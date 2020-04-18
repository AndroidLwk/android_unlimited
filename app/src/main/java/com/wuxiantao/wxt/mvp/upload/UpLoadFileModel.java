package com.wuxiantao.wxt.mvp.upload;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.UpdataLoadFileBean;
import com.wuxiantao.wxt.mvp.model.BaseModel;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.callback.ReqProgressCallBack;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.UserApiService;

import okhttp3.MultipartBody;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-27 上午10:29
 * Description:${DESCRIPTION}
 */
public class UpLoadFileModel extends BaseModel {


    void uploadFiles(BaseObserver<UpdataLoadFileBean> observer, ReqProgressCallBack callBack, MultipartBody.Part body) {
        HttpManager.newInstance()
                .createReqeustService(UserApiService.class,callBack)
                .uploadFiles(body)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


}
