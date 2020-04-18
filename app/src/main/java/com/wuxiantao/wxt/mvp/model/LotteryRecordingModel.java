package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.LotteryRecordingBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.LotteryApiService;

import static com.wuxiantao.wxt.config.Constant.PAGE_SIZE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:LotteryRecordingModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-11 下午2:21
 * Description:${DESCRIPTION}
 */
public class LotteryRecordingModel extends BaseModel {

    public void getLotteryRecording(BaseObserver<LotteryRecordingBean> observer,String token,int page){
        HttpManager.newInstance()
                .createService(LotteryApiService.class)
                .getLotteryRecording(token,page,PAGE_SIZE)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
