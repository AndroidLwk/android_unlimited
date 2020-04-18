package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.WinningListBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.LotteryApiService;

import static com.wuxiantao.wxt.config.Constant.PAGE_SIZE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:WinningListModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-11 下午2:42
 * Description:${DESCRIPTION}
 */
public class WinningListModel extends BaseModel {


    public void getWinningCopperKeyList(BaseObserver<WinningListBean> observer,String token,int page){
        HttpManager.newInstance()
                .createService(LotteryApiService.class)
                .getWinningCopperKeyList(token,page,PAGE_SIZE)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void getWinningGoldKeyList(BaseObserver<WinningListBean> observer,String token,int page){
        HttpManager.newInstance()
                .createService(LotteryApiService.class)
                .getWinningGoldKeyList(token,page,PAGE_SIZE)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void getWinningSilverKeyList(BaseObserver<WinningListBean> observer,String token,int page){
        HttpManager.newInstance()
                .createService(LotteryApiService.class)
                .getWinningSilverKeyList(token,page,PAGE_SIZE)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void getWinningAllList(BaseObserver<WinningListBean> observer,String token,int page){
        HttpManager.newInstance()
                .createService(LotteryApiService.class)
                .getWinningAllList(token,page,PAGE_SIZE)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
