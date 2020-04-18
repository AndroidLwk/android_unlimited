package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.CheckInInfoBean;
import com.wuxiantao.wxt.bean.CommissionWithdrawInfoBean;
import com.wuxiantao.wxt.mvp.contract.CommissionWithdrawContract;
import com.wuxiantao.wxt.mvp.model.CommissionWithdrawModel;
import com.wuxiantao.wxt.mvp.withdraw.WithdrawBasePresenter;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.List;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.RESOURCES;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CommissionWithdrawPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-17 上午8:25
 * Description:${DESCRIPTION}
 */
public class CommissionWithdrawPresenter extends WithdrawBasePresenter<CommissionWithdrawContract.IWithdrawView> implements CommissionWithdrawContract.IWithdrawPresenter {

    private CommissionWithdrawContract.IWithdrawView view;
    private CommissionWithdrawModel model = new CommissionWithdrawModel();

    @Override
    public void getWithdrawInfo(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<CommissionWithdrawInfoBean> observer = new BaseObserver<CommissionWithdrawInfoBean>(view) {
            @Override
            public void onSuccess(CommissionWithdrawInfoBean bean) {
                view.getWithdrawInfoSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getWithdrawInfoFailure(errorMsg);
            }
        };
        model.getCommissionWithdrawInfo(observer,token);
    }

    @Override
    public void getCheckInInfo(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<CheckInInfoBean> observer = new BaseObserver<CheckInInfoBean>() {
            @Override
            public void onSuccess(CheckInInfoBean bean) {
                view.getCheckInInfoSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getCheckInInfoFailure(errorMsg);
            }
        };
        model.getCheckInInfo(observer,token);
    }

    @Override
    public void checkIn(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<List> observer = new BaseObserver<List>() {
            @Override
            public void onSuccess(List list) {
                view.checkInSuccess(RESOURCES.getString(R.string.check_in_success));
            }

            @Override
            public void onFailure(String errorMsg) {
                view.checkInFailure(errorMsg);
            }
        };
        model.checkIn(observer,token);
    }

    @Override
    public void withdraw(Map<String, Object> map) {
        super.withdraw(map,model);
    }
}
