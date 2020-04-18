package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.DepositDayBean;
import com.wuxiantao.wxt.bean.TodayShareDayBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:InviteFriendLoginContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-16 下午3:44
 * Description:${DESCRIPTION}
 */
public interface InviteFriendLoginContract {

     interface IInviteFriendView extends MvpView{
        void depositDaySuccess(DepositDayBean bean);
        void depositDayFailure(String failure);
        void todayShareSuccess(TodayShareDayBean bean);
        void todayShareFailure(String failure);
    }

     interface IInviteFriendPresenter extends MvpPresenter<IInviteFriendView>{
        void depositDay(String token);
        void todayShare(String token);
    }
}
