package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.HistoryRecordingBean;
import com.wuxiantao.wxt.bean.SearchHotBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.TaoBaoApiService;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SearchModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 下午3:27
 * Description:${DESCRIPTION}
 */
public class SearchModel extends BaseModel {


    public void getSearchHot(BaseObserver<SearchHotBean> observer){
        HttpManager.newInstance()
                .createService(TaoBaoApiService.class)
                .getSearchHot()
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void getHistoryRecording(BaseObserver<HistoryRecordingBean> observer, String token){
        HttpManager.newInstance()
                .createService(TaoBaoApiService.class)
                .getSearchRecording(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
