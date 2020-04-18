package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.InterestRateBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:InterestRateContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-9 上午10:29
 * Description:${DESCRIPTION}
 */
public interface InterestRateContract {

    interface IRateView extends MvpView{
        void obtainRateSuccess(InterestRateBean bean);
        void obtainRateFailure(String failure);
    }

    interface IRatePresenter extends MvpPresenter<IRateView>{
        void obtainRate(String token);
    }
}
