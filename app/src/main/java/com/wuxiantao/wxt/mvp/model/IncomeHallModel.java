package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.AreaChangeInfoBean;
import com.wuxiantao.wxt.bean.DragonStatusInfoBean;
import com.wuxiantao.wxt.bean.GameMessageBean;
import com.wuxiantao.wxt.bean.IncomeHallBean;
import com.wuxiantao.wxt.bean.IncreaseCountBean;
import com.wuxiantao.wxt.bean.MyGameInfoBean;
import com.wuxiantao.wxt.bean.OpenDragonBean;
import com.wuxiantao.wxt.bean.StartExperienceBean;
import com.wuxiantao.wxt.bean.VideoDoubleBean;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.CommissionService;
import com.wuxiantao.wxt.net.service.DragonApiService;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:IncomeHallModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-7 下午3:23
 * Description:${DESCRIPTION}
 */
public class IncomeHallModel extends BaseModel {

    public void getIncomeHallInfo(BaseObserver<IncomeHallBean> observer,String token){
        HttpManager.newInstance()
                .createService(DragonApiService.class)
                .getIncomeHallInfo(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void getDragonStatusInfo(BaseObserver<DragonStatusInfoBean> observer, String token){
        HttpManager.newInstance()
                .createService(DragonApiService.class)
                .getDragonStatusInfo(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void onOpenDragon(BaseObserver<OpenDragonBean> observer, String token,int card_id){
        HttpManager.newInstance()
                .createService(DragonApiService.class)
                .onOpenDragon(token,card_id)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void onVideoDouble(BaseObserver<VideoDoubleBean> observer, String token, int dragon_id, String num){
        HttpManager.newInstance()
                .createService(DragonApiService.class)
                .onVideoDouble(token,dragon_id,num)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void onGameMessage(BaseObserver<GameMessageBean> observer, String token){
        HttpManager.newInstance()
                .createService(DragonApiService.class)
                .onGameMessage(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void onStartExperience(BaseObserver<StartExperienceBean> observer, String token){
        HttpManager.newInstance()
                .createService(DragonApiService.class)
                .onStartExperience(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void onChangeAreaInfo(BaseObserver<AreaChangeInfoBean> observer, String token){
        HttpManager.newInstance()
                .createService(DragonApiService.class)
                .onChangeAreaInfo(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void onBindingArea(BaseObserver<List<String>> observer, String token, int id){
        HttpManager.newInstance()
                .createService(DragonApiService.class)
                .onBindingArea(token,id)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void onIncreaseCount(BaseObserver<IncreaseCountBean> observer, String token){
        HttpManager.newInstance()
                .createService(DragonApiService.class)
                .onIncreaseCount(token)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    /**
     * 斩妖之旅
     * @param observer
     * @param token
     */
    public void onGetMyGameInfo(BaseObserver<MyGameInfoBean> observer, String token){
        HttpManager.newInstance()
            .createService(DragonApiService.class)
            .onGetMyGameInfo(token)
            .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
            .subscribe(observer);
    }

    //开始分红
    public void enrollBonus(BaseObserver<List> observer, String token, String type) {
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .enrollBonus(token, type)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
