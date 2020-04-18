package com.wuxiantao.wxt.net.download;

import com.wuxiantao.wxt.mvp.model.BaseModel;
import com.wuxiantao.wxt.net.base.DownObserver;
import com.wuxiantao.wxt.net.callback.ReqProgressCallBack;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.DownLoadService;

import okhttp3.ResponseBody;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DownloadModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-25 下午3:28
 * Description:${DESCRIPTION}
 */
public class DownloadModel extends BaseModel {

    public void onDownloadFile(DownObserver<ResponseBody> observer, String url, ReqProgressCallBack callBack) {
        HttpManager.newInstance()
                .createResponseService(DownLoadService.class, callBack)
                .downLoadImg(url)
                .compose(RxHelper.observableIO2Main())
                .subscribe(observer);
    }
}
