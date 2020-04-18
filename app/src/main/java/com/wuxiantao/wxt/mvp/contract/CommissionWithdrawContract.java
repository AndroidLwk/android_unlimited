package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.CheckInInfoBean;
import com.wuxiantao.wxt.bean.CommissionWithdrawInfoBean;
import com.wuxiantao.wxt.mvp.withdraw.WithdrawMvpPresenter;
import com.wuxiantao.wxt.mvp.withdraw.WithdrawView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CommissionWithdrawContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-17 上午8:23
 * Description:${DESCRIPTION}
 */
public interface CommissionWithdrawContract {

    interface IWithdrawView  extends WithdrawView {
        void getWithdrawInfoSuccess(CommissionWithdrawInfoBean bean);
        void getWithdrawInfoFailure(String failure);
        void getCheckInInfoSuccess(CheckInInfoBean bean);
        void getCheckInInfoFailure(String failure);
        void checkInSuccess(String msg);
        void checkInFailure(String failure);
    }

    interface IWithdrawPresenter extends WithdrawMvpPresenter<IWithdrawView> {
        void getWithdrawInfo(String token);
        void getCheckInInfo(String token);
        void checkIn(String token);
    }


}
