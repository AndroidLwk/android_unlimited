package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.DepositDayBean;
import com.wuxiantao.wxt.bean.TodayShareDayBean;
import com.wuxiantao.wxt.mvp.contract.InviteFriendLoginContract;
import com.wuxiantao.wxt.mvp.model.InviteFriendLoginModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:InviteFriendLoginPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-16 下午3:45
 * Description:${DESCRIPTION}
 */
public class InviteFriendLoginPresenter extends BasePresenter<InviteFriendLoginContract.IInviteFriendView> implements  InviteFriendLoginContract.IInviteFriendPresenter{

    private InviteFriendLoginContract.IInviteFriendView view;
    private InviteFriendLoginModel model = new InviteFriendLoginModel();

    @Override
    public void depositDay(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<DepositDayBean> observer = new BaseObserver<DepositDayBean>(view) {
            @Override
            public void onSuccess(DepositDayBean bean) {
                view.depositDaySuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.depositDayFailure(errorMsg);
            }
        };
        model.depositDay(observer,token);
    }

    @Override
    public void todayShare(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<TodayShareDayBean> observer = new BaseObserver<TodayShareDayBean>() {
            @Override
            public void onSuccess(TodayShareDayBean bean) {
                view.todayShareSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.todayShareFailure(errorMsg);
            }
        };
        model.todayShare(observer,token);
    }
}
