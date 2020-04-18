package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.LotteryBean;
import com.wuxiantao.wxt.bean.LotteryListBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:LotteryContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-11 下午12:55
 * Description:${DESCRIPTION}
 */
public interface LotteryContract {

    interface ILotteryView extends MvpView{
        void getLotteryListSuccess(LotteryListBean bean);
        void getLotteryListFailure(String failure);
        void onLotterySuccess(LotteryBean bean);
        void onLotteryFailure(String failure);
    }

    interface ILotteryPresenter extends MvpPresenter<ILotteryView>{
        void getLotteryList(String token);
        void onLottery(String token);
    }
}
