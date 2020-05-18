package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.AreaChangeInfoBean;
import com.wuxiantao.wxt.bean.DragonStatusInfoBean;
import com.wuxiantao.wxt.bean.GameMessageBean;
import com.wuxiantao.wxt.bean.IncomeHallBean;
import com.wuxiantao.wxt.bean.IncreaseCountBean;
import com.wuxiantao.wxt.bean.OpenDragonBean;
import com.wuxiantao.wxt.bean.StartExperienceBean;
import com.wuxiantao.wxt.bean.VideoDoubleBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:IncomeHallContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-7 下午3:22
 * Description:${DESCRIPTION}
 */
public interface IncomeHallContract {

    interface IIncomeHallView extends MvpView{

        void getIncomeHallInfoSuccess(IncomeHallBean bean);
        void getIncomeHallInfoFailure(String failure);

        void getDragonStatusInfoSuccess(DragonStatusInfoBean bean);
        void getDragonStatusInfoFailure(String failure);

        void onOpenDragonSuccess(OpenDragonBean bean);
        void onOpenDragonFailure(String failure);

        void onGameMessageSuccess(GameMessageBean bean);
        void onGameMessageFailure(String failure);

        void onStartExperienceSuccess(StartExperienceBean bean);
        void onStartExperienceFailure(String failure);

        void onChangeAreaInfoSuccess(AreaChangeInfoBean bean);
        void onChangeAreaInfoFailure(String failure);

        void onBindingAreaSuccess(List<String> msg);
        void onBindingAreaFailure(String failure);

        void onIncreaseCountSuccess(IncreaseCountBean bean);
        void onIncreaseCountFailure(String failure);

        void onVideoDoubleSuccess(VideoDoubleBean bean);
        void onVideoDoubleFailure(String failure);

        //获取我的游戏信息
        void onGetMyGameInfoSuccess();
        void onGetMyGameInfoFailure();
    }

    interface IIncomeHallPresenter extends MvpPresenter<IIncomeHallView>{
        void getIncomeHallInfo(String token);
        void getDragonStatusInfo(String token);
        void onOpenDragon(String token,int card_id);
        void onGameMessage(String token);
        void onStartExperience(String token);
        void onChangeAreaInfo(String token);
        void onBindingArea(String token,int id);
        void onIncreaseCount(String token);
        void onVideoDouble(String token,int dragon_id,String num);

        void getMyGameInfo(String token);
    }
}
