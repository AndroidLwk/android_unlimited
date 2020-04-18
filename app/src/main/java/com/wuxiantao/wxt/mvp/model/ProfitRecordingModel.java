package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.ProfitRecordingBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.SuperApiService;

import static com.wuxiantao.wxt.config.Constant.PAGE_SIZE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ProfitRecordingModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 上午9:40
 * Description:${DESCRIPTION}
 */
public class ProfitRecordingModel extends BaseModel {

    public void getProfitRecording(BaseObserver<ProfitRecordingBean> observer, String token, int page){
        HttpManager.newInstance()
                .createService(SuperApiService.class)
                .getProfitRecording(token,page,PAGE_SIZE)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
